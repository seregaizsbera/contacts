package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.person.businessdelegate.PersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.businessdelegate.impl.DefaultPersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;

public class SearchPersonCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		PersonSearchParameters searchParameters = new PersonPacker(request).getSearchParameters();
		HttpSession session = request.getSession();
		session.setAttribute(AN_SEARCH_PARAMETERS, searchParameters);
		
		PersonPageIteratorBusinessDelegate personsIterator = (PersonPageIteratorBusinessDelegate) session.getAttribute(ANS_PERSONS_ITERATOR);
		if (personsIterator != null) {
			personsIterator.freeResources();
		}
		personsIterator = new DefaultPersonPageIteratorBusinessDelegate(searchParameters, DEFAULT_PAGE_SIZE);
		session.setAttribute(ANS_PERSONS_ITERATOR, personsIterator);
		Person2 persons[] = personsIterator.current();
		if (persons != null && persons.length != 0) {
			request.setAttribute(AN_PERSONS, persons);
            PageIterationInfo iterationInfo = new PageIterationInfo(personsIterator.getNumberOfPages(),
                                                                    personsIterator.getCurrentPage(),
                                                                    personsIterator.getPageSize());
            request.setAttribute(AN_ITERATION_INFO, iterationInfo);
		}
		return PageNames.PERSON_SEARCH_PERSON;
	}
}
