package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.util.*;


/**
 * InsertMethodGenerator
 * 
 * @author Сергей Богданов
 */
public class InsertMethodGenerator extends Broadcaster {
    private StringBuffer method;
    private InsertSQLGenerator sqlGenerator;
    private InsertSetGenerator setGenerator;
    private boolean isIdentity;
    private Table currentTable;
    private TypeListener typeListener;
    private String dtoPackage;
    private String daoException;

    public InsertMethodGenerator(TypeListener typeListener, String dtoPackage, String daoException) {
    	this.typeListener = typeListener;
    	this.dtoPackage = dtoPackage;
    	this.daoException = daoException;
        method = new StringBuffer();
        sqlGenerator = new InsertSQLGenerator();
        setGenerator = new InsertSetGenerator();
        addListener(sqlGenerator);
        addListener(setGenerator);
    }

    public void startTable(Table table) {
        isIdentity = false;
        method.delete(0, method.length());
        currentTable = table;
        super.startTable(table);
    }

    public void attribute(Attribute attribute) {
        if (attribute.isIdentity()) {
            isIdentity = true;
        }
        super.attribute(attribute);
    }

    public void endTable() {
        super.endTable();
        String createInfo = typeListener.type(dtoPackage + "." + HelperFactory.getHelper().getCreateInfoClassName(currentTable));
        String bigDecimal = isIdentity ? typeListener.type("java.lang.Integer") : "void";
        String connection = typeListener.type("java.sql.Connection");
        String preparedStatement = typeListener.type("java.sql.PreparedStatement");
        String realDaoException = typeListener.type(daoException);
        String sqlException = typeListener.type("java.sql.SQLException");
        method.append("    public ").append(bigDecimal).append(" create(").append(createInfo);
        method.append(" value) {\n");
        method.append("        ").append(connection).append(" conn = null;\n");
        method.append("        ").append(preparedStatement).append(" pstmt = null;\n");
        method.append("        try {\n");
        method.append("            conn = getConnection();\n");
        method.append("            pstmt = conn.prepareStatement(\"").append(sqlGenerator.getSql()).append("\");\n");
        method.append("            int index = 1;\n");
        method.append(setGenerator.getSets());
        method.append("            pstmt.executeUpdate();\n");
        if (isIdentity) {
            method.append("            return getCurrentId(conn, \"").append(currentTable.getQualifiedName()).append("\", \"id\");\n");
        }
        method.append("        } catch (").append(sqlException).append(" e) {\n");
        method.append("            throw new ").append(realDaoException).append("(e);\n");
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
