package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.PersonHandle;

public interface Person2 extends Serializable {
	PersonHandle getHandle();
	PersonAttributes getAttributes();
}
