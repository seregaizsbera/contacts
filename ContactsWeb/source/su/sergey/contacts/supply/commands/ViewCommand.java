package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;

public class ViewCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) {
		String result = PageNames.SUPPLY_SHOW_SUPPLY;
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
  		SupplyHandle handle = new SupplyPacker(request).getHandle();
  		if (handle != null) {
            Supply2 supply = delegate.findSupply(handle);
            if (supply != null) {
         		request.setAttribute(AN_SUPPLY, supply);
            } else {
            	request.setAttribute(RequestConstants.AN_MESSAGE, "Организация не найдена");
            	result = PageNames.MESSAGE_PAGE;
            }
  		}
		return result;
	}
}
