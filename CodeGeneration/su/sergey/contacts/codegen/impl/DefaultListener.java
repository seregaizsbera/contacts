package su.sergey.contacts.codegen.impl;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * DefaultListener
 * 
 * @author Сергей Богданов
 */
public class DefaultListener implements TableListener {
    public void startTable(Table table) {}

    public void attribute(Attribute attribute) {}

    public void endTable() {}
}
