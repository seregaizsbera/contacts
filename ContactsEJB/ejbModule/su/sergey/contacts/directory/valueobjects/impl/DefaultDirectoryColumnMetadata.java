package su.sergey.contacts.directory.valueobjects.impl;

import java.sql.Types;

import su.sergey.contacts.directory.valueobjects.DirectoryColumnMetadata;
import su.sergey.contacts.util.ContactsDateTimeFormat;

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
     * ������������� �� �������� ������� ���� ������ �������������
     */
    private boolean isGenerated;    
    
    /**
     * ������� ������.
     */
    public DefaultDirectoryColumnMetadata() {}

    /**
     * ������� ������ - ��� ��������� ������� ����� ���������������� ���������.
     */
    public DefaultDirectoryColumnMetadata(String dbColumnName, String fullName, int width, int type, boolean isNullable, boolean isGenerated) {
        this.dbColumnName = dbColumnName;
        this.fullName = fullName;
        this.type = type;
        this.isNullable = isNullable;
        this.isGenerated = isGenerated;
        switch (type) {
        	case Types.SMALLINT:
        	case Types.INTEGER:
        	case Types.BIGINT:
        	    this.width = width * 3;
        	    break;
        	case Types.DATE:
        	    this.width = 10;
        	    break;
        	case Types.TIME:
        	    this.width = 8;
        	    break;
        	case Types.TIMESTAMP:
        	    this.width = 22;
        	    break;
        	default:
        	    this.width = width;
        }
    }

    /**
     * ���������� ��� ������� � ���� ������.
     */
    public String getDbColumnName() {
        return dbColumnName;
    }

    /**
     * ���������� ������ ������������.
     */
    public String getFullName() {
        return fullName;
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
    
	/**
	 * @see DirectoryColumnMetadata#isGenerated()
	 */
	public boolean isGenerated() {
		return isGenerated;
	}
	
	/**
	 * Sets the fullName
	 * @param fullName The fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
