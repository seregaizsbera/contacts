package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;

public class SearchPersonPageCommand extends AbstractPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		return PageNames.PERSON_SEARCH_PERSON;
	}
}

