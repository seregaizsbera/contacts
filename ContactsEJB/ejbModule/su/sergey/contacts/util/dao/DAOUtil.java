package su.sergey.contacts.util.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.StringTokenizer;

import su.sergey.contacts.valueobjects.Currency;
import su.sergey.contacts.valueobjects.impl.DefaultCurrency;

/**
 * DAOUtil содержит утильный статические методы для работы с базой.
 * 
 * @author 
 */
final public class DAOUtil {
	private DAOUtil() {}

	public static void log(String str) {
		System.err.println(str);
	}

	public static void log(Exception e) {
		e.printStackTrace();
	}

	public static String convertString(String s) {
		if (s.indexOf('\'') != -1) {
			StringBuffer buf = new StringBuffer();
			StringTokenizer tok = new StringTokenizer(s, "\'");
			while (tok.hasMoreTokens()) {
				buf.append(tok.nextToken());
				buf.append("''");
			}
			return buf.toString();
		} else {
			return s;
		}
	}

	/*Заменяет в строке символы '*' на '%' и '?' на '_'*/
	public static String convertSearchString(String str, boolean isLikeSearch) {

		StringBuffer result;

		result = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == '*') {
				result.append('%');
			} else if (str.charAt(i) == '?') {
				result.append('_');
			} else if (str.charAt(i) == '\'') {
				result.append("''");
			} else if (str.charAt(i) == '%') {
				if (isLikeSearch) {
					result.append("\\%");
				} else {
					result.append(str.charAt(i));
				}
			} else if (str.charAt(i) == '_') {
				if (isLikeSearch) {
					result.append("\\_");
				} else {
					result.append(str.charAt(i));
				}
			} else if (str.charAt(i) == '\\') {
				if (isLikeSearch) {
					result.append("\\\\");
				} else {
					result.append(str.charAt(i));
				}
			} else {
				result.append(str.charAt(i));
			}
		}

		return result.toString();
	}

	public static boolean close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			return true;
		} catch (SQLException sqle) {
			log("Exception closing result set: " + sqle);
			log(sqle);
			return false;
		}
	}

	public static boolean close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
			return true;
		} catch (SQLException sqle) {
			log("Exception closing statement: " + sqle);
			log(sqle);
			return false;
		}
	}

	public static boolean close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
			return true;
		} catch (SQLException sqle) {
			log("Exception closing connection: " + sqle);
			log(sqle);
			return false;
		}
	}

	public static void setString(PreparedStatement pstmt, int index, String val) throws SQLException {
		if (val != null) {
			pstmt.setString(index, val);
		} else {
			pstmt.setNull(index, Types.VARCHAR);
		}
	}

	public static void setBigDecimal(PreparedStatement pstmt, int index, BigDecimal val) throws SQLException {
		if (val != null) {
			pstmt.setBigDecimal(index, constraintPrecisionOfBigDecimalValue(val));
		} else {
			pstmt.setNull(index, Types.DECIMAL);
		}
	}

	private static BigDecimal constraintPrecisionOfBigDecimalValue(BigDecimal val) {
		int MAXIMUM_ALLOWED_PRECISION_IN_DATABASE = 38;
		if (val.toString().length() > MAXIMUM_ALLOWED_PRECISION_IN_DATABASE) {
			return new BigDecimal(val.toString().substring(1, MAXIMUM_ALLOWED_PRECISION_IN_DATABASE));
		} else {
			return val;
		}

	}

	public static void setInt(PreparedStatement pstmt, int index, Integer val) throws SQLException {
		if (val != null) {
			pstmt.setInt(index, val.intValue());
		} else {
			pstmt.setNull(index, Types.INTEGER);
		}
	}

	public static void setBoolean(PreparedStatement pstmt, int index, Boolean val) throws SQLException {
		if (val != null) {
			pstmt.setBoolean(index, val.booleanValue());
		} else {
			pstmt.setNull(index, Types.BIT);
		}
	}

	public static void setFloat(PreparedStatement pstmt, int index, Float val) throws SQLException {
		if (val != null) {
			pstmt.setFloat(index, val.floatValue());
		} else {
			pstmt.setNull(index, Types.FLOAT);
		}
	}

	public static void setCurrency(PreparedStatement pstmt, int index, Currency val) throws SQLException {
		//setBigDecimal(pstmt, index, (val != null) ? val.getCurrency() : null);
		//val.f
		if (val != null) {
			//pstmt.setFloat(index, new Float("" + val.getIntegerValue() + "." + val.getFractionalValue()).floatValue());
			pstmt.setBigDecimal(index, new BigDecimal("" + val.getIntegerValue() + "." + val.getFractionalValue()));
		} else {
			//pstmt.setNull(index, Types.FLOAT);
			pstmt.setNull(index, Types.DECIMAL);
		}
	}

	public static void setStream(PreparedStatement pstmt, int index, InputStream val, int length) throws SQLException {
		if (val != null) {
			pstmt.setBinaryStream(index, val, length);
		} else {
			pstmt.setNull(index, Types.LONGVARBINARY);
		}
	}

	public static void setSerializedObject(PreparedStatement pstmt, int index, Serializable val) throws SQLException, DAOException {
		try {
			if (val != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(val);
				oos.close();
				byte[] arr = baos.toByteArray();
				pstmt.setBinaryStream(index, new ByteArrayInputStream(arr), arr.length);
			} else {
				pstmt.setNull(index, Types.VARBINARY);
			}
		} catch (IOException ioe) {
			throw new DAOException(ioe);
		}
	}

	public static void setTimestamp(PreparedStatement pstmt, int index, java.util.Date val) throws SQLException {
		if (val != null) {
			pstmt.setTimestamp(index, new java.sql.Timestamp(val.getTime()));
		} else {
			pstmt.setNull(index, Types.TIMESTAMP);
		}
	}

	public static void setDate(PreparedStatement pstmt, int index, java.util.Date val) throws SQLException {
		if (val != null) {
			pstmt.setDate(index, new java.sql.Date(val.getTime()));
		} else {
			pstmt.setNull(index, Types.DATE);
		}
	}

	public static void setTime(PreparedStatement pstmt, int index, java.util.Date val) throws SQLException {
		if (val != null) {
			pstmt.setTime(index, new java.sql.Time(val.getTime()));
		} else {
			pstmt.setNull(index, Types.TIME);
		}
	}

	public static BigDecimal getBigDecimal(ResultSet rs, String fieldName) throws SQLException {
		BigDecimal value = rs.getBigDecimal(fieldName);
		if (rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public static BigDecimal getBigDecimal(ResultSet rs, int index) throws SQLException {
		BigDecimal value = rs.getBigDecimal(index);
		if (rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public static java.util.Date getTimestamp(ResultSet rs, String fieldName) throws SQLException {
		java.sql.Timestamp value = rs.getTimestamp(fieldName);
		if (rs.wasNull()) {
			return null;
		} else {
			return new java.util.Date(value.getTime());
		}
	}

	public static java.util.Date getTimestamp(ResultSet rs, int index) throws SQLException {
		java.sql.Timestamp value = rs.getTimestamp(index);
		if (rs.wasNull()) {
			return null;
		} else {
			return value;
		}
	}

	public static java.util.Date getDate(ResultSet rs, int index) throws SQLException {
		java.util.Date value = rs.getDate(index);
		if (rs.wasNull()) {
			return null;
		} else {
			return value;
		}
	}

	public static java.util.Date getDate(ResultSet rs, String fieldName) throws SQLException {
		java.util.Date value = rs.getDate(fieldName);
		if (rs.wasNull()) {
			return null;
		} else {
			return value;
		}
	}

	public static java.util.Date getTime(ResultSet rs, int index) throws SQLException {
		java.util.Date value = rs.getTime(index);
		if (rs.wasNull()) {
			return null;
		} else {
			return value;
		}
	}

	public static Serializable getSerializedObject(ResultSet rs, String fieldName) throws SQLException, DAOException {
		try {
			InputStream stream = rs.getBinaryStream(fieldName);
			if (rs.wasNull()) {
				return null;
			} else {
				ObjectInputStream ois = new ObjectInputStream(stream);
				Serializable res = (Serializable) ois.readObject();
				ois.close();
				return res;
			}
		} catch (IOException ioe) {
			throw new DAOException(ioe);
		} catch (ClassNotFoundException cnfe) {
			throw new DAOException(cnfe);
		}
	}

	public static Serializable getSerializedObject(ResultSet rs, int index) throws SQLException, DAOException {
		try {
			InputStream stream = rs.getBinaryStream(index);
			if (rs.wasNull()) {
				return null;
			} else {
				ObjectInputStream ois = new ObjectInputStream(stream);
				Serializable res = (Serializable) ois.readObject();
				ois.close();
				return res;
			}
		} catch (IOException ioe) {
			throw new DAOException(ioe);
		} catch (ClassNotFoundException cnfe) {
			throw new DAOException(cnfe);
		}
	}

	public static InputStream getStream(ResultSet rs, String fieldName) throws SQLException, DAOException {
		InputStream stream = rs.getBinaryStream(fieldName);
		if (rs.wasNull()) {
			return null;
		} else {
			return stream;
		}
	}

	public static InputStream getStream(ResultSet rs, int index) throws SQLException, DAOException {
		InputStream value = rs.getBinaryStream(index);
		if (rs.wasNull()) {
			return null;
		} else {
			return value;
		}
	}

	public static String getString(ResultSet rs, String fieldName) throws SQLException {
		String value = rs.getString(fieldName);
		if (rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public static String getString(ResultSet rs, int index) throws SQLException {
		String value = rs.getString(index);
		if (rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public static String getCharString(ResultSet rs, int index) throws SQLException {
		String s = getString(rs, index);
		return s != null ? s.trim() : null;
	}

	public static String getCharString(ResultSet rs, String fieldName) throws SQLException {
		String s = getString(rs, fieldName);
		return s != null ? s.trim() : null;
	}

	public static Integer getInt(ResultSet rs, int index) throws SQLException {
		int value = rs.getInt(index);

		if (rs.wasNull()) {
			return null;
		}

		return new Integer(value);
	}

	public static Integer getInt(ResultSet rs, String fieldName) throws SQLException {
		int value = rs.getInt(fieldName);
		if (rs.wasNull()) {
			return null;
		}
		return new Integer(value);
	}

	public static Boolean getBoolean(ResultSet rs, int index) throws SQLException {
		boolean value = rs.getBoolean(index);
		if (rs.wasNull()) {
			return null;
		}

		return new Boolean(value);
	}

	public static Boolean getBoolean(ResultSet rs, String fieldName) throws SQLException {
		boolean value = rs.getBoolean(fieldName);
		if (rs.wasNull()) {
			return null;
		}
		return new Boolean(value);
	}

	public static Long getLong(ResultSet rs, int index) throws SQLException {
		long value = rs.getLong(index);
		if (rs.wasNull()) {
			return null;
		}
		return new Long(value);
	}

	public static Long getLong(ResultSet rs, String fieldName) throws SQLException {
		long value = rs.getLong(fieldName);
		if (rs.wasNull()) {
			return null;
		}
		return new Long(value);
	}

	public static void setLong(PreparedStatement pstmt, int index, Long val) throws SQLException {
		if (val != null) {
			pstmt.setLong(index, val.longValue());
		} else {
			pstmt.setNull(index, Types.BIGINT);
		}
	}

	public static Float getFloat(ResultSet rs, int index) throws SQLException {
		float value = rs.getFloat(index);
		if (rs.wasNull()) {
			return null;
		}
		return new Float(value);
	}

	public static Currency getCurrency(ResultSet rs, int index) throws SQLException {
		BigDecimal value = rs.getBigDecimal(index);
		if (rs.wasNull()) {
			return null;
		}
		return new DefaultCurrency(value);
	}

	public static Integer getCurrentId(Connection conn, String sequence) throws SQLException {
		Integer result = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("select currval(?)");
			int index = 1;
			setString(stmt, index++, sequence);
			rs = stmt.executeQuery();
			if (rs.next()) {
				index = 1;
				result = getInt(rs, index++);
			}
			return result;
		} finally {
			close(rs);
			close(stmt);
		}
	}
	
	public static Integer getCurrentId(Connection conn, String table, String column) throws SQLException {
		if (column == null) {
			column = "id";
		}
		return getCurrentId(conn, table + "_" + column + "_seq");
	}
}
