package su.sergey.contacts.inquiry;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjects;

/**
 * Bean implementation class for Enterprise Bean: Inquiry
 */
public class InquiryBean implements SessionBean {
	private SessionContext mySessionCtx;
	private InquiryDAOFacade inquiryDaoFacade;

	public InquiryObjects inquireTable(String alias) {
		return inquiryDaoFacade.inquireTable(alias, mySessionCtx);
	}

	public String[] inquireTableAliases(int scope) {
		return inquiryDaoFacade.inquireTableAliases(scope);
	}
	
	public String getCurrentDatabase() {
		String url = inquiryDaoFacade.getCurrentDatabase();
		try {
			RE regexp = new RE("^([\\w]+):([\\w]+):([\\w]+)");
			String result;
			if (regexp.match(url)) {
				result = regexp.getParen(3);
			} else {
				result = null;
			}
			return result;
		} catch (RESyntaxException e) {
			throw new EJBException(e);
		}
	}

	//--------------------------------------------------------------------------

	/**
	 * getSessionContext
	 */
	public SessionContext getSessionContext() {
		return mySessionCtx;
	}

	/**
	 * setSessionContext
	 */
	public void setSessionContext(SessionContext ctx) {
		mySessionCtx = ctx;
	}

	/**
	 * ejbActivate
	 */
	public void ejbActivate() {}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws CreateException {
		inquiryDaoFacade = InquiryDAOFacade.getInstance();
	}

	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {}
}