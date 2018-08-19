package su.sergey.contacts.properties;

import su.sergey.contacts.exceptions.ContactsException;

public class InvalidPropertyValueException extends ContactsException {

	/**
	 * Constructor for InvalidPropertyValueException
	 */
	public InvalidPropertyValueException() {
	}

	/**
	 * Constructor for InvalidPropertyValueException
	 */
	public InvalidPropertyValueException(String message) {
		super(message);
	}

	/**
	 * Constructor for InvalidPropertyValueException
	 */
	public InvalidPropertyValueException(Throwable parentException) {
		super(parentException);
	}

	/**
	 * Constructor for InvalidPropertyValueException
	 */
	public InvalidPropertyValueException(String message, Throwable parentException) {
		super(message, parentException);
	}
}
