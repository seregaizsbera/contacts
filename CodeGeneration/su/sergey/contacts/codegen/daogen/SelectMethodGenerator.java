package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.impl.Broadcaster;

/**
 * InsertMethodGenerator
 * @author 
 */
public class SelectMethodGenerator extends Broadcaster {

    private StringBuffer method;
    private SelectSQLGenerator sqlGenerator;
    private SelectGetGenerator getGenerator;
    private SelectSetGenerator setGenerator;

    private Table currentTable;

    public SelectMethodGenerator() {
        method = new StringBuffer();
        sqlGenerator = new SelectSQLGenerator();
        getGenerator = new SelectGetGenerator();
        setGenerator = new SelectSetGenerator();
        addListener(sqlGenerator);
        addListener(getGenerator);
        addListener(setGenerator);
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        method.append("\tpublic ").append(Helper.getDataClassName(table)).append(" find(").append(Helper.getHandleClassName(table)).append(" value) throws DAOException {\n");
        method.append("\t\tConnection conn = null;\n");
        method.append("\t\tPreparedStatement pstmt = null;\n");
        method.append("\t\tResultSet rs = null;\n");
        method.append("\t\ttry {\n");
        method.append("\t\t\tconn = getConnection();\n");
        currentTable = table;
        super.startTable(table);
    }

    public void endTable() {
        super.endTable();
        method.append("\t\t\tpstmt = conn.prepareStatement(\"").append(sqlGenerator.getSql()).append("\");\n");
        method.append("\t\t\tint index = 1;\n");
        method.append(setGenerator.getSets());
        method.append("\t\t\trs = pstmt.executeQuery();\n");
        method.append("\t\t\tif (rs.next()) {\n");
        //method.append("\t\t\t\t").append(Helper.getDataClassName(currentTable)).append(" value = new ").append(Helper.getDataClassName(currentTable)).append("();\n");
        method.append("\t\t\t\tindex = 1;\n");
        method.append(getGenerator.getGets());
        method.append("\t\t\t\treturn value;\n");
        method.append("\t\t\t} else {\n");
        method.append("\t\t\t\treturn null;\n");
        method.append("\t\t\t} \n");
        method.append("\t\t} catch (SQLException e) {\n");
        method.append("\t\t\tthrow new DAOException(e);\n");
        method.append("\t\t} finally {\n");
        method.append("\t\t\tclose(rs);\n");
        method.append("\t\t\tclose(pstmt);\n");
        method.append("\t\t\tclose(conn);\n");
        method.append("\t\t}\n");
        method.append("\t}\n");
    }
    public String getMethod() {
        return method.toString();
    }
}

