package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.dto.MsuDepartmentData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.valueobjects.Person2;

public class ViewPersonCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		MsuDepartmentData departments[] = delegate.getMsuDepartments();
		request.setAttribute(AN_MSU_DEPARTMENTS, departments);
		Integer personId = getId(request);
		if (personId != null) {
    		PersonHandle handle = new PersonHandle(personId);
		    Person2 person = delegate.findPerson(handle);
		    request.setAttribute(AN_PERSON, person);
		}
		return PageNames.PERSON_SHOW_PERSON;
	}
}
