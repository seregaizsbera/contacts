package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.Person2;

public class DefaultPerson2 implements Serializable, Person2 {
	private PersonHandle handle;
	private PersonAttributes attributes;

	/**
	 * Constructor for DefaultPerson2
	 */
	public DefaultPerson2(PersonHandle handle, PersonAttributes attributes) {
		this.handle = handle;
		this.attributes = attributes;
	}

	/**
	 * Gets the handle
	 * @return Returns a PersonHandle
	 */
	public PersonHandle getHandle() {
		return handle;
	}
	
	/**
	 * Gets the attributes
	 * @return Returns a Person
	 */
	public PersonAttributes getAttributes() {
		return attributes;
	}
}
