package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.supply.businessdelegate.SupplyPageIteratorBusinessDelegate;

public class SearchPageCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		HttpSession session = request.getSession();
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		SupplyPageIteratorBusinessDelegate suppliesIterator = (SupplyPageIteratorBusinessDelegate) session.getAttribute(ANS_SUPPLIES_ITERATOR);
		if (suppliesIterator != null) {
			suppliesIterator.freeResources();
		}
		session.removeAttribute(AN_SEARCH_PARAMETERS);
		request.setAttribute("supplyKinds_2", delegate.inquireTableAsNames(TableNames.SUPPLY_KINDS));
		return PageNames.SUPPLY_SEARCH_SUPPLY;
	}
}
