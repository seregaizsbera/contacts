package su.sergey.contacts.properties;

import su.sergey.contacts.exceptions.ContactsException;

public class PropertyNotFoundException extends ContactsException {

	/**
	 * Constructor for PropertyNotFoundException
	 */
	public PropertyNotFoundException() {
	}

	/**
	 * Constructor for PropertyNotFoundException
	 */
	public PropertyNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructor for PropertyNotFoundException
	 */
	public PropertyNotFoundException(Throwable parentException) {
		super(parentException);
	}

	/**
	 * Constructor for PropertyNotFoundException
	 */
	public PropertyNotFoundException(String message, Throwable parentException) {
		super(message, parentException);
	}
}
