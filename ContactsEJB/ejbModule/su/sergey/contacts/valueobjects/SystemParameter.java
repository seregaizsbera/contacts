package su.sergey.contacts.valueobjects;

/**
 * Содержит данные об одном параметры системы (показываются на странице "Параметры")
 * @author: 
 * @version: 1.0
 */
public interface SystemParameter {
    /**
     * Возвращает имя параметра.
     */
    String getName();

    /**
     * Устанавливает имя параметра.
     */
    void setName(String name);

    /**
     * Возвращает тип параметра.
     */
    String getType();

    /**
     * Устанавливает тип параметра.
     */
    void setType(String type);

    /**
     * Возвращает максимальную длину параметра.
     */
    int getLength();

    /**
     * Устанавливает максимальную длину параметра.
     */
    void setLength(int length);

    /**
     * Возвращает значение параметра.
     */
    String getValue();

    /**
     * Устанавливает значение параметра.
     */
    void setValue(String value);
}
