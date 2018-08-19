package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;

public interface Related extends Serializable {
	String getRelationship();
	String getDescription();
}
