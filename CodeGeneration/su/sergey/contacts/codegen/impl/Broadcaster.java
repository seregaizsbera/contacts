package su.sergey.contacts.codegen.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * Broadcaster
 * @author Сергей Богданов
 */
public class Broadcaster implements TableListener {
    private static final int DEF_NODE_COUNT = 5;
    private List nodes;

    public Broadcaster() {
        nodes = new ArrayList(DEF_NODE_COUNT);
    }

    public boolean addListener(TableListener listener) {
        return nodes.add(listener);
    }

    public boolean removeListener(TableListener listener) {
        return nodes.remove(listener);
    }

    public List getListeners() {
        return Collections.unmodifiableList(nodes);
    }

    public void startTable(Table table) {
        for (Iterator i = nodes.iterator(); i.hasNext();) {
            TableListener listener = (TableListener)i.next();
            listener.startTable(table);
        }
    }

    public void attribute(Attribute attribute) {
        for (Iterator i = nodes.iterator(); i.hasNext();) {
            TableListener listener = (TableListener)i.next();
            listener.attribute(attribute);
        }
    }

    public void endTable() {
        for (Iterator i = nodes.iterator(); i.hasNext();) {
            TableListener listener = (TableListener)i.next();
            listener.endTable();
        }
    }
}
