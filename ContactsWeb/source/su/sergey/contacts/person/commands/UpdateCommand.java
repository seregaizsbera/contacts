package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class UpdateCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		PersonPacker packer = new PersonPacker(request);
		PersonHandle handle = packer.getHandle();
		PersonAttributes attributes = packer.getAttributes();
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		delegate.updatePerson(handle, attributes);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Данные о личности обновлены");
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 2));
		return PageNames.MESSAGE_PAGE;
	}
}
