package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.RequestHistory;
import su.sergey.contacts.SessionConstants;
import su.sergey.contacts.inquiry.businessdelegate.InquiryBusinessDelegate;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;

public abstract class AbstractCommand implements Command {
    protected DAOBusinessDelegate getDAOBusinessDelegate(HttpServletRequest request) {
        return (DAOBusinessDelegate) request.getSession().getAttribute(SessionConstants.DAO_BUSINESS_DELEGATE);
    }
    
    protected InquiryBusinessDelegate getInquiryBusinessDelegate(HttpServletRequest request) {
        return (InquiryBusinessDelegate) request.getSession().getAttribute(SessionConstants.INQUIRY_BUSINESS_DELEGATE);
    }
    
    protected RequestHistory getRequestHistory(HttpServletRequest request) {
    	return (RequestHistory) request.getSession().getAttribute(SessionConstants.AN_HISTORY);
    }
    
	protected String getReturnUrl(HttpServletRequest request, int count) {
		return getRequestHistory(request).getReturnUrl(request, count);
	}
	
	protected void saveInquiryData(HttpServletRequest request, String alias) {
		request.setAttribute(alias, getInquiryBusinessDelegate(request).inquireTable(alias));
	}
}
