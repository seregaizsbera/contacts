package su.sergey.contacts.inquiry;

import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

/**
 * Bean implementation class for Enterprise Bean: Inquiry
 */
public class InquiryBean implements SessionBean {
	private SessionContext mySessionCtx;
	private InquiryDAOFacade inquiryDaoFacade;

	public InquiryObject[] inquireTableAsIds(String tableName) {
		if (!TableNames.getTableNames().containsKey(tableName)) {
			throw new EJBException("Недопустимое имя таблицы");
		}
		return inquiryDaoFacade.inquireTableAsIds(tableName);
	}

	public InquiryObject[] inquireTableAsNames(String tableName) {
		if (!TableNames.getTableNames().containsKey(tableName)) {
			throw new EJBException("Недопустимое имя таблицы");
		}
		return inquiryDaoFacade.inquireTableAsNames(tableName);
	}

	public HashMap inquireTableAsHash(String tableName) {
		if (!TableNames.getTableNames().containsKey(tableName)) {
			throw new EJBException("Недопустимое имя таблицы");
		}
		return inquiryDaoFacade.inquireTableAsHash(tableName);
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