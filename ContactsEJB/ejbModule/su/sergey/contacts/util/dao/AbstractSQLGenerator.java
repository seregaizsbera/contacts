package su.sergey.contacts.util.dao;

import java.util.Set;
import java.util.HashSet;

/**
 * ������� ����� ��� ������� ������������ ��������������� ������� SQL
 * ��������.
 * 
 * @author 
 */
public class AbstractSQLGenerator {
    protected StringBuffer from;
    protected StringBuffer select;
    protected String distinct;
    protected StringBuffer where;
    protected StringBuffer leftJoin;
    protected StringBuffer result;
    protected StringBuffer order;
    protected Set tables;

    protected AbstractSQLGenerator() {
        tables = new HashSet();
        from = new StringBuffer();
        select = new StringBuffer();
        distinct = " ";
        where = new StringBuffer();
        leftJoin = new StringBuffer();
        result = new StringBuffer();
        order = new StringBuffer();
    }

    /**
     * �������������� ����� ��� ����������� ������ SQL �������.
     * 
     * @param table ��������� ������� �������.
     */
    public void init(String table) {
        from.delete(0, from.length());
        select.delete(0, select.length());
        distinct = " ";
        where.delete(0, where.length());
        leftJoin.delete(0, leftJoin.length());
        result.delete(0, result.length());
        tables.clear();
        tables.add(table);
        from.append(table);
        order.delete(0, order.length());
    }

    /**
     * ��������� � ������ ��� ���� ������� � ������� INNER JOIN.
     * 
     * @param oldTable ������� ������� ��� ����������� � �������.
     * @param newTable ����� �� ������������� � ������� �������.
     * @param oldId ��� ���� � ������ ������� �� �������� ���� �����.
     * @param newId ��� ���� � ����� ������� �� �������� ���� �����.
     */
    public void joinTable(String oldTable, String newTable, String oldId, String newId) {
        tables.add(newTable);
        if (leftJoin.length() > 0) {
            leftJoin.append(" ");
        }
        leftJoin.append("INNER JOIN ").append(newTable).append(" ON ").append(oldTable).append('.').append(oldId).
            append(" = ").append(newTable).append('.').append(newId);
    }

    /**
     * ��������� � ������ ��� ���� ������� � ������� OUTER LEFT JOIN.
     * 
     * @param oldTable ������� ������� ��� ����������� � �������.
     * @param newTable ����� �� ������������� � ������� �������.
     * @param oldId ��� ���� � ������ ������� �� �������� ���� �����.
     * @param newId ��� ���� � ����� ������� �� �������� ���� �����.
     */
    public void leftJoinTable(String oldTable, String newTable, String oldId, String newId) {
        tables.add(newTable);
        if (leftJoin.length() > 0) {
            leftJoin.append(" ");
        }
        leftJoin.append("LEFT JOIN ").append(newTable).append(" ON ").append(oldTable).append('.').append(oldId).
            append(" = ").append(newTable).append('.').append(newId);
    }

    public void addFirstBrace() {
        where.append("(");
    }

    public void addSecondBrace() {
        where.append(")");
    }

    /**
     * ����������� ����������� �� ��������� ���������� �������.
     * ��� ����������� ������������ �������� AND.
     * 
     * @param table ��� ������� �� ���� ������� ������������ �������
     * @param column ��� ���� �� ������ ������������� �������
     * @param condition �������, �������� &quot; LIKE '%'&quot;
     */
    public void addCondition(String table, String column, String condition) {
        if (where.length() != 0) {
            where.append(" AND ");
        }
        where.append(table).append('.').append(column).append(condition);
    }

    /**
     * ����������� ����������� DISTINCT �� ��������� ���������� �������. 
     * 
     * @param distinct_condition ������� ������ ���������� �������� ����� ��������� SELECT
     */
    public void addDistinct(boolean distinct_condition) {
        if (distinct_condition) {
        	distinct = " DISTINCT ";
        } else {
        	distinct = " ";
        }
    }

    public void addFirstCondition(String table, String column, String condition) {
        where.append(table).append('.').append(column).append(condition);
    }

    public void addOpenClause() {
        where.append("(");
    }

    /**
     * ����������� ����������� �� ��������� ���������� �������.
     * ��� ����������� ������������ �������� OR.
     * 
     * @param table ��� ������� �� ���� ������� ������������ �������
     * @param column ��� ���� �� ������ ������������� �������
     * @param condition �������, �������� &quot; LIKE '%'&quot;
     */
    public void addORCondition(String table, String column, String condition) {
        if (where.length() != 0) {
            where.append(" OR ");
        }
        where.append(table).append('.').append(column).append(condition);
    }

    public void addCloseClause() {
        where.append(")");
    }

    /**
     * ����������� ����������� �� ��������� ���������� �������.
     * 
     * @param condition �������, �������� &quot;&lt;��� �������&gt;.&lt;��� ����&gt; LIKE '%'&quot;
     */
    public void addCondition(String condition) {
        if (where.length() != 0) {
            where.append(" AND ");
        }
        where.append(condition);
    }

    /**
     * ��������� ������� ��� �������������� ����������� �������
     *
     * @param condition ������� �������������� �������� :
     *                  &quot; &lt;��� ����&gt; ASC&quot;
     */
    public void addOrder(String condition) {
        if (order.length() != 0) {
            order.append(',');
        }
        order.append(condition);
    }

    /**
     * ���������� ����������� ������.
     * 
     * @throws IllegalStateException ���� �������������� ������ ����������. ��������,
     *                               �� ���������� ������� ������.
     */
    public String getSQL() {
        if (select.length() != 0) {
            result.delete(0, result.length());
            result.append("SELECT ").append(distinct).append(select.toString()).append(" FROM ").append(from.toString());
            if (leftJoin.length() > 0) {
                result.append(" ");
            }
            result.append(leftJoin.toString());
            if (where.length() != 0) {
                result.append(" WHERE ").append(where.toString());
            }
            if (order.length() != 0) {
                result.append(" ORDER BY ").append(order.toString());
            }
            return result.toString();
        } else {
            throw new IllegalStateException("Nothing to select!");
        }
    }
}
