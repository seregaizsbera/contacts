package su.sergey.contacts.valueobjects.impl;

import su.sergey.contacts.valueobjects.DirectoryRecord;

/**
 * DefaultDirectoryRecord
 * 
 * @author: Сергей Богданов
 */
public class DefaultDirectoryRecord implements DirectoryRecord {
    /**
     * Значения одной записи справочника.
     */
    private String[] values;
    
    /**
     * Значение внутреннего идентификатора записи
     */
    private Integer oid;
    
    public DefaultDirectoryRecord() {}

    /**
     * Создает объект.
     */
    public DefaultDirectoryRecord(Integer oid, String[] values) {
    	this.oid = oid;
        this.values = values;
    }

    /**
     * Возвращает значения одной записи справочника.
     */
    public String[] getValues() {
        return values;
    }
    
    /**
     * Устанавливает значения одной записи справочника.
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
