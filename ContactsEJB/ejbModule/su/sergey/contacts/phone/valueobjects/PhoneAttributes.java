package su.sergey.contacts.phone.valueobjects;

import java.io.Serializable;

public interface PhoneAttributes extends Serializable {
	String getPhone();
	Integer getType();
	boolean isBasic();
}
