package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

/**
 * ���������� ������������� ���������� <code>DirectoryMetadata</code>.
 * @author: 
 * @version: 1.0
 */
public class DefaultDirectoryMetadata implements DirectoryMetadata {

    /**
     * ���������� ���������� �����������.
     */
    private DirectoryMetadataHandle handle;
    /**
     * ���������� � �����������.
     */
    private String description;
    /**
     * �������� ����� �� ������������ � ���� ������.
     */
    private String dbSchemaName;
    /**
     * �������� ������� �� ������������ � ���� ������.
     */
    private String dbTableName;
    /**
     * ������ ����-��������� �����������.
     */
    private DirectoryColumnMetadata[] columnMetadata;

    /**
     * ������� ������.
     */
    public DefaultDirectoryMetadata() {}

    /**
     * ������� ������ ��������� ��� �����, �������, ��������, ������ �������
     */
    public DefaultDirectoryMetadata(String dbSchemaName, String dbTableName, String description, DirectoryColumnMetadata[] columnMetadata) {
        this.handle = new DirectoryMetadataHandle(dbTableName);
        this.dbSchemaName = dbSchemaName;
        this.dbTableName = dbTableName;
        this.description = description;
        this.columnMetadata = columnMetadata;
    }

    /**
     * ���������� ���������� ���������� �����������.
     */
    public DirectoryMetadataHandle getHandle() {
        return handle;
    }

    /**
     * ���������� ���������� � �����������.
     */
    public String getDescription() {
        return description;
    }

    /**
     * ������������� ����� ���������� � �����������.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ���������� �������� ����� �� ������������ � ���� ������.
     */
    public String getDbSchemaName() {
        return dbSchemaName;
    }

    /**
     * ���������� �������� ������� �� ������������ � ���� ������.
     */
    public String getDbTableName() {
        return dbTableName;
    }

    /**
     * ���������� ������ ����-��������� �����������.
     */
    public DirectoryColumnMetadata[] getColumnMetadata() {
        return columnMetadata;
    }
}
