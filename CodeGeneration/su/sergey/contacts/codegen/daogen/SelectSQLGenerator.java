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

public class SelectSQLGenerator implements TableListener {
    private StringBuffer sql;

    private Table currentTable;
    private List pkAttrs;
    private boolean first;

    public SelectSQLGenerator() {
        sql = new StringBuffer();
        pkAttrs = new ArrayList(5);
    }

    public void startTable(Table table) {
        sql.delete(0, sql.length());
        sql.append("SELECT ");
        currentTable = table;
        pkAttrs.clear();
        first = true;
    }

    public void attribute(Attribute attribute) {
        if (first) {
            first = false;
        } else {
            sql.append(", ");
        }
        sql.append(attribute.getColumnName());
        if (attribute.getKeyseq() != 0) {
            pkAttrs.add(attribute);
        }
    }


    public void endTable() {
        sql.append(" FROM ").append(Helper.getTableName(currentTable)).append(" WHERE ");
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
