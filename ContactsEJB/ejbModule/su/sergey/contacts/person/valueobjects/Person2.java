package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.valueobjects.PersonAttributes;

public class Person2 implements Serializable {
	private PersonHandle handle;
	private PersonAttributes attributes;

	/**
	 * Constructor for Person2
	 */
	public Person2(PersonHandle handle, PersonAttributes attributes) {
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
