package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * SelectSQLGenerator
 * @author 
 */
public class InsertSetGenerator implements TableListener {
    private StringBuffer sets;
    public static final String PREFIX = "\t\t\t";

    public InsertSetGenerator() {
        sets = new StringBuffer();
    }

    public void startTable(Table table) {
        sets.delete(0, sets.length());
    }

    public void attribute(Attribute attribute) {
        if (!attribute.isGenerated()) {
            sets.append(PREFIX).append(Helper.getSetMethod(attribute)).append("value.get").append(Helper.getAttributeName(attribute)).append("());\n");
        }
    }

    public void endTable() {
    }

    public String getSets() {
        return sets.toString();
    }
}
