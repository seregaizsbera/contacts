package su.sergey.contacts.query.valueobjects;

import java.io.Serializable;

public interface QueryResult extends Serializable {
	QueryMetaData getMetaData();
	QueryRecord[] getRecords();
}
