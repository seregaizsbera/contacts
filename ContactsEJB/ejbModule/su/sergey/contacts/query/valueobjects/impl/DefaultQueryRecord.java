package su.sergey.contacts.query.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.query.valueobjects.QueryRecord;

public class DefaultQueryRecord implements Serializable, QueryRecord {
	private String values[];
	
	public DefaultQueryRecord() {
	}
	
	public DefaultQueryRecord(String values[]) {
		this.values = values;
	}

	/**
	 * Gets the values
	 * @return Returns a String[]
	 */
	public String[] getValues() {
		return values;
	}
	
	/**
	 * Sets the values
	 * @param values The values to set
	 */
	public void setValues(String[] values) {
		this.values = values;
	}
}
