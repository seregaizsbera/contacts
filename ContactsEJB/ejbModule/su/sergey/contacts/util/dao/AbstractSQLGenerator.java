package su.sergey.contacts.util.dao;

import java.util.Set;
import java.util.HashSet;

/**
 * Базовый класс для классов занимающихся конструирование сложных SQL
 * запросов.
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
     * Инициализирует класс для составления нового SQL запроса.
     * 
     * @param table начальная таблица запроса.
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
     * Вовлекает в запрос еще одну таблицу с помощью INNER JOIN.
     * 
     * @param oldTable таблица которая уже упоминалась в запросе.
     * @param newTable новая не упоминавщаяся в запросе таблица.
     * @param oldId имя поля в старой таблице по которому идет связь.
     * @param newId имя поля в новой таблице по которому идет связь.
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
     * Вовлекает в запрос еще одну таблицу с помощью OUTER LEFT JOIN.
     * 
     * @param oldTable таблица которая уже упоминалась в запросе.
     * @param newTable новая не упоминавщаяся в запросе таблица.
     * @param oldId имя поля в старой таблице по которому идет связь.
     * @param newId имя поля в новой таблице по которому идет связь.
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
     * Накладывает ограничение на результат выполнения запроса.
     * Все ограничения объединяются условием AND.
     * 
     * @param table имя таблицы на поле которой накладвается условие
     * @param column имя поля на коорое накладывается условие
     * @param condition условие, например &quot; LIKE '%'&quot;
     */
    public void addCondition(String table, String column, String condition) {
        if (where.length() != 0) {
            where.append(" AND ");
        }
        where.append(table).append('.').append(column).append(condition);
    }

    /**
     * Накладывает ограничение DISTINCT на результат выполнения запроса. 
     * 
     * @param distinct_condition условие выбора уникальных значений полей выражения SELECT
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
     * Накладывает ограничение на результат выполнения запроса.
     * Все ограничения объединяются условием OR.
     * 
     * @param table имя таблицы на поле которой накладвается условие
     * @param column имя поля на коорое накладывается условие
     * @param condition условие, например &quot; LIKE '%'&quot;
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
     * Накладывает ограничение на результат выполнения запроса.
     * 
     * @param condition условие, например &quot;&lt;имя таблицы&gt;.&lt;имя поля&gt; LIKE '%'&quot;
     */
    public void addCondition(String condition) {
        if (where.length() != 0) {
            where.append(" AND ");
        }
        where.append(condition);
    }

    /**
     * Добавляет условие для упорядочивания результатов запроса
     *
     * @param condition Условие упорядочивания например :
     *                  &quot; &lt;имя поля&gt; ASC&quot;
     */
    public void addOrder(String condition) {
        if (order.length() != 0) {
            order.append(',');
        }
        order.append(condition);
    }

    /**
     * Возвращает составленый запрос.
     * 
     * @throws IllegalStateException если получивчишийся запрос некоректен. Например,
     *                               не возвращает никаких данных.
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
