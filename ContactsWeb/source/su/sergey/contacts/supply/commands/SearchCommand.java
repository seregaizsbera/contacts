package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.util.InquiryAliases;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
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
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		HttpSession session = request.getSession();
		session.setAttribute(AN_SEARCH_PARAMETERS, searchParameters);
		
		SupplyPageIteratorBusinessDelegate suppliesIterator = (SupplyPageIteratorBusinessDelegate) session.getAttribute(ANS_SUPPLIES_ITERATOR);
		if (suppliesIterator != null) {
			suppliesIterator.freeResources();
		}
		suppliesIterator = new DefaultSupplyPageIteratorBusinessDelegate(JNDINames.SUPPLY_PAGE_ITERATOR_REFERENCE, searchParameters, DEFAULT_PAGE_SIZE);
		session.setAttribute(ANS_SUPPLIES_ITERATOR, suppliesIterator);
		Supply2 supplies[] = suppliesIterator.current();
		if (supplies != null) {
			request.setAttribute(AN_SUPPLIES, supplies);
            PageIterationInfo iterationInfo = new PageIterationInfo(suppliesIterator.getNumberOfPages(),
                                                                    suppliesIterator.getCurrentPage(),
                                                                    suppliesIterator.getPageSize());
            request.setAttribute(AN_ITERATION_INFO, iterationInfo);
		}
		saveInquiryData(request, InquiryAliases.SUPPLY_KINDS);
		saveInquiryData(request, InquiryAliases.SUPPLY_KINDS_AS_HASH);
		return PageNames.SUPPLY_SEARCH_SUPPLY;
	}
}
