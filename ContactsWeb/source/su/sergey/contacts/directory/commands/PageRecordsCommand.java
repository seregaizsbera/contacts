package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;

public class PageRecordsCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		processRecordsPage(myRequest);
        return PageNames.DIRECTORY_SHOW_RECORDS;
	}
}
