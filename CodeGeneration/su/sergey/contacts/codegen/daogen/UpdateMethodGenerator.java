package su.sergey.contacts.codegen.daogen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.util.*;


/**
 * UpdateMethodGenerator
 * 
 * @author Сергей Богданов
 */
public class UpdateMethodGenerator extends Broadcaster {
    private StringBuffer method;
    private UpdateSQLGenerator sqlGenerator;
    private UpdateSetGenerator setGenerator;
    private Table currentTable;
    private TypeListener typeListener;
    private String dtoPackage;
    private String daoExceptionClassName;
    private boolean empty;
    
    public UpdateMethodGenerator(TypeListener typeListener, String dtoPackage, String daoExceptionClassName) {
    	this.typeListener = typeListener;
    	this.dtoPackage = dtoPackage;
    	this.daoExceptionClassName = daoExceptionClassName;
        method = new StringBuffer();
        sqlGenerator = new UpdateSQLGenerator();
        setGenerator = new UpdateSetGenerator();
        empty = true;
        addListener(sqlGenerator);
        addListener(setGenerator);
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        currentTable = table;
        empty = true;
        super.startTable(table);
    }

    public void endTable() {
        super.endTable();
        String updateInfo = dtoPackage + "." + HelperFactory.getHelper().getUpdateInfoClassName(currentTable);
        if (!empty) {
        	updateInfo = typeListener.type(updateInfo);
        }
        String handle = typeListener.type(dtoPackage + "." + HelperFactory.getHelper().getHandleClassName(currentTable));
        String daoException = typeListener.type(daoExceptionClassName);
        String connection = typeListener.type(Connection.class);
        String preparedStatement = typeListener.type(PreparedStatement.class);
        String string = typeListener.type(String.class);
        String sqlException = typeListener.type(SQLException.class);
        String sets = setGenerator.getSets();
        method.append("    public void update(").append(handle).append(" handle, ").append(updateInfo).append(" value) {\n");
        method.append("        ").append(connection).append(" conn = null;\n");
        method.append("        ").append(preparedStatement).append(" pstmt = null;\n");
        method.append("        ").append(string).append(" query = \"").append(sqlGenerator.getSql()).append("\";\n");
        method.append("        try {\n");
        method.append("            conn = getConnection();\n");
        method.append("            pstmt = conn.prepareStatement(query);\n");
        method.append("            int index = 1;\n");
        method.append(sets);
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
    	String result = "";
    	if (!empty) {
    		result = method.toString();
    	}
        return result;
    }
    
    boolean isEmpty() {
    	return empty;
    }
	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {
		super.attribute(attribute);
		if (HelperFactory.getHelper().isForUpdate(attribute)) {
			empty = false;
		}
	}
}
