package su.sergey.contacts.util.dao;

/**
 * Служит для составления простых SELECT запросов.
 * 
 * @author 
 */
public class SQLGenerator extends AbstractSQLGenerator {
    /**
     * Если свойство maxFetch этого класса равно этому значению
     * то запрос будет возвращать все результаты.
     */
    public static final int FETCH_ALL = -1;

    private int maxFetch = FETCH_ALL;
    private boolean isForReadOnly = true;

    public void init(String table) {
        super.init(table);
        maxFetch = FETCH_ALL;
    }

    /**
     * Добавляет еще одну колонку в результат запроса.
     * 
     * @param table имя таблицы, колонку которой надо добавить к результат.
     * @param column имя колонки, которую надо добавить результат.
     */
    public void addOut(String table, String column) {
        if (select.length() != 0) {
            select.append(", ");
        }
        select.append(table).append('.').append(column);
    }

    /**
     * Устанавливает свойство которое определяет максимальное
     * колличество строк, которое может вернуться в результате выполнения
     * составленно запроса. Установите это свойство равным FETCH_ALL
     * если хотите получить все результаты.
     * 
     * @param maxFetch максимальное
     *                 колличество строк, которое может вернуться в результате выполнения
     *                 составленно запроса.
     */
    public void setMaxFetch(int maxFetch) {
        this.maxFetch = maxFetch;
    }

    /**
     * @return возвращает максимальное
     * колличество строк, которое может вернуться в результате выполнения
     * составленно запроса. Если возвращается отрицательное значение
     * то запрос вернет все результаты.
     */
    public int getMaxFetch() {
        return maxFetch;
    }

    public boolean isForReadOnly() {
        return isForReadOnly;
    }

    public void setForReadOnly(boolean forReadOnly) {
        isForReadOnly = forReadOnly;
    }

    public String getSQL() {
        if (isForReadOnly) {
            return maxFetch <= FETCH_ALL ? (super.getSQL() + " FOR READ ONLY") : (super.getSQL() + " FETCH FIRST " + maxFetch + " ROWS ONLY FOR READ ONLY");
        } else {
            return maxFetch <= FETCH_ALL ? super.getSQL() : (super.getSQL() + " FETCH FIRST " + maxFetch + " ROWS ONLY");
        }
    }
}
