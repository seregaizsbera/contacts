package su.sergey.contacts.inquiry;

import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

/**
 * Bean implementation class for Enterprise Bean: Inquiry
 */
public class InquiryBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public InquiryObject[] inquireTableAsIds(String tableName) {
		if (!TableNames.getTableNames().containsKey(tableName)) {
			throw new EJBException("Недопустимое имя таблицы");
		}
		return InquiryDAOFacade.getInstance().inquireTableAsIds(tableName);
	}
	
	public InquiryObject[] inquireTableAsNames(String tableName) {
		if (!TableNames.getTableNames().containsKey(tableName)) {
			throw new EJBException("Недопустимое имя таблицы");
		}
		return InquiryDAOFacade.getInstance().inquireTableAsNames(tableName);
	}
	
	public HashMap inquireTableAsHash(String tableName) {
		if (!TableNames.getTableNames().containsKey(tableName)) {
			throw new EJBException("Недопустимое имя таблицы");
		}
		return InquiryDAOFacade.getInstance().inquireTableAsHash(tableName);
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
	public void ejbActivate() {
	}
	
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws CreateException {
	}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
}
