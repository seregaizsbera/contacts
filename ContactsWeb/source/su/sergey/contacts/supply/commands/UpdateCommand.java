package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class UpdateCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		SupplyPacker packer = new SupplyPacker(request);
		SupplyHandle handle = packer.getHandle();
		SupplyAttributes attributes = packer.getAttributes();
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		delegate.updateSupply(handle, attributes);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Данные об организации обновлены");
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 2));
		return PageNames.MESSAGE_PAGE;
	}
}
