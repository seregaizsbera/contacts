package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;

public class RemoveCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		PersonPacker packer = new PersonPacker(request);
		PersonHandle handle = packer.getHandle();
		DAOBusinessDelegate businessDelegate = getDAOBusinessDelegate(request);
		businessDelegate.removePerson(handle);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Личность удалена");
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 2));
		return PageNames.MESSAGE_PAGE;
	}
}
