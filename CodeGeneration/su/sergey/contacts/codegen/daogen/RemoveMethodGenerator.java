package su.sergey.contacts.codegen.daogen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.impl.Broadcaster;

/**
 * RemoveMethodGenerator
 * 
 * @author Сергей Богданов
 */
public class RemoveMethodGenerator extends Broadcaster {
    private StringBuffer method;
    private RemoveSQLGenerator sqlGenerator;
    private RemoveSetGenerator setGenerator;
    private Table currentTable;
    private TypeListener typeListener;
    private String dtoPackage;
    private String daoExceptionClassName;

    public RemoveMethodGenerator(TypeListener typeListener, String dtoPackage, String daoExceptionClassName) {
    	this.typeListener = typeListener;
    	this.dtoPackage = dtoPackage;
    	this.daoExceptionClassName = daoExceptionClassName;
        method = new StringBuffer();
        sqlGenerator = new RemoveSQLGenerator();
        setGenerator = new RemoveSetGenerator();
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
        String handle = typeListener.type(dtoPackage + "." + Helper.getHandleClassName(currentTable));
        String daoException = typeListener.type(daoExceptionClassName);
        String connection = typeListener.type(Connection.class);
        String preparedStatement = typeListener.type(PreparedStatement.class);
        String string = typeListener.type(String.class);
        String sqlException = typeListener.type(SQLException.class);
        method.append("    public void remove(").append(handle).append(" handle)");
        method.append(" throws ").append(daoException).append(" {\n");
        method.append("        ").append(connection).append(" conn = null;\n");
        method.append("        ").append(preparedStatement).append(" pstmt = null;\n");
        method.append("        ").append(string).append(" query = \"").append(sqlGenerator.getSql()).append("\";\n");
        method.append("        try {\n");
        method.append("            conn = getConnection();\n");
        method.append("            pstmt = conn.prepareStatement(query);\n");
        method.append("            int index = 1;\n");
        method.append(setGenerator.getSets());
        method.append("            pstmt.executeUpdate();\n");
        method.append("        } catch (").append(sqlException).append(" e) {\n");
        method.append("            throw new ").append(daoException).append("(e);\n");
        method.append("        } finally {\n");
        method.append("            close(pstmt);\n");
        method.append("            close(conn);\n");
        method.append("        }\n");
        method.append("    }\n");
    }

    public String getMethod() {
        return method.toString();
    }
}
