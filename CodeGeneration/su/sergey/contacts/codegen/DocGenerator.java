package su.sergey.contacts.codegen;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * @author: Сергей Богданов
 * @version: 1.0
 */
public class DocGenerator {
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_NAME = "jdbc:postgresql:contacts";
    private static final String USER_LOGIN = "sergey";
    private static final String USER_PASSWORD = "changeit";
    private static final String SCHEMA_PATTERN = null;
    private static final String TABLE_PATTERN = "%";
    private static final String FILE_NAME = "table_desc.html";
    private static final String HEADER_COLUMNS[] = {"?", "Название", "Тип", "Комментарий"};

    private StringBuffer result;

    public static void main(String args[]) {
        try {
            DocGenerator docGenerator = new DocGenerator();
            docGenerator.makeDocs(SCHEMA_PATTERN, TABLE_PATTERN, FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DocGenerator() {
    	try {
	    	Class.forName(DATABASE_DRIVER);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
        result = new StringBuffer();
    }

    public void makeDocs(String schemaTemplate, String tableTemplate, String fileName)
		    throws SQLException, IOException {
	    if(schemaTemplate == null) {
	    	schemaTemplate = " ";
	    }
	    result.append(makeHtmlStart());
	    StringTokenizer st = new StringTokenizer(schemaTemplate, ",");
	    while (st.hasMoreTokens()) {
	        generateTableDocs(st.nextToken(), tableTemplate);
	    }
	    result.append(makeHtmlEnd());
        saveFile(fileName);
    }
    
    private void close(Connection connection) {
    	try {
	    	if (connection != null) {
	    		connection.close();
	    	}
    	} catch (SQLException e) {}
    }

    private void close(Statement statement) {
    	try {
	    	if (statement != null) {
	    		statement.close();
	    	}
    	} catch (SQLException e) {}
    }

    private void close(ResultSet resultSet) {
    	try {
	    	if (resultSet != null) {
	    		resultSet.close();
	    	}
    	} catch (SQLException e) {}
    }

    public void generateTableDocs(String schemaTemplate, String tableTemplate) throws SQLException {
    	if (schemaTemplate != null && schemaTemplate.trim().equals("")) {
    		schemaTemplate = null;
    	}
        System.err.println("generateTableDocs st=" + ((schemaTemplate == null) ? "null" : schemaTemplate));
        Connection connection = null;
        ResultSet rs = null;
        String types[] = {"TABLE"};
        try {
        	connection = getConnection();
	        rs = connection.getMetaData().getTables(null, schemaTemplate, tableTemplate, types);
	        while (rs.next()) {
	            processRowFromTablesCursor(rs);
	        }
        } finally {
	        close(rs);
	        close(connection);
        }
    }

    private void saveFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        try {
            stream.write(result.toString().getBytes());
        } finally {
            stream.close();
        }
    }

    private static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DATABASE_NAME, USER_LOGIN, USER_PASSWORD);
        return conn;
    }
    
    private void processRowFromTablesCursor(ResultSet rs) throws SQLException {
        String schema = rs.getString("TABLE_SCHEM");
        if (schema != null && schema.trim().equals("")) {
        	schema = null;
        }
        String table = rs.getString("TABLE_NAME");
        String remarks = rs.getString("REMARKS");
        result.append(makeHtmlTableTitle(schema, table, remarks));
        processTable(schema, table);
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
    	}
    	finally {
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
    	}
    	finally {
    		close(rs);
    		close(statement);
    		close(connection);
    	}
    	return result;
    }
    
    private void processTable(String schema, String table) throws SQLException {
        result.append(makeHtmlTableStart());
        result.append(makeHtmlTableHeader());
        Connection connection = null;
        ResultSet resultSet = null;
        Map primaryKeyInfo = getPrimaryKeyInfo(schema, table);
        Map defaultValueInfo = getDefaultValueInfo(schema, table);
        try {
        	connection = getConnection();
        	resultSet = connection.getMetaData().getColumns(null, schema, table, "%");
	        while (resultSet.next()) {
	            processRowFromColumnsCursor(resultSet, primaryKeyInfo, defaultValueInfo);
	        }
        } finally {
	        close(resultSet);
	        close(connection);
        }
        result.append(makeHtmlTableEnd());
    }

