package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.SystemParameter;

/**
 * Дефалтовая имплементация интерфейса <code>SystemParameter</code>.
 * @author: 
 * @version: 1.0
 */
public class DefaultSystemParameter implements SystemParameter {
    /**
     * Имя параметра.
     */
    private String name;
    /**
     * Тип параметра.
     * @see su.sergey.contacts.valueobjects.SystemParameter
     */
    private String type;
    /**
     * Максимальная длина параметра.
     */
    private int length;
    /**
     * Значение параметра.
     */
    private String value;

    /**
     * Создает объект.
     */
    public DefaultSystemParameter() {
    }
    /**
     * Создает объект.
     */
    public DefaultSystemParameter(String name, String type, int length, String value) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.value = value;
    }

    /**
     * Возвращает имя параметра.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя параметра.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает тип параметра.
     */
    public String getType() {
        return type;
    }

    /**
     * Устанавливает тип параметра.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Возвращает максимальную длину параметра.
     */
    public int getLength() {
        return length;
    }

    /**
     * Устанавливает максимальную длину параметра.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Возвращает значение параметра.
     */
    public String getValue() {
        return value;
    }

    /**
     * Устанавливает значение параметра.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
