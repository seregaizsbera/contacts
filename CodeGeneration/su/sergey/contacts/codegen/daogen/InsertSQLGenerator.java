package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.util.*;


/**
 * InsertSQLGenerator
 * 
 * @author Сергей Богданов
 */
public class InsertSQLGenerator implements TableListener {
    private StringBuffer sql;
    private int attributesCount = 0;

    public InsertSQLGenerator() {
        sql = new StringBuffer();
    }

    public void startTable(Table table) {
        sql.delete(0, sql.length());
        sql.append("INSERT INTO ").append(HelperFactory.getHelper().getTableName(table)).append(" (");
        attributesCount = 0;
    }

    public void attribute(Attribute attribute) {
        if (!attribute.isGenerated()) {
            if (attributesCount != 0) {
                sql.append(", ");
            }
            sql.append(attribute.getColumnName());
            attributesCount++;
        }
    }


    public void endTable() {
        sql.append(") VALUES (");
        for (int i = 0; i < attributesCount; i++) {
            if (i != 0) {
                sql.append(", ");
            }
            sql.append("?");
        }
        sql.append(")");
    }

    public String getSql() {
        return sql.toString();
    }
}
