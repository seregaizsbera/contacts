package su.sergey.contacts.person;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.dao.PersonDAOFacade;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.impl.DefaultPerson2;

/**
 * Bean implementation class for Enterprise Bean: Person
 */
public class PersonBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public Person2 findPerson(PersonHandle handle) {
		PersonDAOFacade daoFacade = PersonDAOFacade.getInstance();
		PersonAttributes attributes = daoFacade.findPerson(handle);
	    if (attributes == null) {
	    	return null;
	    }
	    Person2 result = new DefaultPerson2(handle, attributes);
		return result;
	}
	
	public PersonHandle createPerson(PersonAttributes attributes) throws MultipleFieldsValidationException {
		return null;
	}
	
	public void updatePerson(PersonHandle handle, PersonAttributes attributes) throws MultipleFieldsValidationException {
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
