package su.sergey.contacts.query.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.query.valueobjects.QueryMetaData;
import su.sergey.contacts.query.valueobjects.QueryRecord;
import su.sergey.contacts.query.valueobjects.QueryResult;

public class DefaultQueryResult implements Serializable, QueryResult {
	private QueryMetaData metaData;
	private QueryRecord records[];
	
	public DefaultQueryResult(QueryMetaData metaData, QueryRecord records[]) {
		this.metaData = metaData;
		this.records = records;
	}

	/**
	 * Gets the records
	 * @return Returns a QueryRecord[]
	 */
	public QueryRecord[] getRecords() {
		return records;
	}
	
	/**
	 * Sets the records
	 * @param records The records to set
	 */
	public void setRecords(QueryRecord[] records) {
		this.records = records;
	}

	/**
	 * Gets the metaData
	 * @return Returns a QueryMetaData
	 */
	public QueryMetaData getMetaData() {
		return metaData;
	}
	
	/**
	 * Sets the metaData
	 * @param metaData The metaData to set
	 */
	public void setMetaData(QueryMetaData metaData) {
		this.metaData = metaData;
	}
}
