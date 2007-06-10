package su.sergey.contacts.properties;

import java.io.Serializable;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.properties.dao.SystemPropertyDAOFacade;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

/**
 * Bean implementation class for Enterprise Bean: Property
 */
public class PropertyBean implements SessionBean {
	private SessionContext mySessionCtx;
	private SystemPropertyDAOFacade propertyDao;
	
	public Serializable getValue(String name) throws PropertyNotFoundException {
		Object value = propertyDao.getValue(name);
		Serializable result = (Serializable) value;
		return result;
	}
	
    public void setValue(String name, Serializable value) throws PropertyNotFoundException {
    	propertyDao.setValue(name, value);
    }
    
    public void setValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException {
    	propertyDao.setValue(name, value);
    }
    
    //--------------------------------------------------------------------------------------------
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
		propertyDao = new SystemPropertyDAOFacade(connectionSource);
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
