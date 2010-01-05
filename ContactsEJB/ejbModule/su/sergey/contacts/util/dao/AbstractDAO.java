package su.sergey.contacts.util.dao;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

/**
 * Утилитарный класс для от котогорого удобно наследовать
 * DAO модули. Предоставляет сервис получения соединений к базе данных с
 * помощью {@link su.sergey.contacts.util.dao.ConnectionSource ConnectionSource}, которым конфигурируется в конструкторе.
 */
abstract public class AbstractDAO {
	private final ConnectionSource connectionSource;

	protected AbstractDAO(ConnectionSource connectionSource) {
		this.connectionSource = connectionSource;
	}

	protected Connection getConnection() {
		return connectionSource.getConnection();
	}

	protected Connection getConnection(String userName, String password) {
		return connectionSource.getConnection(userName, password);
	}

	protected static final String convertString(String s) {
		return DAOUtil.convertString(s);
	}

	protected static final void setString(PreparedStatement pstmt, int index, String val) throws SQLException {
		DAOUtil.setString(pstmt, index, val);
	}

	protected static final void setBigDecimal(PreparedStatement pstmt, int index, BigDecimal val) throws SQLException {
		DAOUtil.setBigDecimal(pstmt, index, val);
	}

	protected static final void setInt(PreparedStatement pstmt, int index, Integer val) throws SQLException {
		DAOUtil.setInt(pstmt, index, val);
	}

	protected static final void setLong(PreparedStatement pstmt, int index, Long val) throws SQLException {
		DAOUtil.setLong(pstmt, index, val);
	}

	protected static final void setBoolean(PreparedStatement pstmt, int index, Boolean val) throws SQLException {
		DAOUtil.setBoolean(pstmt, index, val);
	}

	protected static final void setFloat(PreparedStatement pstmt, int index, Float val) throws SQLException {
		DAOUtil.setFloat(pstmt, index, val);
	}

	protected static final void setCurrency(PreparedStatement pstmt, int index, Currency val) throws SQLException {
		DAOUtil.setCurrency(pstmt, index, val);
	}

	protected static final void setStream(PreparedStatement pstmt, int index, InputStream val, int length) throws SQLException {
		DAOUtil.setStream(pstmt, index, val, length);
	}

	protected static final void setSerializedObject(PreparedStatement pstmt, int index, Serializable val) throws SQLException{
		DAOUtil.setSerializedObject(pstmt, index, val);
	}

	protected static final void setTimestamp(PreparedStatement pstmt, int index, java.util.Date val) throws SQLException {
		DAOUtil.setTimestamp(pstmt, index, val);
	}

	protected static final void setTime(PreparedStatement pstmt, int index, java.util.Date val) throws SQLException {
		DAOUtil.setTime(pstmt, index, val);
	}

	protected static final void setDate(PreparedStatement pstmt, int index, java.util.Date val) throws SQLException {
		DAOUtil.setDate(pstmt, index, val);
	}

	protected static final BigDecimal getBigDecimal(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getBigDecimal(rs, fieldName);
	}
	protected static final BigDecimal getBigDecimal(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getBigDecimal(rs, index);
	}
	protected static final java.util.Date getTimestamp(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getTimestamp(rs, fieldName);
	}

	protected static final java.util.Date getTimestamp(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getTimestamp(rs, index);
	}
	protected static final java.util.Date getTime(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getTime(rs, index);
	}

	protected static final java.util.Date getDate(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getDate(rs, index);
	}

	protected static final java.util.Date getDate(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getDate(rs, fieldName);
	}

	protected static final Serializable getSerializedObject(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getSerializedObject(rs, fieldName);
	}

	protected static final InputStream getStream(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getStream(rs, fieldName);
	}

	protected static final String getString(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getString(rs, fieldName);
	}

	protected static final String getString(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getString(rs, index);
	}

	protected static final String getCharString(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getCharString(rs, index);
	}

	protected static final String getCharString(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getCharString(rs, fieldName);
	}

	protected static final Integer getInt(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getInt(rs, index);
	}

	protected static final Integer getInt(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getInt(rs, fieldName);
	}

	protected static final Boolean getBoolean(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getBoolean(rs, index);
	}

	protected static final Boolean getBoolean(ResultSet rs, String fieldName) throws SQLException {
		return DAOUtil.getBoolean(rs, fieldName);
	}

	protected static final Long getLong(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getLong(rs, index);
	}

	protected static final Float getFloat(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getFloat(rs, index);
	}

	protected static final Currency getCurrency(ResultSet rs, int index) throws SQLException {
		return DAOUtil.getCurrency(rs, index);
	}

	protected void log(String str) {
		System.err.println(str);
	}

	protected void log(Exception e) {
		e.printStackTrace();
	}

	protected boolean close(ResultSet rs) {
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

	protected boolean close(Statement stmt) {
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
	
	protected boolean close(Connection conn) {
		try {
			connectionSource.close(conn);
			return true;
		} catch (DAOException e) {
				log("Exception closing connection: " + e);
				log(e);
			return false;
		}
	}

    protected static boolean needsLikeSearch(String s) {
    	boolean result = s.indexOf('*') >= 0
                         || s.indexOf('?') >= 0;
        return result;
    }
    
	protected Integer getCurrentId(Connection conn, String sequence) throws SQLException {
		return DAOUtil.getCurrentId(conn, sequence);
	}

	protected Integer getCurrentId(Connection conn, String table, String column) throws SQLException {
		return DAOUtil.getCurrentId(conn, table, column);
	}
}
