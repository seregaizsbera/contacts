package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.businessdelegate.PersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;

public class PageSearchCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
        PersonPageIteratorBusinessDelegate personsIterator = (PersonPageIteratorBusinessDelegate)
                request.getSession().getAttribute(ANS_PERSONS_ITERATOR);
        Integer pageNumber = getPage(request);
        Person2[] persons;
        if (pageNumber == null) {
        	persons = personsIterator.current();
        } else {
        	persons = personsIterator.goTo(pageNumber.intValue());
        }
        request.setAttribute(AN_PERSONS, persons);
        PageIterationInfo iterationInfo = new PageIterationInfo(
                personsIterator.getNumberOfPages(),
                personsIterator.getCurrentPage(),
                personsIterator.getPageSize());
        request.setAttribute(AN_ITERATION_INFO, iterationInfo);
		return PageNames.PERSON_SEARCH_PERSON;
	}
}
