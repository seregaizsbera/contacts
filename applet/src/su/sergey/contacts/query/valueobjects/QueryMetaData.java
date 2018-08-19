package su.sergey.contacts.query.valueobjects;

import java.io.Serializable;

public interface QueryMetaData extends Serializable {
	String[] getColumnNames();
}
