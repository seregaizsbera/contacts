package su.sergey.contacts.codegen.daogen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * RemoveSQLGenerator
 * 
 * @author Сергей Богданов
 */
public class RemoveSQLGenerator implements TableListener {
    private StringBuffer sql;
    private List pkAttrs;

    public RemoveSQLGenerator() {
        sql = new StringBuffer();
        pkAttrs = new ArrayList(5);
    }

    public void startTable(Table table) {
        sql.delete(0, sql.length());
        sql.append("DELETE FROM ").append(Helper.getTableName(table)).append(" WHERE ");
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
            sql.append(attribute.getColumnName()).append(" = ?");
            if (i.hasNext()) {
                sql.append(" AND ");
            }
        }
    }

    public String getSql() {
        return sql.toString();
    }
}
