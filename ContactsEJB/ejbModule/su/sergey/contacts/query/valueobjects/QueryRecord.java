package su.sergey.contacts.query.valueobjects;

import java.io.Serializable;

public interface QueryRecord extends Serializable {
	String[] getValues();
}
