package su.sergey.contacts.util;

import su.sergey.contacts.exceptions.ContactsException;

public class LoginFailedException extends ContactsException {

	/**
	 * Constructor for LoginFailedException
	 */
	public LoginFailedException() {
	}

	/**
	 * Constructor for LoginFailedException
	 */
	public LoginFailedException(String message) {
		super(message);
	}

	/**
	 * Constructor for LoginFailedException
	 */
	public LoginFailedException(Throwable parentException) {
		super(parentException);
	}

	/**
	 * Constructor for LoginFailedException
	 */
	public LoginFailedException(String message, Throwable parentException) {
		super(message, parentException);
	}
}
