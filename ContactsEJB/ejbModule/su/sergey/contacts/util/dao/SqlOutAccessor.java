package su.sergey.contacts.util.dao;

/**
 * Утильный интерфес созданный для процесса добавления
 * колонок в результат запроса из автогенерящихся DAO.
 * Создан для использоваия SQLGenerator.
 * 
 * @author Сергей Богданов
 */
public interface SqlOutAccessor {
    /**
     * Добавляет колонку в запрос.
     * @param columnName имя колонки.
     */
    void addOut(String columnName);
}
