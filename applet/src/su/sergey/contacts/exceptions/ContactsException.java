package su.sergey.contacts.exceptions;

public class ContactsException extends GenericException {
	
	public ContactsException() {}
	
	public ContactsException(String message) {
		super(message);
	}
	
	/**
	 * Constructor for ContactsException
	 */
	public ContactsException(Throwable parentException) {
		super(parentException);
	}

	/**
	 * Constructor for ContactsException
	 */
	public ContactsException(String message, Throwable parentException) {
		super(message, parentException);
	}
}

