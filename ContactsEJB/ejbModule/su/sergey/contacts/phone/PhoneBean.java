package su.sergey.contacts.phone;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.dao.PhoneDAO;
import su.sergey.contacts.dto.PhoneCreateInfo;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.PhoneUpdateInfo;
import su.sergey.contacts.phone.delegate.PhoneToPhoneData;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

/**
 * Bean implementation class for Enterprise Bean: Phone
 */
public class PhoneBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public PhoneHandle createPhone(PhoneAttributes phone) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		PhoneCreateInfo createInfo = new PhoneToPhoneData(phone);
		Integer phoneId = phoneDao.create(createInfo);
		PhoneHandle result = new PhoneHandle(phoneId);
		return result;
	}
	
	public void updatePhone(PhoneHandle handle, PhoneAttributes phone) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		PhoneUpdateInfo updateInfo = new PhoneToPhoneData(phone);
		phoneDao.update(handle, updateInfo);
	}
	
	public void removePhone(PhoneHandle handle) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		phoneDao.remove(handle);
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
