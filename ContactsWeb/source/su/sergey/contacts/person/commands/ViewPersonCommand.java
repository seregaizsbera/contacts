package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.person.valueobjects.Person2;

public class ViewPersonCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		request.setAttribute(AN_SHNIPPERS, delegate.inquireTable(TableNames.SHNIPPERS));
  		PersonHandle handle = new PersonPacker(request).getHandle();
  		if (handle != null) {
            Person2 person = delegate.findPerson(handle);
     		request.setAttribute(AN_PERSON, person);
  		}
		return PageNames.PERSON_SHOW_PERSON;
	}
}
