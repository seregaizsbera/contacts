package su.sergey.contacts.supply;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.dao.EmailDAO;
import su.sergey.contacts.dao.PhoneDAO;
import su.sergey.contacts.dao.SupplyEmailsDAO;
import su.sergey.contacts.dao.SupplyPhonesDAO;
import su.sergey.contacts.dto.EmailCreateInfo;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PhoneCreateInfo;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.SupplyEmailsCreateInfo;
import su.sergey.contacts.dto.SupplyEmailsHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.dto.SupplyPhonesCreateInfo;
import su.sergey.contacts.dto.SupplyPhonesHandle;
import su.sergey.contacts.email.delegate.EmailToEmailData;
import su.sergey.contacts.email.delegate.EmailToSupplyEmailsData;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.phone.delegate.PhoneToPhoneData;
import su.sergey.contacts.phone.delegate.PhoneToSupplyPhonesData;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.supply.dao.SupplyDAOFacade;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

/**
 * Bean implementation class for Enterprise Bean: Supply
 */
public class SupplyBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public Supply2 findSupply(SupplyHandle handle, boolean fullData) {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		SupplyAttributes attributes = daoFacade.findSupply(handle, fullData);
	    if (attributes == null) {
	    	return null;
	    }
	    Supply2 result = new Supply2(handle, attributes);
		return result;
	}
	
	public SupplyHandle createSupply(SupplyAttributes attributes) throws MultipleFieldsValidationException {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		return daoFacade.createSupply(attributes);
	}
	
	public void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws MultipleFieldsValidationException {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		daoFacade.updateSupply(handle, attributes);
	}
	
	public void removeSupply(SupplyHandle handle) {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		daoFacade.removeSupply(handle);
	}
	
	public PhoneHandle addPhone(SupplyHandle supplyHandle, PhoneAttributes phone) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		SupplyPhonesDAO supplyPhonesDao = SupplyPhonesDAO.getInstance();
		PhoneCreateInfo phoneCreateInfo = new PhoneToPhoneData(phone);
		Integer phoneId = phoneDao.create(phoneCreateInfo);
		PhoneHandle phoneHandle = new PhoneHandle(phoneId);
		SupplyPhonesCreateInfo supplyPhonesCreateInfo = new PhoneToSupplyPhonesData(supplyHandle, phoneHandle);
		supplyPhonesDao.create(supplyPhonesCreateInfo);
		return phoneHandle;
	}
	
	public void removePhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		SupplyPhonesDAO supplyPhonesDao = SupplyPhonesDAO.getInstance();
		SupplyPhonesHandle supplyPhonesHandle = new SupplyPhonesHandle(supplyHandle.getId(), phoneHandle.getId());
		supplyPhonesDao.remove(supplyPhonesHandle);
		phoneDao.remove(phoneHandle);
	}
	
	public Phone2[] getSupplyPhones(SupplyHandle handle) {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		return daoFacade.getSupplyPhones(handle);
	}
	
	public EmailHandle addEmail(SupplyHandle supplyHandle, EmailAttributes email) {
		EmailDAO emailDao = EmailDAO.getInstance();
		SupplyEmailsDAO supplyEmailsDao = SupplyEmailsDAO.getInstance();
		EmailCreateInfo emailCreateInfo = new EmailToEmailData(email);
		Integer emailId = emailDao.create(emailCreateInfo);
		EmailHandle emailHandle = new EmailHandle(emailId);
		SupplyEmailsCreateInfo supplyEmailsCreateInfo = new EmailToSupplyEmailsData(supplyHandle, emailHandle);
		supplyEmailsDao.create(supplyEmailsCreateInfo);
		return emailHandle;
	}
	
	public void removeEmail(SupplyHandle supplyHandle, EmailHandle emailHandle) {
		EmailDAO emailDao = EmailDAO.getInstance();
		SupplyEmailsDAO supplyEmailsDao = SupplyEmailsDAO.getInstance();
		SupplyEmailsHandle supplyEmailsHandle = new SupplyEmailsHandle(supplyHandle.getId(), emailHandle.getId());
		supplyEmailsDao.remove(supplyEmailsHandle);
		emailDao.remove(emailHandle);
	}
	
	public Email2[] getSupplyEmails(SupplyHandle handle) {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		return daoFacade.getSupplyEmails(handle);
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
