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
import java.util.TreeMap;

import su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.directory.valueobjects.impl.DefaultDirectoryColumnMetadata;
import su.sergey.contacts.directory.valueobjects.impl.DefaultDirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.impl.DefaultDirectoryRecord;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectorySearchParameters;
import su.sergey.contacts.util.dao.AbstractSearchDAO;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.DAOUtil;

/**
 * �������� ������ ������ �������, � ����� ���������� � �������� ������
 * 
 * @author ������ ��������
 */
public class FindDirectoryDAO extends AbstractSearchDAO {
    private static FindDirectoryDAO instance = null;

    /**
     * ������� ��������� DAO
     */
    private FindDirectoryDAO() {
    }

  
    /**
     * ���������� ������ � ��������
     * 
     * @param start ����� ������ �������
     * @param length ���������� ������
     * @return List ������ DirectoryMetadata
     */
    public List findDirectoryMetadata(DirectorySearchParameters searchParameters, int start, int length) {
        Connection conn = null;
        PreparedStatement statement = null;
        DatabaseMetaData dbMetaData = null;
        ResultSet rs = null;
        String tablePattern = getTablePattern(searchParameters);
        String schemaPattern = getSchemaPattern(searchParameters);
        String query = "SELECT a.relname AS TABLE_NAME"
	                       + ", b.description AS REMARKS"
			       + ", (CASE WHEN a.relhasrules THEN 'VIEW' ELSE 'TABLE' END) as TABLE_TYPE"
			       + " FROM pg_class AS a"
			       + " JOIN pg_description AS b ON a.oid = b.objoid"
			       + " WHERE a.relkind = 'r'"
			       + " AND a.relname NOT LIKE 'pg_%'"
			       + " AND a.relname LIKE ?"
			       + " ORDER BY a.relname";
        try {
            conn = getConnection();
            statement = conn.prepareStatement(query);
            int index = 1;
            setString(statement, index++, tablePattern);
            rs = statement.executeQuery();
            List result = new ArrayList();
            String tableName;
            String tableDescription;
            String tableType;
            Map tables = new TreeMap();
            for(int i = 1; rs.next() && i < (start + length); i++) {
            	if(i >= start) {
                  tableName = getString(rs, "TABLE_NAME");                  
                  tableDescription = getString(rs,"REMARKS");
                  tableType = getString(rs, "TABLE_TYPE");
                  tables.put(tableName, new String[]{tableDescription, tableType});
            	}
            }
            for(Iterator i = tables.keySet().iterator(); i.hasNext();) {
            	tableName = (String) i.next();
            	tableDescription = ((String[]) tables.get(tableName))[0];
            	tableType = ((String[]) tables.get(tableName))[1];
                result.add(new DefaultDirectoryMetadata(null, tableName, tableDescription,
                            findDirectoryColumnMetadata(conn, new DirectoryMetadataHandle(tableName)),
                            tableType.equals("VIEW")));
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(statement);
            close(conn);
        }
    }
    
    private String getTablePattern(DirectorySearchParameters searchParameters) {
    	String pattern = searchParameters.getTableName();
    	if (pattern == null || pattern.trim().length() == 0) {
    		return "%";
    	}
    	String result = DAOUtil.convertSearchString(pattern, needsLikeSearch(pattern));
    	return result;
    }

    private String getSchemaPattern(DirectorySearchParameters searchParameters) {
    	return null;
    }

    /**
     * ������������ ���������� ������ � �������
     * 
     * @return int ���������� ������
     */
    public int countDirectoryMetadata(DirectorySearchParameters searchParameters) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String tablePattern = getTablePattern(searchParameters);
        String schemaPattern = getSchemaPattern(searchParameters);
        String query = "SELECT a.relname"
		       + " FROM pg_class AS a"
		       + " WHERE a.relkind = 'r'"
		       + " AND a.relname NOT LIKE 'pg_%'"
		       + " AND a.relname LIKE ?";
        try {
            conn = getConnection();
            statement = conn.prepareStatement(query);
            int index = 1;
            setString(statement, index++, tablePattern);
            rs = statement.executeQuery();
            return rs.last() ? rs.getRow() : 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(rs);
            close(statement);
            close(conn);
        }
    }

    /**
     * ���������� ������ � �������
     * 
     * @param directoryMetadataHandle ���������, ���������� �������� �������
     * @return DirectoryMetadata ���������, ���������� ��� �������, ������ ��������
     */
    public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle) {
        Connection connection = null;
        try {
        	connection = getConnection();
         	return getDirectoryMetadata(connection, directoryMetadataHandle);
        } finally {
        	close(connection);
        }
    }
    
	private DirectoryMetadata getDirectoryMetadata(Connection connection, DirectoryMetadataHandle directoryMetadataHandle) throws DAOException {
		DirectoryMetadata result = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String query = "SELECT a.relname AS TABLE_NAME"
			               + ", b.description AS REMARKS"
				       + ", (CASE WHEN a.relhasrules THEN 'VIEW' ELSE 'TABLE' END) as TABLE_TYPE"
				       + " FROM pg_class AS a"
				       + " JOIN pg_description AS b ON a.oid = b.objoid"
				       + " WHERE a.relkind = 'r'"
				       + " AND a.relname = ?";
			statement = connection.prepareStatement(query);
			int index = 1;
			setString(statement, index++, directoryMetadataHandle.getTableName());
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String tableName = getString(resultSet, "TABLE_NAME");
				String tableDescription = getString(resultSet, "REMARKS");
				String tableType = getString(resultSet, "TABLE_TYPE");
				resultSet.close();
				result = new DefaultDirectoryMetadata(null, tableName, tableDescription, findDirectoryColumnMetadata(connection, directoryMetadataHandle), tableType.equals("VIEW"));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(statement);
			close(resultSet);
		}
		return result;
	}

    /**
     * ������������ ���������� ������� � �������
     * 
     * @param searchParameters ��������� ��� ������ �������
     * @return int ���������� ������� � �������
     */
    public int countDirectoryRecords(DirectoryRecordSearchParameters searchParameters) {
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
    public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {
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
    public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) {
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
    public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) {
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
    public void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {
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
     */
    public List findDirectoryRecords(DirectoryRecordSearchParameters searchParameters, int start, int length) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            DirectoryMetadata metadata = getDirectoryMetadata(connection, searchParameters.getDirectoryMetadataHandle());
            String query = "select " + (metadata.isReadOnly() ? "null AS oid" : "oid") + ", * from " + getTableName(searchParameters)
                       + getSearchStatementCondition(searchParameters.getParameters())
                       + " limit " + length + " offset " + (start - 1);
            stmt = connection.prepareStatement(query);
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
            close(connection);
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
    private DirectoryColumnMetadata[] findDirectoryColumnMetadata(Connection conn, DirectoryMetadataHandle handle) {
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
                setInt(pstmt, index, value == null ? null : new Integer(value));
                break;
            case Types.VARCHAR:
            case Types.CHAR:
                setString(pstmt, index, value);
                break;
            default:
                setString(pstmt, index, value);
        }
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
