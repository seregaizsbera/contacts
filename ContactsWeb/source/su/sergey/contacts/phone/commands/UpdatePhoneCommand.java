package su.sergey.contacts.phone.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.phone.PhonePacker;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class UpdatePhoneCommand extends AbstractCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		PhonePacker packer = new PhonePacker(request);
		PhoneHandle handle = packer.getHandle();
		PhoneAttributes phone = packer.getAttributes();
		delegate.updatePhone(handle, phone);
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 1));
		request.setAttribute(RequestConstants.AN_MESSAGE, "Телефон изменен");
		return PageNames.MESSAGE_PAGE;
	}
}
