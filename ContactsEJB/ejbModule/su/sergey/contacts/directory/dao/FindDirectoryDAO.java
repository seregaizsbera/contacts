package su.sergey.contacts.directory.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.DAOUtil;
import su.sergey.contacts.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.valueobjects.impl.DefaultDirectoryColumnMetadata;
import su.sergey.contacts.valueobjects.impl.DefaultDirectoryMetadata;
import su.sergey.contacts.valueobjects.impl.DefaultDirectoryRecord;
import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

/**
 * �������� ������ ������ �������, � ����� ���������� � �������� ������
 * 
 * @author ������ ��������
 */
public class FindDirectoryDAO extends AbstractDAO {
    private static FindDirectoryDAO instance = null;

    /**
     * ������� ��������� DAO
     */
    private FindDirectoryDAO() {}

  
    /**
     * ���������� ������ � ��������
     * 
     * @param start ����� ������ �������
     * @param length ���������� ������
     * @return List ������ DirectoryMetadata
     * @throws DAOException ���� ��� ������� �������� � ����� ��������� ��������.
     */
    public List findDirectoryMetadata(int start, int length) throws DAOException {
        Connection conn = null;
        DatabaseMetaData dbMetaData = null;
        ResultSet rs = null;
        String types[] = {"TABLE"};
        try {
            conn = getConnection();
            dbMetaData = conn.getMetaData();
            rs = dbMetaData.getTables(null, null, "%", types);
            List result = new ArrayList();
            String tableName;
            String tableDescription;
            Map tables = new HashMap();
            for(int i = 1; rs. next() && i < (start + length); i++) {
            	if(i >= start) {
                  tableName = getString(rs, "TABLE_NAME");
                  tableDescription = getString(rs,"REMARKS");
                  tables.put(tableName, tableDescription);
            	}
            }
            for(Iterator i = tables.keySet().iterator(); i.hasNext();) {
            	tableName = (String) i.next();
            	tableDescription = (String)tables.get(tableName);
                result.add(new DefaultDirectoryMetadata(null, tableName, tableDescription,
                            findDirectoryColumnMetadata(conn, new DirectoryMetadataHandle(tableName))));
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(conn);
        }
    }

    /**
     * ������������ ���������� ������ � �������
     * 
     * @return int ���������� ������
     * @throws DAOException ���� ��� ������� �������� � ����� ��������� ��������.
     */
    public int countDirectoryMetadata() throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        String types[] = {"TABLE"};
        try {
            conn = getConnection();
            rs = conn.getMetaData().getTables(null, null, "%", types);
            return rs.last() ? rs.getRow() : 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(conn);
        }
    }

