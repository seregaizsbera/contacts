package su.sergey.contacts.codegen.daogen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class RemoveSetGenerator implements TableListener {
    public static final String PREFIX = "\t\t\t";

    private StringBuffer sets;

    private List pkAttrs;

    public RemoveSetGenerator() {
        sets = new StringBuffer();
        pkAttrs = new ArrayList(5);
    }

    public void startTable(Table table) {
        sets.delete(0, sets.length());
        pkAttrs.clear();
    }

    public void attribute(Attribute attribute) {
        if (attribute.getKeyseq() != 0) {
            pkAttrs.add(attribute);
        }
    }


    public void endTable() {
        for (Iterator i = pkAttrs.iterator(); i.hasNext();) {
            Attribute attribute = (Attribute)i.next();
            sets.append(PREFIX).append(Helper.getSetMethod(attribute)).append(Helper.getAttributeFieldName(attribute)).append(");\n");
        }
    }

    public String getSets() {
        return sets.toString();
    }
}
