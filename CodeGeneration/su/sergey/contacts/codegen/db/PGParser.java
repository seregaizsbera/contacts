package su.sergey.contacts.codegen.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * PGParser
 * 
 * @author Сергей Богданов
 */
public class PGParser {
    private String databaseName;
    private String userLogin;
    private String userPassword;
    private TableListener tableListener = null;
    private Map parsedTables;

    public PGParser(String databaseName, String userLogin, String userPassword, TableListener tableListener) {
        this.databaseName = databaseName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.tableListener = tableListener;
        parsedTables = new HashMap();
    }
    
    private void processRowFromTablesCursor(ResultSet rs) throws SQLException {
        int index = 1;
        String schema = rs.getString("TABLE_SCHEM");
      	if(schema != null && schema.trim().equals("")) {
      		schema = null;
       	}
        String table = rs.getString("TABLE_NAME");
        String remarks = rs.getString("REMARKS");
        Table theTable = new Table(schema, table, remarks);
        if (parsedTables.containsKey(theTable.getQualifiedName())) {
        	return;
        }
        parsedTables.put(theTable.getQualifiedName(), Boolean.TRUE);
        System.err.println(theTable);
        tableListener.startTable(theTable);
        processTable(theTable);
        tableListener.endTable();
    }

    private void processTable(Table currentTable) throws SQLException {
    	String schema = currentTable.getSchema();
    	String table = currentTable.getTable();
    	Map primaryKeyInfo = getPrimaryKeyInfo(schema, table);
    	Map defaultValueInfo = getDefaultValueInfo(schema, table);
    	Connection conn = getConnection();
        ResultSet rs = null;
        try {
            rs = conn.getMetaData().getColumns(null, schema, table, "%");
            while(rs.next()) {
                processRowFromColumnsCursor(currentTable, rs, primaryKeyInfo, defaultValueInfo);
            }
        } finally {
            close(rs);
            close(conn);
        }
    }
    
    private Map getPrimaryKeyInfo(String schema, String table) throws SQLException {
    	Map result = new HashMap();
    	Connection connection = getConnection();
    	ResultSet rs = null;
    	try {
    		rs = connection.getMetaData().getPrimaryKeys(null, schema, table);
    		while(rs.next()) {
    			String columnName = rs.getString("COLUMN_NAME");
    			int keySeq = rs.getInt("KEY_SEQ");
    			result.put(columnName, new Integer(keySeq));
    		}
    	} finally {
    		close(rs);
    		close(connection);
    	}
    	return result;
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
    			String columnName = rs.getString(index++);
    			String defaultValue = rs.getString(index++);
    			result.put(columnName, defaultValue);
    		}
    	} finally {
    		close(rs);
    		close(statement);
    		close(connection);
    	}
    	return result;
    }

    private void processRowFromColumnsCursor(Table currentTable, ResultSet rs, Map primaryKeyInfo, Map defaultValueInfo) throws SQLException {
        String columnName = rs.getString("COLUMN_NAME");
        int columnNumber = rs.getInt("ORDINAL_POSITION");
        String type = rs.getString("TYPE_NAME");
        if (type.equalsIgnoreCase("name")) {
        	type = type;
        }
        int length = rs.getInt("COLUMN_SIZE");
        int scale = rs.getInt("DECIMAL_DIGITS");
        boolean nulls = (rs.getString("IS_NULLABLE").equals("YES")) ? true : false;
        Integer keySeq = (Integer)primaryKeyInfo.get(columnName);
        int keyseq = (keySeq == null) ? 0 : keySeq.intValue();
        String remarks = rs.getString("REMARKS");
        String defaultValue = (String)defaultValueInfo.get(columnName);
        boolean generated = defaultValue != null;
        boolean identity = generated && defaultValue.indexOf("nextval") >= 0;
        tableListener.attribute(new Attribute(currentTable, columnName, columnNumber, type, length, scale, nulls, keyseq, remarks, generated, identity));
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseName, userLogin, userPassword);
    }
    
    protected void close(ResultSet rs) {
    	if(rs != null) {
    		try {
    			rs.close();
    		} catch(SQLException e) {}
    	}
    }

    protected void close(Statement statement) {
    	if(statement != null) {
    		try {
    			statement.close();
    		} catch(SQLException e) {}
    	}
    }

    protected void close(Connection connection) {
    	if(connection != null) {
    		try {
        		connection.close();
    		} catch(SQLException e) {}
    	}
    }

    public void start(String schemaTemplate, String tableTemplate) throws SQLException {
    	parsedTables.clear();
    	if (schemaTemplate == null) {
    		schemaTemplate = " ";
    	}
	    for (StringTokenizer i = new StringTokenizer(schemaTemplate, ","); i.hasMoreTokens();) {
	    	String schemas = i.nextToken();
	    	for (StringTokenizer j = new StringTokenizer(tableTemplate, ","); j.hasMoreTokens();) {
	    		String tables = j.nextToken();
			    parseTable(schemas, tables);
	    	}
	    }
    }

	private void parseTable(String schemas, String tables) throws java.sql.SQLException {
		Connection conn = getConnection();
		ResultSet rs = null;
		try {
			String types[] = {"TABLE"};
		    rs = conn.getMetaData().getTables(null, schemas, tables, types);
		    while (rs.next()) {
		        processRowFromTablesCursor(rs);
		    }
		} finally {
		    close(rs);
		    close(conn);
		}
	}
	    
    public TableListener getTableListener() {
        return tableListener;
    }

    public void setTableListener(TableListener tableListener) {
        this.tableListener = tableListener;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
