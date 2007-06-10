package su.sergey.contacts.email;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.dao.EmailDAO;
import su.sergey.contacts.dto.EmailCreateInfo;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.EmailUpdateInfo;
import su.sergey.contacts.email.delegate.EmailToEmailData;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

/**
 * Bean implementation class for Enterprise Bean: Email
 */
public class EmailBean implements SessionBean {
	private SessionContext mySessionCtx;
	private EmailDAO emailDao;
	
	public EmailHandle createEmail(EmailAttributes email) {
		EmailCreateInfo createInfo = new EmailToEmailData(email);
		Integer emailId = emailDao.create(createInfo);
		EmailHandle result = new EmailHandle(emailId);
		return result;
	}
	
	public void updateEmail(EmailHandle handle, EmailAttributes email) {
		EmailUpdateInfo updateInfo = new EmailToEmailData(email);
		emailDao.update(handle, updateInfo);
	}
	
	public void removeEmail(EmailHandle handle) {
		emailDao.remove(handle);
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
	    ConnectionSource connectionSource = new ContainerConnectionSource();
	    emailDao = new EmailDAO(connectionSource);
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
