package su.sergey.contacts.email.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.email.EmailPacker;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class UpdateEmailCommand extends AbstractCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		EmailPacker packer = new EmailPacker(request);
		EmailHandle handle = packer.getHandle();
		EmailAttributes email = packer.getAttributes();
		delegate.updateEmail(handle, email);
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 0));
		request.setAttribute(RequestConstants.AN_MESSAGE, "Адрес электронной почты изменен");
		return PageNames.MESSAGE_PAGE;
	}
}
