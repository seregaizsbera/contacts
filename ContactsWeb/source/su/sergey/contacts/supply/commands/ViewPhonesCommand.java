package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.phone.PhoneParameters;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class ViewPhonesCommand extends DefaultSupplyCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws InvalidParameterException {
		SupplyPacker packer = new SupplyPacker(request);
		SupplyHandle handle = packer.getHandle();
		DAOBusinessDelegate businessDelegate = getDAOBusinessDelegate(request);
		Phone2 phones[] = businessDelegate.getSupplyPhones(handle);
		request.setAttribute(PhoneParameters.AN_PHONES, phones);
		request.setAttribute(AN_SUPPLY_HANDLE, handle);
		return PageNames.SUPPLY_PHONES_PAGE;
	}
}
