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

public class RemoveMethodGenerator extends Broadcaster {

    private StringBuffer method;
    private StringBuffer pkSignature;
    private RemoveSQLGenerator sqlGenerator;
    private RemoveSetGenerator setGenerator;

    private Table currentTable;

    public RemoveMethodGenerator() {
        method = new StringBuffer();
        pkSignature = new StringBuffer();
        sqlGenerator = new RemoveSQLGenerator();
        setGenerator = new RemoveSetGenerator();
        addListener(sqlGenerator);
        addListener(setGenerator);
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        pkSignature.delete(0, pkSignature.length());
        currentTable = table;
        super.startTable(table);
    }

    public void attribute(Attribute attribute) {
        if (attribute.getKeyseq() != 0) {
            if (pkSignature.length() != 0) {
                pkSignature.append(", ");
            }
            pkSignature.append(Helper.getJavaType(attribute)).append(" ").append(Helper.getAttributeFieldName(attribute));
        }
        super.attribute(attribute);
    }

    public void endTable() {
        super.endTable();

        method.append("\tpublic int remove(").append(pkSignature).append(") throws DAOException {\n");
        method.append("\t\tConnection conn = null;\n");
        method.append("\t\tPreparedStatement pstmt = null;\n");
        method.append("\t\ttry {\n");
        method.append("\t\t\tconn = getConnection();\n");

        method.append("\t\t\tpstmt = conn.prepareStatement(\"").append(sqlGenerator.getSql()).append("\");\n");
        method.append("\t\t\tint index = 1;\n");
        method.append(setGenerator.getSets());
        method.append("\t\t\treturn pstmt.executeUpdate();\n");
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

