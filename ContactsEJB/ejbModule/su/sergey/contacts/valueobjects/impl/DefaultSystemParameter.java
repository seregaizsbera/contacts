package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.SystemParameter;

/**
 * ���������� ������������� ���������� <code>SystemParameter</code>.
 * @author: 
 * @version: 1.0
 */
public class DefaultSystemParameter implements SystemParameter {
    /**
     * ��� ���������.
     */
    private String name;
    /**
     * ��� ���������.
     * @see su.sergey.contacts.valueobjects.SystemParameter
     */
    private String type;
    /**
     * ������������ ����� ���������.
     */
    private int length;
    /**
     * �������� ���������.
     */
    private String value;

    /**
     * ������� ������.
     */
    public DefaultSystemParameter() {
    }
    /**
     * ������� ������.
     */
    public DefaultSystemParameter(String name, String type, int length, String value) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.value = value;
    }

    /**
     * ���������� ��� ���������.
     */
    public String getName() {
        return name;
    }

    /**
     * ������������� ��� ���������.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ���������� ��� ���������.
     */
    public String getType() {
        return type;
    }

    /**
     * ������������� ��� ���������.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * ���������� ������������ ����� ���������.
     */
    public int getLength() {
        return length;
    }

    /**
     * ������������� ������������ ����� ���������.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * ���������� �������� ���������.
     */
    public String getValue() {
        return value;
    }

    /**
     * ������������� �������� ���������.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
