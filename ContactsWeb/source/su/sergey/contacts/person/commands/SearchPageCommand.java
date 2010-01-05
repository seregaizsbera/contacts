package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.businessdelegate.PersonPageIteratorBusinessDelegate;

public class SearchPageCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		HttpSession session = request.getSession(false);
		PersonPageIteratorBusinessDelegate personsIterator = (PersonPageIteratorBusinessDelegate) session.getAttribute(ANS_PERSONS_ITERATOR);
		if (personsIterator != null) {
			personsIterator.freeResources();
		}
		session.removeAttribute(AN_SEARCH_PARAMETERS);
		return PageNames.PERSON_SEARCH_PERSON;
	}
}
