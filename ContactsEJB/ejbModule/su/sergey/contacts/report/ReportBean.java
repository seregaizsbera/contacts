package su.sergey.contacts.report;
import java.io.File;
import java.io.Serializable;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.report.dao.ReportDAOFacade;
/**
 * Bean implementation class for Enterprise Bean: Report
 */
public class ReportBean implements SessionBean {
	private SessionContext mySessionCtx;
	private ReportDAOFacade reportDaoFacade;
	
	public File buildReport(Integer reportType, String description, Serializable parameters) throws ReportException {
		File result = reportDaoFacade.buildReport(reportType, description, (Object) parameters);
		return result;
	}
	
	/**
	 * getSessionContext
	 */
	public SessionContext getSessionContext() {
		return mySessionCtx;
	}
	
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
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
		reportDaoFacade = ReportDAOFacade.getInstance();
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
