package su.sergey.contacts.codegen.daogen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.util.*;


/**
 * UpdateSetGenerator
 * 
 * @author Сергей Богданов
 */
public class UpdateSetGenerator implements TableListener {
    public static final String PREFIX = "            ";
    private StringBuffer sets;
    private List pkAttrs;

    public UpdateSetGenerator() {
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
        } else {
            if (!attribute.isGenerated()) {
                sets.append(PREFIX).append(HelperFactory.getHelper().getSetMethod(attribute)).append("value.get").append(HelperFactory.getHelper().getAttributeName(attribute)).append("());\n");
            }
        }
    }

    public void endTable() {
        for (Iterator i = pkAttrs.iterator(); i.hasNext();) {
            Attribute attribute = (Attribute)i.next();
            sets.append(PREFIX).append(HelperFactory.getHelper().getSetMethod(attribute)).append("handle.get").append(HelperFactory.getHelper().getAttributeName(attribute)).append("());\n");
        }
    }

    public String getSets() {
        return sets.toString();
    }
}
