package su.sergey.contacts.codegen.statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.impl.DefaultListener;

/**
 * TypeStatistics
 * @author 
 */
public class AutoGenerateStatistics extends DefaultListener implements TableListener {

    private Map tables;
    private Table currentTable;

    public AutoGenerateStatistics() {
        tables = new HashMap();
    }

    public void startTable(Table table) {
        currentTable = table;
        tables.put(table.getTable(), new ArrayList());
    }

    public void attribute(Attribute attribute) {
        if (attribute.isGenerated()) {
            ((Collection)tables.get(attribute.getTable().getTable())).add(attribute);
        }
    }

    public void endTable() {
        if (((Collection)tables.get(currentTable.getTable())).size() == 0) {
            tables.remove(currentTable.getTable());
        }
    }

    public String listTypes() {
        StringBuffer res = new StringBuffer();
        for (Iterator i = tables.entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry)i.next();
            res.append(entry.getKey()).append("\n");
            Collection attrs = (Collection)entry.getValue();
            for (Iterator j = attrs.iterator(); j.hasNext();) {
                Attribute attribute = (Attribute)j.next();
                res.append("\t").append(attribute.getColumnName()).append("\n");
            }
        }
        return res.toString();
    }
}
