package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.phone.PhonePacker;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class AddPhoneCommand extends DefaultSupplyCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		PhonePacker phonePacker = new PhonePacker(request);
		PhoneAttributes phone = phonePacker.getAttributes();
		SupplyPacker supplyPacker = new SupplyPacker(request);
		SupplyHandle supplyHandle = supplyPacker.getHandle();
		delegate.addSupplyPhone(supplyHandle, phone);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Телефон добавлен");
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 0));
		request.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Продолжить");
		return PageNames.MESSAGE_PAGE;
	}
}

