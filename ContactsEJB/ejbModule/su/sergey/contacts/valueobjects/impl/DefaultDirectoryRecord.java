package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.DirectoryRecord;

/**
 * DefaultDirectoryRecord
 * 
 * @author: ������ ��������
 */
public class DefaultDirectoryRecord implements DirectoryRecord {
    /**
     * �������� ����� ������ �����������.
     */
    private String[] values;
    
    /**
     * �������� ����������� �������������� ������
     */
    private Integer oid;
    
    public DefaultDirectoryRecord() {}

    /**
     * ������� ������.
     */
    public DefaultDirectoryRecord(Integer oid, String[] values) {
    	this.oid = oid;
        this.values = values;
    }

    /**
     * ���������� �������� ����� ������ �����������.
     */
    public String[] getValues() {
        return values;
    }
    
    /**
     * ������������� �������� ����� ������ �����������.
     */
    public void setValues(String[] values) {
        this.values = values;
    }
    
	/**
	 * Gets the oid
	 * @return Returns a Integer
	 */
	public Integer getOid() {
		return oid;
	}
	
	/**
	 * Sets the oid
	 * @param oid The oid to set
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}
}
