package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.util.*;


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
            sets.append(PREFIX).append(HelperFactory.getHelper().getSetMethod(attribute)).append("value.get").append(HelperFactory.getHelper().getAttributeName(attribute)).append("());\n");
        }
    }

    public void endTable() {
    }

    public String getSets() {
        return sets.toString();
    }
}
