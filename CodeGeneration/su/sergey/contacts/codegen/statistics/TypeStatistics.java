package su.sergey.contacts.codegen.statistics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.impl.DefaultListener;

/**
 * TypeStatistics
 * 
 * @author Сергей Богданов
 */
public class TypeStatistics extends DefaultListener implements TableListener {

    private Set types;

    public TypeStatistics() {
        types = new HashSet();
    }

    public void attribute(Attribute attribute) {
    	if (attribute.getType().equalsIgnoreCase("name")) {
    		attribute = attribute;
    	}
        types.add(attribute.getType());
    }

    public String listTypes() {
        System.err.println("# = " + types.size());
        StringBuffer buffer = new StringBuffer();
        for (Iterator iterator = types.iterator(); iterator.hasNext();) {
            String s = (String)iterator.next();
            buffer.append(s).append(", ");
        }
        return buffer.toString();
    }
}
