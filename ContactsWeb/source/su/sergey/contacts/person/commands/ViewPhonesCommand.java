package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.phone.PhoneParameters;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class ViewPhonesCommand extends DefaultPersonCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		PersonPacker packer = new PersonPacker(request);
		PersonHandle handle = packer.getHandle();
		DAOBusinessDelegate businessDelegate = getDAOBusinessDelegate(request);
		Phone2 phones[] = businessDelegate.getPersonPhones(handle);
		request.setAttribute(PhoneParameters.AN_ENTITY, "person");
		request.setAttribute(PhoneParameters.AN_PHONES, phones);
		request.setAttribute(AN_PERSON_HANDLE, handle);
		return PageNames.PHONES_PAGE;
	}
}
