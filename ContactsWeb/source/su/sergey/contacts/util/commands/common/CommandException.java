package su.sergey.contacts.util.commands.common;

import su.sergey.contacts.exceptions.GenericException;
import su.sergey.contacts.exceptions.*;


public class CommandException extends GenericException {

	/**
	 * Constructor for CommandException
	 */
	public CommandException(Throwable parentException) {
		super(parentException);
	}

	/**
	 * Constructor for CommandException
	 */
	public CommandException(String message, Throwable parentException) {
		super(message, parentException);
	}
	
	public CommandException(String message) {
		super(message);
	}
}
