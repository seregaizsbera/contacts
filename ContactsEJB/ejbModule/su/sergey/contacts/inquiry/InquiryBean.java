package su.sergey.contacts.inquiry;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.dto.MsuDepartmentData;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

/**
 * Bean implementation class for Enterprise Bean: Inquiry
 */
public class InquiryBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public MsuDepartmentData[] getMsuDepartments() {
		return InquiryDAOFacade.getInstance().getMsuDepartments();
	}
	
	public InquiryObject[] inquireTable(String tableName) {
		return InquiryDAOFacade.getInstance().inquireTable(tableName);
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
