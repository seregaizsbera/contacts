package su.sergey.contacts.email.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.EmailHandle;

public interface Email2 extends Serializable {
	EmailHandle getHandle();
	EmailAttributes getAttributes();
}
