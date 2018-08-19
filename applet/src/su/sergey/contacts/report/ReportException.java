package su.sergey.contacts.report;

import su.sergey.contacts.exceptions.ContactsException;

public class ReportException extends ContactsException {

	/**
	 * Constructor for ReportException
	 */
	public ReportException() {
	}

	/**
	 * Constructor for ReportException
	 */
	public ReportException(String message) {
		super(message);
	}

	/**
	 * Constructor for ReportException
	 */
	public ReportException(Throwable parentException) {
		super(parentException);
	}

	/**
	 * Constructor for ReportException
	 */
	public ReportException(String message, Throwable parentException) {
		super(message, parentException);
	}
}
