package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.JNDINamesForWeb;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.supply.businessdelegate.SupplyPageIteratorBusinessDelegate;
import su.sergey.contacts.supply.businessdelegate.impl.DefaultSupplyPageIteratorBusinessDelegate;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;

public class SearchCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws InvalidParameterException {
		SupplySearchParameters searchParameters = new SupplyPacker(request).getSearchParameters();
		HttpSession session = request.getSession();
		session.setAttribute(AN_SEARCH_PARAMETERS, searchParameters);
		
		SupplyPageIteratorBusinessDelegate suppliesIterator = (SupplyPageIteratorBusinessDelegate) session.getAttribute(ANS_SUPPLIES_ITERATOR);
		if (suppliesIterator != null) {
			suppliesIterator.freeResources();
		}
		suppliesIterator = new DefaultSupplyPageIteratorBusinessDelegate(JNDINamesForWeb.SUPPLY_PAGE_ITERATOR_REFERENCE, searchParameters, DEFAULT_PAGE_SIZE);
		session.setAttribute(ANS_SUPPLIES_ITERATOR, suppliesIterator);
		Supply2 supplies[] = suppliesIterator.current();
		if (supplies != null) {
			request.setAttribute(AN_SUPPLIES, supplies);
            PageIterationInfo iterationInfo = new PageIterationInfo(suppliesIterator.getNumberOfPages(),
                                                                    suppliesIterator.getCurrentPage(),
                                                                    suppliesIterator.getPageSize());
            request.setAttribute(AN_ITERATION_INFO, iterationInfo);
		}
		return PageNames.SUPPLY_SEARCH_SUPPLY;
	}
}