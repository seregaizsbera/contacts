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

public class UpdateSQLGenerator implements TableListener {
    private StringBuffer sql;

    private List pkAttrs;
    private boolean first;

    public UpdateSQLGenerator() {
        sql = new StringBuffer();
        pkAttrs = new ArrayList(5);
    }

    public void startTable(Table table) {
        sql.delete(0, sql.length());
        sql.append("UPDATE ").append(Helper.getTableName(table)).append(" SET ");
        pkAttrs.clear();
        first = true;
    }

    public void attribute(Attribute attribute) {
        if (attribute.getKeyseq() != 0) {
            pkAttrs.add(attribute);
        } else {
            if (!attribute.isGenerated()) {
                if (first) {
                    first = false;
                } else {
                    sql.append(", ");
                }
                sql.append(attribute.getColumnName()).append(" = ?");
            }
        }
    }


    public void endTable() {
        sql.append(" WHERE ");
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