    /**
     * ���������� ������ � �������
     * 
     * @param directoryMetadataHandle ���������, ���������� �������� �������
     * @return DirectoryMetadata ���������, ���������� ��� �������, ������ ��������
     * @throws DAOException ���� ��� ������� �������� � ����� ��������� ��������.
     */
    public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle) throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            String tableDescription = getTableDescription(conn, null, directoryMetadataHandle.getTableName());
            return new DefaultDirectoryMetadata(null, directoryMetadataHandle.getTableName(), tableDescription,
                    findDirectoryColumnMetadata(conn, directoryMetadataHandle));
        } finally {
            close(conn);
        }
    }

    /**
     * ��������� ���������� �������.
     * @throws DAOException ���� ��� ������� �������� � ����� ��������� ��������.
     */
    public void updateDirectoryMetadata(DirectoryMetadataHandle handle, DirectoryMetadata directoryMetadata) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
	        String updateTableRemark = getUpdateTableRemarkStatement(handle.getTableName(), directoryMetadata.getDescription());
            stmt.addBatch(updateTableRemark);
            DirectoryColumnMetadata columnsMetaData[] = directoryMetadata.getColumnMetadata();
            for (int i = 0; i < columnsMetaData.length; i++) {
                String updateColumnRemark = getUpdateColumnRemarkStatement(handle.getTableName(), columnsMetaData[i]);
	            stmt.addBatch(updateColumnRemark);
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * ������������ ���������� ������� � �������
     * 
     * @param searchParameters ��������� ��� ������ �������
     * @return int ���������� ������� � �������
     * @throws DAOException ���� ��� ������� �������� � ����� ��������� ��������.
     */
    public int countDirectoryRecords(DirectoryRecordSearchParameters searchParameters) throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) from " + getTableName(searchParameters) +
                    getSearchStatementCondition(searchParameters.getParameters()));
            return rs.next() ? getInt(rs, 1).intValue() : 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }

    /**
     * ���������� ������� �������, ������ �� ����� ������� � �������� primary key
     * 
     * @param directoryRecordHandle ���������, ���������� ��� ������� � �������� primary key ������
     * @return DirectoryRecord ��������� ������
     */
    public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select oid, * from " + getTableName(directoryRecordHandle.getDirectoryMetadataHandle())
                       + " where oid=?";
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            int index = 1;
            setInt(stmt, index++, directoryRecordHandle.getOid());
            rs = stmt.executeQuery();
            int columnsCount = rs.getMetaData().getColumnCount();
            if (rs.next()) {
            	DefaultDirectoryRecord result = new DefaultDirectoryRecord();
            	populate(rs, result, 1, columnsCount - 1);
                return result;
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }

    /**
     * ��������� ������ �������.
     * 
     * @param directoryMetadataHandle ���������, ���������� �������� �������
     * @param DirectoryRecord ����� ������
     */
    public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = getUpdateRecordStatement(directoryRecordHandle);
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            setUpdateRecordStatement(pstmt, directoryRecordHandle, directoryRecord);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }
    
    /**
     * ��������� ����� ������ � �������.
     * 
     * @param directoryMetadataHandle ���������, ���������� ��� �������
     * @param DirectoryRecord ����� ������
     */
    public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String query = getAddRecordStatement(directoryMetadataHandle);
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            setAddRecordStatement(pstmt, directoryMetadataHandle, directoryRecord);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    /**
     * ������� ������ �������.
     * 
     * @param directoryRecordHandle ���������, ���������� ��� ������� � �������� ���������� ����� ������
     */
    public void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(getDeleteRecordStatement(directoryRecordHandle.getDirectoryMetadataHandle()));
            setDeleteRecordStatement(pstmt, directoryRecordHandle);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    /**
     * ���������� ������ ������� �������
     * 
     * @param searchParameters ��������� ��� ������ �������
     * @param start ����� ������ ������
     * @param length ���������� �������
     * @return List ������ �������
     * @throws DAOException ���� ��� ������� �������� � ����� ��������� ��������.
     */
    public List findDirectoryRecords(DirectoryRecordSearchParameters searchParameters, int start, int length) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "select oid, * from " + getTableName(searchParameters)
                       + getSearchStatementCondition(searchParameters.getParameters())
                       + " limit " + length + " offset " + (start - 1);
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            List result = new ArrayList();
            int columnsCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
            	DefaultDirectoryRecord record = new DefaultDirectoryRecord();
            	populate(rs, record, 1, columnsCount - 1);
                result.add(record);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    }

    private Map getDefaultValueInfo(String schema, String table) throws SQLException {
    	Map result = new HashMap();
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	ResultSet rs = null;
    	String sql = "select pg_attribute.attname"
                     + ", pg_attrdef.adsrc"
                     + " from pg_class"
    		         + " join pg_attrdef on pg_class.oid=pg_attrdef.adrelid"
    		         + " join pg_attribute on pg_attribute.attrelid=pg_class.oid and pg_attribute.attnum=pg_attrdef.adnum"
    		         + " where pg_class.relname=?";    	
    	try {
    		statement = connection.prepareStatement(sql);
    		int index = 1;
    		statement.setString(index++, table);
    		rs = statement.executeQuery();
    		while(rs.next()) {
    			index = 1;
    			String columnName = getString(rs, index++);
    			String defaultValue = getString(rs, index++);
    			result.put(columnName, defaultValue);
    		}
    	} finally {
    		close(rs);
    		close(statement);
    		close(connection);
    	}
    	return result;
    }
    
    /**
     * ���������� ������ DirectoryColumnMetadata, ���������� ��� ������� � ����������� � ����
     */
    private DirectoryColumnMetadata[] findDirectoryColumnMetadata(Connection conn, DirectoryMetadataHandle handle)
            throws DAOException {
        ResultSet rs = null;
        try {
        	Map defaultValueInfo = getDefaultValueInfo(null, handle.getTableName());
            rs = conn.getMetaData().getColumns(null, null, handle.getTableName(), "%");
            Collection columns = new ArrayList();
            while (rs.next()) {
            	String columnName = getString(rs, "COLUMN_NAME");
            	String remark = getString(rs, "REMARKS");
            	boolean isNullable = !getString(rs, "IS_NULLABLE").equals("NO");
            	int size = getInt(rs, "COLUMN_SIZE").intValue();
            	int type = getInt(rs, "DATA_TYPE").intValue();
            	String defaultValue = (String) defaultValueInfo.get(columnName);
            	boolean isGenerated = defaultValue != null;
            	columns.add(new DefaultDirectoryColumnMetadata(columnName, remark, size, type, isNullable, isGenerated));
            }
            int index = 0;
            DirectoryColumnMetadata result[] = (DirectoryColumnMetadata[]) columns.toArray(new DirectoryColumnMetadata[0]);
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
        }
    }

    /**
     * ���������� ����������� � �������
     */
    private String getTableDescription(Connection conn, String schemaTemplate, String tableTemplate) throws DAOException {
        ResultSet rs = null;
        String types[] = {"TABLE"};
        try {
            rs = conn.getMetaData().getTables(null, schemaTemplate, tableTemplate, types);
            return getRemark(rs);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
        }
    }

    /**
     * ���������� �������� primary key �� resultset (������ �������)
     */
    private String getPrimaryKeyValue(ResultSet rs) throws SQLException {
        return getString(rs, 1);
    }

    /**
     * ����� ����������� �� ResultSet
     */
    private String getRemark(ResultSet rs) throws SQLException {
        String result = "";
        if (rs.next()) {
            result = getString(rs, "REMARKS");
        }
        return result;
    }

    /**
     * ���������� ������ ��� ������� �� ���������� ������
     */
    private String getTableName(DirectoryRecordSearchParameters searchParameters) {
        String tableName = searchParameters.getDirectoryMetadataHandle().getTableName();
        return tableName;
    }

    /**
     * ���������� ������ ��� ������� �� ���������� ����������
     */
    private String getTableName(DirectoryMetadataHandle directoryMetadataHandle) {
        return directoryMetadataHandle.getTableName();
    }

    /**
     * ���������� ������ ��� ���������� ����������� � �������
     */
    private String getUpdateTableRemarkStatement(String tableName, String description) {
    	return "update pg_description set description='" + description +"'"
    	       + " where objoid=(select oid from pg_class where relname='" + tableName + "')";
    }

    /**
     * ���������� ������ ��� ���������� ������������ � �������� �������
     */
    private String getUpdateColumnRemarkStatement(String tableName, DirectoryColumnMetadata columnMetadata) {
    	String statement = "update pg_description set description="
    	                   + "'" + columnMetadata.getFullName() + "'"
    	                   + " where objoid=("
    	                   + "select a.oid from pg_attribute as a"
    	                   + " join pg_class as b on a.attrelid=b.oid"
    	                   + " where b.relname='" + tableName + "' and a.attname='" + columnMetadata.getDbColumnName() +"'"
    	                   + ")";
        return statement;
    }

    /**
     * ���������� ������ ��� ���������� ������ � �����������
     */
    private String getUpdateRecordStatement(DirectoryRecordHandle directoryRecordHandle) {
        DirectoryMetadata directoryMetadata = findDirectoryMetadata(directoryRecordHandle.getDirectoryMetadataHandle());
        DirectoryColumnMetadata[] columns = directoryMetadata.getColumnMetadata();
        String statement = "update " + getTableName(directoryRecordHandle.getDirectoryMetadataHandle()) + " set ";
        Collection valueableColumns = new ArrayList();
        for (int i = 0; i < columns.length; i++) {
        	if (!columns[i].isGenerated()) {
	        	valueableColumns.add(columns[i]);
        	}
        }
        for (Iterator i = valueableColumns.iterator(); i.hasNext();) {
        	DirectoryColumnMetadata column = (DirectoryColumnMetadata) i.next();
            statement += column.getDbColumnName() + "=?";
            if (i.hasNext()) {
                statement += ", ";
            }
        }
        statement += " where oid=?";
        return statement;
    }

    /**
     * ������������� �������� � ������� ��� ���������� ������ � �����������
     */
    private void setUpdateRecordStatement(PreparedStatement pstmt, DirectoryRecordHandle directoryRecordHandle,
                                                 DirectoryRecord directoryRecord) throws SQLException {
        DirectoryMetadata directoryMetadata = findDirectoryMetadata(directoryRecordHandle.getDirectoryMetadataHandle());
        DirectoryColumnMetadata[] columns = directoryMetadata.getColumnMetadata();
        int index = 1;
        for (int i = 0; i < columns.length; i++) {
        	if (!columns[i].isGenerated()) {
	            setStatementValue(pstmt, directoryRecord.getValues()[i], columns[i].getType(), index++);
        	}
        }
        setInt(pstmt, index++, directoryRecordHandle.getOid());
    }

    /**
     * ���������� ������ ��� ���������� ������ � �������
     */
    private String getAddRecordStatement(DirectoryMetadataHandle directoryMetadataHandle) {
        DirectoryMetadata directoryMetadata = findDirectoryMetadata(directoryMetadataHandle);
        DirectoryColumnMetadata[] columns = directoryMetadata.getColumnMetadata();
        Collection valueableColumns = new ArrayList();
        StringBuffer values = new StringBuffer();
        StringBuffer questions = new StringBuffer();
        for (int i = 0; i < columns.length; i++) {
        	if (!columns[i].isGenerated()) {
	        	valueableColumns.add(columns[i]);
        	}
        }
        for (Iterator i = valueableColumns.iterator(); i.hasNext();) {
        	DirectoryColumnMetadata column = (DirectoryColumnMetadata) i.next();
        	values.append(column.getDbColumnName());
        	questions.append("?");
            if (i.hasNext()) {
            	values.append(", ");
            	questions.append(", ");
            }
        }
        String result = "insert into " + getTableName(directoryMetadataHandle)
                        + " (" + values.toString() + ") values"
                        + " (" + questions.toString() +")";
        return result;
    }


    /**
     * ������������� �������� � prepared statement ��� add ������ � �����������
     */
    private void setAddRecordStatement(PreparedStatement pstmt, DirectoryMetadataHandle directoryMetadataHandle,
                                                 DirectoryRecord directoryRecord) throws SQLException {
        DirectoryMetadata directoryMetadata = findDirectoryMetadata(directoryMetadataHandle);
        DirectoryColumnMetadata[] columns = directoryMetadata.getColumnMetadata();
        int index = 1;
        for (int i = 0; i < columns.length; i++) {
        	if (!columns[i].isGenerated()) {
	            setStatementValue(pstmt, directoryRecord.getValues()[i], columns[i].getType(), index++);
        	}
        }
    }

    /**
     * ���������� ������ ��� �������� ������ �� �������
     */
    private String getDeleteRecordStatement(DirectoryMetadataHandle directoryMetadataHandle) {
        DirectoryMetadata directoryMetadata = findDirectoryMetadata(directoryMetadataHandle);
        String statement = "delete from " + getTableName(directoryMetadataHandle) + " where oid=?";
        return statement;
    }

    /**
     * ������������� �������� � prepared statement ��� �������� ������ �� �������
     */
    private void setDeleteRecordStatement(PreparedStatement pstmt, DirectoryRecordHandle directoryRecordHandle) throws SQLException {
        DirectoryMetadata directoryMetadata = findDirectoryMetadata(directoryRecordHandle.getDirectoryMetadataHandle());
        setInt(pstmt, 1, directoryRecordHandle.getOid());
    }

    /**
     * ���������� ������� ��� Search Statement
     */
    private String getSearchStatementCondition(Properties parameters) {
        String columnName;
        String statement = "";
        Enumeration propertyNames = parameters.propertyNames();
        if (propertyNames.hasMoreElements()) {
            statement += " where ";
        }
        for (Enumeration e = propertyNames; e.hasMoreElements(); ) {
            columnName = (String)e.nextElement();
            if (parameters.get(columnName) instanceof Integer) {
                statement += columnName + "=" + parameters.get(columnName);
            } else {
                if (needsLikeSearch((String)parameters.get(columnName))) {
                    statement += "trim(text(" + columnName + ")) like \'" +
                            DAOUtil.convertSearchString((String)parameters.get(columnName), true) + "\'";
                } else {
                    statement += "text(" + columnName + ") = \'" +
                            DAOUtil.convertSearchString((String)parameters.get(columnName), false) + "\'";
                }
            }
            if (e.hasMoreElements()) {
                statement += " and ";
            }
        }
        return statement;
    }

    /**
     * ������������� �������� � prepared statement � ����������� �� �� ����
     */
    private void setStatementValue(PreparedStatement pstmt, String value, int type, int index) throws SQLException {
        switch (type) {
            case Types.SMALLINT:
            case Types.INTEGER:
                setInt(pstmt, index, new Integer(value));
                break;
            case Types.VARCHAR:
            case Types.CHAR:
                setString(pstmt, index, value);
                break;
            default:
                setString(pstmt, index, value);
        }
    }

    private static boolean needsLikeSearch(String s) {
        return ((s.indexOf('*') != -1) || (s.indexOf('?') != -1));
    }
    
    private int populate(ResultSet rs, DefaultDirectoryRecord record, int startIndex, int valuesCount)
		    throws SQLException {
    	int index = startIndex;
        String[] values = new String[valuesCount];
    	Integer oid = getInt(rs, index++);
        for (int i = 0; i < values.length; i++) {
            values[i] = getCharString(rs, index++);
        }
        record.setOid(oid);
        record.setValues(values);
        return index;
    }

    /**
     * �������� ��������� ������ FindDirectoryDAO
     */
    public static final FindDirectoryDAO getInstance() {
    	if (instance == null) {
    		instance = new FindDirectoryDAO();
    	}
        return instance;
    }
}
