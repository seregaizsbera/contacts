package su.sergey.contacts.codegen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author: óÅÒÇÅÊ âÏÇÄÁÎÏ×
 */
public class PropertiesGenerator {
    private static final String SCHEMA_PATTERN = null;
    private static final String TABLE_PATTERN = "%";
    private static final String HEADER_COLUMNS[] = {"?", "Ïâÿþâïêå", "Æêò", "Ìðîîåïæâôêë"};
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_NAME = "jdbc:postgresql:contacts";
    private static final String USER_LOGIN = "sergey";
    private static final String USER_PASSWORD = "changeit";
    private static final Map className;
    
    static {
        className = new HashMap();
        className.put("persons", "Person");
    }
    
    private Writer dataFile = null;

    public static void main(String args[]) {
        try {
            PropertiesGenerator propertiesGenerator = new PropertiesGenerator();
            propertiesGenerator.makeDocs(SCHEMA_PATTERN, TABLE_PATTERN);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public PropertiesGenerator() {
    	loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            throw new RuntimeException(e.getMessage());
        }
    }
    
    private static void close(Statement stmt) {
    	try {
    		if(stmt != null) {
    			stmt.close();
    		}
    	}
    	catch(SQLException e) {
    	}    		
    }

    private static void close(ResultSet rs) {
    	try {
    		if(rs != null) {
    			rs.close();
    		}
    	}
    	catch(SQLException e) {
    	}    		
    }

    private void close(Connection conn) {
    	try {
    		if(conn != null) {
    			conn.close();
    		}
    	}
    	catch(SQLException e) {
    	}    		
    }
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_NAME, USER_LOGIN, USER_PASSWORD);
    }
    
    private static String getQualifiedTableName(String schema, String table) {
    	if(schema == null || schema.equals("")) {
    		return table;
    	}
    	return schema + "." + table;
    }
    
    private static final void writeClassStart(Writer out, String schema, String table) throws IOException {
        out.write("package com;\n\n");
        out.write("public class ");
        out.write(getDataName(schema, table));
        out.write(" {\n");
    }

    private static final String getDAOName(String schema, String table) {
        return (String)className.get(getQualifiedTableName(schema, table) + "DAO");
    }

    private static final String getDataName(String schema, String table) {
        return (String)className.get(getQualifiedTableName(schema, table)) + "Data";
    }

    private static final void writeClassEnd(Writer out, String schema, String table) throws IOException {
        out.write("}\n");
    }

    private void processRowFromTablesCursor(ResultSet rs) throws SQLException, IOException {
        int index = 1;
        String schema = rs.getString("TABLE_SCHEM");
        if(schema != null) {
        	schema = schema.trim();
        	if(schema.equals("")) {
        		schema = null;
        	}
        }
        String table = rs.getString("TABLE_NAME");
        String remarks = rs.getString("REMARKS");
        if(className.containsKey(getQualifiedTableName(schema, table))) {
	        System.err.println("schema: [" + ((schema == null) ? "null" : schema) + "] table: [" + table + "]");
            dataFile = new BufferedWriter(new FileWriter(getDataName(schema, table) + ".java"));
            writeClassStart(dataFile, schema, table);
            processTable(schema, table);
            writeClassEnd(dataFile, schema, table);
            dataFile.close();
        }
    }

    private void processTable(String schema, String table) throws SQLException, IOException {
    	Connection conn = null;
    	ResultSet rs = null;
        StringBuffer fields = new StringBuffer();
        StringBuffer methods = new StringBuffer();
        Map primaryKeyInfo = getPrimaryKeyInfo(schema, table);
    	try {
    		conn = getConnection();
	        rs = conn.getMetaData().getColumns(null, schema, table, "%");
	        while (rs.next()) {
	            processRowFromColumnsCursor(rs, fields, methods, primaryKeyInfo);
	        }
    	}
    	finally {
	        close(rs);
	        close(conn);
    	}
        writeDataClassContent(fields, methods);
    }

    private void writeDataClassContent(StringBuffer fields, StringBuffer methods) throws IOException {
        dataFile.write(fields.toString());
        dataFile.write(methods.toString());
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
    
    private void processRowFromColumnsCursor(ResultSet rs, StringBuffer fields, StringBuffer methods, Map primaryKeyInfo) throws SQLException {
        String columnName = rs.getString("COLUMN_NAME");
        int columnNumber = rs.getInt("ORDINAL_POSITION");
        String type = rs.getString("TYPE_NAME");
        int length = rs.getInt("COLUMN_SIZE");
        int scale = rs.getInt("DECIMAL_DIGITS");
        boolean nulls = (rs.getString("IS_NULLABLE").equals("YES")) ? true : false;
        Integer keySeq = (Integer)primaryKeyInfo.get(columnName);
        int keyseq = (keySeq == null) ? 0 : keySeq.intValue();
        String remarks = rs.getString("REMARKS");
        Class c = getJavaType(type, length, scale, nulls, keyseq);
        writeMember(fields, c, columnName);
        writeAccesors(methods, c, columnName);
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
    
    public static final void writeMember(StringBuffer fields, Class c, String columnName) {
        fields.append("    private ").append(c.getName()).append(" _").append(columnName).append(";\n");
    }

    public static final void writeAccesors(StringBuffer methods, Class c, String columnName) {
        methods.append("\n    public ").append(c.getName()).append(" get_").append(columnName).append("() {\n");
        methods.append("        return ").append("_").append(columnName).append(";\n    }\n\n");
        methods.append("    public void ").append(" set_").append(columnName).append("(").
                append(c.getName()).append(" ").append(columnName).append(") {\n");
        methods.append("        this.").append("_").append(columnName).append(" =").
                    append(" ").append(columnName).append(";\n    }\n");
    }

    public static final Class getJavaType(String type, int length, int scale, boolean nulls, int keyseq) {
        return String.class;
    }

    public void makeDocs(String schemaTemplate, String tableTemplate) throws SQLException, IOException {
    	Connection conn = null;
        try {
            conn = getConnection();
            StringTokenizer st = new StringTokenizer((schemaTemplate) == null ? " " : schemaTemplate, ",");
            while(st.hasMoreTokens()) {
                generateTableDocs(st.nextToken(), tableTemplate);
            }
        }
        finally {
            close(conn);
        }
    }

    public void generateTableDocs(String schemaTemplate, String tableTemplate) throws SQLException, IOException {
    	if(schemaTemplate != null) {
    		schemaTemplate = schemaTemplate.trim();
    		if(schemaTemplate.equals("")) {
    			schemaTemplate = null;
    		}
    	}
    	String types[] = {"TABLE"};
    	Connection conn = null;
    	ResultSet rs = null;
    	try {
    		conn = getConnection();
	        rs = conn.getMetaData().getTables(null, schemaTemplate, tableTemplate, types);
	        while(rs.next()) {
	            processRowFromTablesCursor(rs);
	        }
        }
        finally {
	        close(rs);
	        close(conn);
    	}
    }
}
