package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.DirectoryColumnMetadata;

/**
 * ���������� ������������� ���������� <tt>DirectoryColumnMetadata</tt>.
 * 
 * @author: ������ ��������
 */
public class DefaultDirectoryColumnMetadata implements DirectoryColumnMetadata {
    /**
     * ����� �� ���� � ������� ����
     */
    private boolean isNullable;

    /**
     * ��� ������ �������
     */
    private int type;

    /**
     * ������ �������
     */
    private int width;

    /**
     * ��� ������� � ���� ������ (�� ������������ � UI).
     */
    private String dbColumnName;

    /**
     * ������ ������������. ��������� ��� ��������� ��� �������� ������ ���� �� ����� ������ �����������.
     */
    private String fullName;
    
    /**
     * ������� ������.
     */
    public DefaultDirectoryColumnMetadata() {}

    /**
     * ������� ������ - ��� ��������� ������� ����� ���������������� ���������.
     */
    public DefaultDirectoryColumnMetadata(String dbColumnName, String fullName, int width, int type, boolean isNullable) {
        this.dbColumnName = dbColumnName;
        this.fullName = fullName;
        this.width = width;
        this.type = type;
        this.isNullable = isNullable;
    }

    /**
     * ���������� ��� ������� � ���� ������.
     */
    public String getDbColumnName() {
        return dbColumnName;
    }

    /**
     * ������������� ��� ������� � ���� ������.
     */
    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    /**
     * ���������� ������ ������������.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * ������������� ����� ������ ������������.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * ���������� ������ ����.
     */
    public int getWidth() {
        return width;
    }

    /**
     * ���������� ��� ������ ����
     */
    public int getType() {
        return type;
    }

    /**
     * ����� �� ���� � ������� ����
     */
    public boolean isNullable() {
        return isNullable;
    }
    
}
