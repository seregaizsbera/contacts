package su.sergey.contacts.person.valueobjects.delegate;

import su.sergey.contacts.dto.PersonCreateInfo;
import su.sergey.contacts.dto.PersonUpdateInfo;
import su.sergey.contacts.person.valueobjects.PersonAttributes;

public final class PersonToPersonData implements PersonUpdateInfo, PersonCreateInfo {
	private PersonAttributes attributes;

	/**
	 * Constructor for PersonToPersonData
	 */
	public PersonToPersonData(PersonAttributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * @see PersonUpdateInfo#getFirst()
	 */
	public String getFirst() {
		return attributes.getFirstName();
	}

	/**
	 * @see PersonUpdateInfo#getMiddle()
	 */
	public String getMiddle() {
		return attributes.getMiddleName();
	}

	/**
	 * @see PersonUpdateInfo#getLast()
	 */
	public String getLast() {
		return attributes.getLastName();
	}

	/**
	 * @see PersonUpdateInfo#getNote()
	 */
	public String getNote() {
		return attributes.getNote();
	}
}
