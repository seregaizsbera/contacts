package su.sergey.contacts.valueobjects;

import java.io.Serializable;

/**
 * Содержит описание метаданных cтолбца таблицы.
 * 
 * @author: Сергей Богданов
 */
public interface DirectoryColumnMetadata extends Serializable {

    /**
     * Возвращает имя колонки в базе данных.
     */
    String getDbColumnName();

    /**
     * Возвращает полное наименование.
     */
    String getFullName();

    /**
     * Устанавливает новое полное наименование
     */
    void setFullName(String fullName);

    /**
     * Возвращает ширину поля.
     */
    int getWidth();

    /**
     * Возвращает тип данных поля.
     */
    int getType();

    /**
     * Показывает, могут ли быть в поле нули
     */
    boolean isNullable();
    
    /**
     * Показывает, присваивается ли значение данному полю записи автоматически
     */
    boolean isGenerated();
}