    private void processRowFromColumnsCursor(ResultSet rs, Map primaryKeyInfo, Map defaultValueInfo) throws SQLException {
        String columnName = rs.getString("COLUMN_NAME");
        int columnNumber = rs.getInt("ORDINAL_POSITION");
        String type = rs.getString("TYPE_NAME");
        int length = rs.getInt("COLUMN_SIZE");
        int scale = rs.getInt("DECIMAL_DIGITS");
        boolean nulls = (rs.getString("IS_NULLABLE").equals("YES")) ? true : false;
        Integer keySeq = (Integer)primaryKeyInfo.get(columnName);
        int keyseq = (keySeq == null) ? 0 : keySeq.intValue();
        String remarks = rs.getString("REMARKS");
        String defaultValue = (String)defaultValueInfo.get(columnName); //rs.getString("COLUMN_DEF");
        boolean generated = defaultValue != null;
        String columns[] = new String[HEADER_COLUMNS.length];
        int columnsIndex = 0;
        columns[columnsIndex++] = (columnNumber) + ".";
        columns[columnsIndex++] = columnName;
        columns[columnsIndex++] = makeColumnTypeDescription(type, length, scale, nulls, keyseq);
        columns[columnsIndex++] = (remarks == null) ? "&nbsp;" : remarks;
        result.append(makeHtmlTableRow(columns));
    }

    public static final String TYPE_DECIMAL = "DECIMAL";
    public static final String TYPE_CHARACTER = "CHARACTER";
    public static final String TYPE_CHAR = "CHAR";

    private String makeColumnTypeDescription(String type, int length, int scale, boolean nulls, int keyseq) {
        String result = "";
        if (type.equals(TYPE_CHARACTER)) {
            result += TYPE_CHAR;
        } else {
            result += type;
        }
        if (type.equals(TYPE_DECIMAL) || type.equals(TYPE_CHARACTER)) {
            result += "(" + length;
            if (scale != 0) {
                result += ", " + scale;
            }
            result += ")";
        }
        result += " ";
        if (!nulls) {
            result += "NOT NULL";
        }
        if (keyseq != 0) {
            result += ", PK";
        }
        return result;
    }

    private String makeHtmlTableRow(String[] columns) {
        StringBuffer result = new StringBuffer("<tr>\n");
        for (int i = 0; i < columns.length; i++) {
            result.append("  <td>" + columns[i] + "</td>\n");
        }
        result.append("</tr>\n");
        return result.toString();
    }
    
    private String makeHtmlTableHeader() {
        StringBuffer result = new StringBuffer("<tr>\n");
        for (int i = 0; i < HEADER_COLUMNS.length; i++) {
            result.append("  <th>" + HEADER_COLUMNS[i] + "</th>\n");
        }
        result.append("</tr>\n");
        return result.toString();
    }
    
    private String getQualifiedTableName(String schema, String table) {
    	if (schema == null) {
    		return table;
    	}
    	return schema + "." + table;
    }
    
    private String makeHtmlTableTitle(String schemaName, String tableName, String tableDescription) {
        StringBuffer result = new StringBuffer("<p>");
        result.append("<b>Таблица " + getQualifiedTableName(schemaName, tableName) + "</b>\n<p>\n");
        result.append(tableName + " - " + ((tableDescription == null) ? "" : tableDescription) + "\n<p>\n");
        return result.toString();
    }
    
    private String makeHtmlTableStart() {
        return "<table border=\"1\">\n";
    }
    
    private String makeHtmlTableEnd() {
        return "</table>\n";
    }
    
    private String makeHtmlStart() {
    	return "<html><head><title>База данных " + DATABASE_NAME + "</title></head><body>\n";
    }
    
    private String makeHtmlEnd() {
    	return "</body></html>\n";
    }
}
