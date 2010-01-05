package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.InquiryAliases;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.supply.businessdelegate.SupplyPageIteratorBusinessDelegate;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;

public class PageSearchCommand extends DefaultSupplyCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
        SupplyPageIteratorBusinessDelegate suppliesIterator = (SupplyPageIteratorBusinessDelegate)
                request.getSession(false).getAttribute(ANS_SUPPLIES_ITERATOR);
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
        Integer pageNumber = getPage(request);
        Supply2[] supplies;
        if (pageNumber == null) {
        	supplies = suppliesIterator.current();
        } else {
        	supplies = suppliesIterator.goTo(pageNumber.intValue());
        }
        request.setAttribute(AN_SUPPLIES, supplies);
        PageIterationInfo iterationInfo = new PageIterationInfo(
                suppliesIterator.getNumberOfPages(),
                suppliesIterator.getCurrentPage(),
                suppliesIterator.getPageSize());
        request.setAttribute(AN_ITERATION_INFO, iterationInfo);
		saveInquiryData(request, InquiryAliases.SUPPLY_KINDS);
		saveInquiryData(request, InquiryAliases.SUPPLY_KINDS_AS_HASH);
		return PageNames.SUPPLY_SEARCH_SUPPLY;
	}
}
