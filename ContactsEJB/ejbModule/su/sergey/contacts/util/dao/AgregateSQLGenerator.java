package su.sergey.contacts.util.dao;

/**
 * Служит для составления агригированных SQL запросов.
 * 
 * @author Сергей Богданов
 */
public class AgregateSQLGenerator extends AbstractSQLGenerator {
    /**
     * Добавляет в результат запроса агрегационный оператор COUNT.
     * 
     * @param table имя таблицы в которой находится колонка фигурирующая
     *              в агригацмоном операторе.
     * @param column имя колонки фигурирующий в агрегационном операторе.
     * @param distinct указывает нужно ли пересчитывать все результаты
     *                 или только различные.
     * @throws IllegalStateException
     */
    public void count(String table, String column, boolean distinct) {
        if (select.length() != 0) {
            throw new IllegalStateException();
        } else {
            select.append("COUNT(").append(distinct ? "DISTINCT " : "").append(table).append('.').append(column).append(")");
        }
    }
}
