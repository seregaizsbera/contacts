package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class CreateCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		SupplyPacker packer = new SupplyPacker(request);
		SupplyAttributes attributes = packer.getAttributes();
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		SupplyHandle handle = delegate.createSupply(attributes);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Данные об организации добавлены");
		request.setAttribute(RequestConstants.AN_NEXT_URL, request.getContextPath() + "/controller?action=supply.view&id=" + handle.getId());
		request.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Продолжить");
		request.setAttribute(RequestConstants.AN_ALTERNATE_URL, getReturnUrl(request, 1));
		request.setAttribute(RequestConstants.AN_ALTERNATE_MESSAGE, "К поиску");
		return PageNames.MESSAGE_PAGE;
	}
}
