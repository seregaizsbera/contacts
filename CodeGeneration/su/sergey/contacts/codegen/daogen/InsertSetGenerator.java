package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * SelectSetGenerator
 * 
 * @author Сергей Богданов
 */
class InsertSetGenerator implements TableListener {
    private static final String PREFIX = "            ";
    private StringBuffer sets;

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
