package su.sergey.contacts.person;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.dao.EmailDAO;
import su.sergey.contacts.dao.PersonEmailsDAO;
import su.sergey.contacts.dao.PersonPhonesDAO;
import su.sergey.contacts.dao.PhoneDAO;
import su.sergey.contacts.dto.EmailCreateInfo;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PersonEmailsCreateInfo;
import su.sergey.contacts.dto.PersonEmailsHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PersonPhonesCreateInfo;
import su.sergey.contacts.dto.PersonPhonesHandle;
import su.sergey.contacts.dto.PhoneCreateInfo;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.email.delegate.EmailToEmailData;
import su.sergey.contacts.email.delegate.EmailToPersonEmailsData;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.dao.PersonDAOFacade;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.impl.DefaultPerson2;
import su.sergey.contacts.phone.delegate.PhoneToPersonPhonesData;
import su.sergey.contacts.phone.delegate.PhoneToPhoneData;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

/**
 * Bean implementation class for Enterprise Bean: Person
 */
public class PersonBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public Person2 findPerson(PersonHandle handle, boolean fullData) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		PersonAttributes attributes = daoFacade.findPerson(handle, fullData);
	    if (attributes == null) {
	    	return null;
	    }
	    Person2 result = new DefaultPerson2(handle, attributes);
		return result;
	}
	
	public PersonHandle createPerson(PersonAttributes attributes) throws MultipleFieldsValidationException {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		return daoFacade.createPerson(attributes);
	}
	
	public void updatePerson(PersonHandle handle, PersonAttributes attributes) throws MultipleFieldsValidationException {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		daoFacade.updatePerson(handle, attributes);
	}
	
	public void removePerson(PersonHandle handle) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		daoFacade.removePerson(handle);
	}
	
	public PhoneHandle addPhone(PersonHandle personHandle, PhoneAttributes phone) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		PersonPhonesDAO personPhonesDao = PersonPhonesDAO.getInstance();
		PhoneCreateInfo phoneCreateInfo = new PhoneToPhoneData(phone);
		Integer phoneId = phoneDao.create(phoneCreateInfo);
		PhoneHandle phoneHandle = new PhoneHandle(phoneId);
		PersonPhonesCreateInfo personPhonesCreateInfo = new PhoneToPersonPhonesData(personHandle, phoneHandle, phone);
		personPhonesDao.create(personPhonesCreateInfo);
		return phoneHandle;
	}
	
	public void setBasicPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		daoFacade.setBasicPhone(personHandle, phoneHandle);
	}
	
	public void removePhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		PersonPhonesDAO personPhonesDao = PersonPhonesDAO.getInstance();
		PersonPhonesHandle personPhonesHandle = new PersonPhonesHandle(personHandle.getId(), phoneHandle.getId());
		personPhonesDao.remove(personPhonesHandle);
		phoneDao.remove(phoneHandle);
	}
	
	public Phone2[] getPersonPhones(PersonHandle handle) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		return daoFacade.getPersonPhones(handle);
	}
	
	public EmailHandle addEmail(PersonHandle personHandle, EmailAttributes email) {
		EmailDAO emailDao = EmailDAO.getInstance();
		PersonEmailsDAO personEmailsDao = PersonEmailsDAO.getInstance();
		EmailCreateInfo emailCreateInfo = new EmailToEmailData(email);
		Integer emailId = emailDao.create(emailCreateInfo);
		EmailHandle emailHandle = new EmailHandle(emailId);
		PersonEmailsCreateInfo personEmailsCreateInfo = new EmailToPersonEmailsData(personHandle, emailHandle, email);
		personEmailsDao.create(personEmailsCreateInfo);
		return emailHandle;
	}
	
	public void setBasicEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		daoFacade.setBasicEmail(personHandle, emailHandle);
	}
	
	public void removeEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		EmailDAO emailDao = EmailDAO.getInstance();
		PersonEmailsDAO personEmailsDao = PersonEmailsDAO.getInstance();
		PersonEmailsHandle personEmailsHandle = new PersonEmailsHandle(personHandle.getId(), emailHandle.getId());
		personEmailsDao.remove(personEmailsHandle);
		emailDao.remove(emailHandle);
	}
	
	public Email2[] getPersonEmails(PersonHandle handle) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		return daoFacade.getPersonEmails(handle);
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
