package su.sergey.contacts.directory.valueobjects.impl;

import su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;

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
    
    private boolean readOnly;

    /**
     * ������� ������.
     */
    public DefaultDirectoryMetadata() {
    }

    /**
     * ������� ������ ��������� ��� �����, �������, ��������, ������ �������
     */
    public DefaultDirectoryMetadata(String dbSchemaName, String dbTableName, String description, DirectoryColumnMetadata[] columnMetadata, boolean readOnly) {
        this.handle = new DirectoryMetadataHandle(dbTableName);
        this.dbSchemaName = dbSchemaName;
        this.dbTableName = dbTableName;
        this.description = description;
        this.columnMetadata = columnMetadata;
        this.readOnly = readOnly;
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
	/**
	 * Gets the readOnly
	 * @return Returns a boolean
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	
	/**
	 * Sets the readOnly
	 * @param readOnly The readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
