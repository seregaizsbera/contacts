package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;

public class RemoveCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		SupplyPacker packer = new SupplyPacker(request);
		SupplyHandle handle = packer.getHandle();
		DAOBusinessDelegate businessDelegate = getDAOBusinessDelegate(request);
		businessDelegate.removeSupply(handle);
		request.setAttribute(RequestConstants.AN_MESSAGE, "Организация удалена");
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 2));
		return PageNames.MESSAGE_PAGE;
	}
}
