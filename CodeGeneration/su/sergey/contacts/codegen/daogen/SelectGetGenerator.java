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

public class SelectGetGenerator implements TableListener {
    private StringBuffer gets;
    public static final String PREFIX = "\t\t\t\t";

    public SelectGetGenerator() {
        gets = new StringBuffer();
    }

    public void startTable(Table table) {
        gets.delete(0, gets.length());
    }

    public void attribute(Attribute attribute) {
        gets.append(PREFIX).append("value.set").append(Helper.getAttributeName(attribute)).append("(").append(Helper.getGetMethod(attribute)).append(");\n");
    }

    public void endTable() {
    }

    public String getGets() {
        return gets.toString();
    }
}
