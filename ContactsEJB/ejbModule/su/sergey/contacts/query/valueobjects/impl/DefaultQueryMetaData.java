package su.sergey.contacts.query.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.query.valueobjects.QueryMetaData;

public class DefaultQueryMetaData implements Serializable, QueryMetaData {
	private String columnNames[];
	
	public DefaultQueryMetaData(String columnNames[]) {
		this.columnNames = columnNames;
	}
	
	/**
	 * Gets the columnNames
	 * @return Returns a String[]
	 */
	public String[] getColumnNames() {
		return columnNames;
	}
	
	/**
	 * Sets the columnNames
	 * @param columnNames The columnNames to set
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
}
