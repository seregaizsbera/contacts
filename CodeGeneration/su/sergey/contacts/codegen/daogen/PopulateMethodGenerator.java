package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * SelectSQLGenerator
 * @author 
 * @date 16.07.2002
 * @time 11:42:33
 */

public class PopulateMethodGenerator implements TableListener {
    private StringBuffer method;
    public static final String PREFIX = "\t\t";

    public PopulateMethodGenerator() {
        method = new StringBuffer();
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        method.append("\tpublic int populate(").append(Helper.getHandleClassName(table)).append(" value, ResultSet rs, int startIndex) throws SQLException {\n");
        method.append("\t\tint index = startIndex;\n");
    }

    public void attribute(Attribute attribute) {
        method.append(PREFIX).append("value.set").append(Helper.getAttributeName(attribute)).append("(").append(Helper.getGetMethod(attribute)).append(");\n");
    }

    public void endTable() {
        method.append("\t\treturn index;\n");
        method.append("\t}\n");
    }

    public String getMethod() {
        return method.toString();
    }
}
