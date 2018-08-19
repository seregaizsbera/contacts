package su.sergey.contacts.directory.valueobjects;

import java.io.Serializable;

/**
 * Содержит данные об одной записи таблицы.
 * 
 * @author: Сергей Богданов
 */
public interface DirectoryRecord extends Serializable {
    /**
     * Возвращает значения одной записи справочника.
     */
    String[] getValues();

    /**
     * Возвращает значение PK справочника.
     */
    Integer getOid();
}
