package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.impl.Broadcaster;

/**
 * InsertMethodGenerator
 * @author 
 * @date 16.07.2002
 * @time 15:41:16
 */

public class InsertMethodGenerator extends Broadcaster {

    private StringBuffer method;
    private InsertSQLGenerator sqlGenerator;
    private InsertSetGenerator setGenerator;
    private boolean isGenerated = false;
    private Table currentTable = null;

    public InsertMethodGenerator() {
        method = new StringBuffer();
        sqlGenerator = new InsertSQLGenerator();
        setGenerator = new InsertSetGenerator();
        addListener(sqlGenerator);
        addListener(setGenerator);
    }

    public void startTable(Table table) {
        isGenerated = false;
        method.delete(0, method.length());
        currentTable = table;
        super.startTable(table);
    }

    public void attribute(Attribute attribute) {
        if (attribute.isGenerated()) {
            isGenerated = true;
        }
        super.attribute(attribute);
    }

    public void endTable() {
        super.endTable();

        method.append("\tpublic ").append(isGenerated ? "java.math.BigDecimal" : "void").append(" create(").append(Helper.getCreateInfoClassName(currentTable)).append(" value) throws DAOException {\n");
        method.append("\t\tConnection conn = null;\n");
        method.append("\t\tPreparedStatement pstmt = null;\n");
        method.append("\t\ttry {\n");
        method.append("\t\t\tconn = getConnection();\n");

        method.append("\t\t\tpstmt = conn.prepareStatement(\"").append(sqlGenerator.getSql()).append("\");\n");
        method.append("\t\t\tint index = 1;\n");
        method.append(setGenerator.getSets());
        method.append("\t\t\tpstmt.executeUpdate();\n");
        if (isGenerated) {
            method.append("\t\t\treturn getCurrentId(conn, \"").append(currentTable.getSchema()).append("\", \"").append(currentTable.getTable()).append("\");\n");
        }
        method.append("\t\t} catch (SQLException e) {\n");
        method.append("\t\t\tthrow new DAOException(e);\n");
        method.append("\t\t} finally {\n");
        method.append("\t\t\tclose(pstmt);\n");
        method.append("\t\t\tclose(conn);\n");
        method.append("\t\t}\n");
        method.append("\t}\n");
    }

    public String getMethod() {
        return method.toString();
    }
}

