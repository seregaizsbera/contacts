package su.sergey.contacts.email.valueobjects;

import java.io.Serializable;

public interface EmailAttributes extends Serializable {
	String getEmail();
	boolean isBasic();
}
