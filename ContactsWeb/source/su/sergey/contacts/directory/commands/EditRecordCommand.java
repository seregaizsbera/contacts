package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.exceptions.ContactsException;

public class EditRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		return null;
	}

}

