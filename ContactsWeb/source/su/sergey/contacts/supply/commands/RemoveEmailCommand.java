package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.EmailPacker;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class RemoveEmailCommand extends DefaultSupplyCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		EmailPacker emailPacker = new EmailPacker(request);
		EmailHandle emailHandle = emailPacker.getHandle();
		SupplyPacker supplyPacker = new SupplyPacker(request);
		SupplyHandle supplyHandle = supplyPacker.getHandle();
		delegate.removeSupplyEmail(supplyHandle, emailHandle);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Адрес электронной почты удален");
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 0));
		request.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Продолжить");
		return PageNames.MESSAGE_PAGE;
	}
}
