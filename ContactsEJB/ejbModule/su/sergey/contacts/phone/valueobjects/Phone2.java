package su.sergey.contacts.phone.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.person.valueobjects.*;
import su.sergey.contacts.phone.valueobjects.*;



public interface Phone2 extends Serializable {
	PhoneHandle getHandle();
	PhoneAttributes getAttributes();
}
