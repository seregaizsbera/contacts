package su.sergey.contacts.codegen.daogen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.util.*;


/**
 * SelectMethodGenerator
 * 
 * @author Сергей Богданов
 */
public class SelectMethodGenerator extends Broadcaster {
    private StringBuffer method;
    private SelectSQLGenerator sqlGenerator;
    private SelectSetGenerator setGenerator;
    private Table currentTable;
    private TypeListener typeListener;
    private String dtoPackage;
    private String daoExceptionClassName;

    public SelectMethodGenerator(TypeListener typeListener, String dtoPackage, String daoExceptionClassName) {
    	this.typeListener = typeListener;
    	this.dtoPackage = dtoPackage;
    	this.daoExceptionClassName = daoExceptionClassName;
        method = new StringBuffer();
        sqlGenerator = new SelectSQLGenerator();
        setGenerator = new SelectSetGenerator();
        addListener(sqlGenerator);
        addListener(setGenerator);
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        currentTable = table;
        super.startTable(table);
    }

    public void endTable() {
        super.endTable();
        String data = typeListener.type(dtoPackage + "." + HelperFactory.getHelper().getDataClassName(currentTable));
        String handle = typeListener.type(dtoPackage + "." + HelperFactory.getHelper().getHandleClassName(currentTable));
        String daoException = typeListener.type(daoExceptionClassName);
        String connection = typeListener.type(Connection.class);
        String preparedStatement = typeListener.type(PreparedStatement.class);
        String resultSet = typeListener.type(ResultSet.class);
        String string = typeListener.type(String.class);
        String sqlException = typeListener.type(SQLException.class);
        method.append("    public ").append(data);
        method.append(" find(").append(handle).append(" handle) {\n");
        method.append("        ").append(connection).append(" conn = null;\n");
        method.append("        ").append(preparedStatement).append(" pstmt = null;\n");
        method.append("        ").append(resultSet).append(" rs = null;\n");
        method.append("        ").append(string).append(" query = \"").append(sqlGenerator.getSql()).append("\";\n");
        method.append("        ").append(data).append(" result = null;\n");
        method.append("        try {\n");
        method.append("            conn = getConnection();\n");
        method.append("            pstmt = conn.prepareStatement(query);\n");
        method.append("            int index = 1;\n");
        method.append(setGenerator.getSets());
        method.append("            rs = pstmt.executeQuery();\n");
        method.append("            if (rs.next()) {\n");
        method.append("                result = new ").append(data).append("();\n");
        method.append("                populate(result, rs, 1);\n");
        method.append("            }\n");
        method.append("        } catch (").append(sqlException).append(" e) {\n");
        method.append("            throw new ").append(daoException).append("(e);\n");
        method.append("        } finally {\n");
        method.append("            close(rs);\n");
        method.append("            close(pstmt);\n");
        method.append("            close(conn);\n");
        method.append("        }\n");
        method.append("        return result;\n");
        method.append("    }\n");
    }
    
    public String getMethod() {
        return method.toString();
    }
}
