package su.sergey.contacts.properties;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * Remote interface for Enterprise Bean: Property
 */
public interface Property extends EJBObject {
	Serializable getValue(String name) throws RemoteException, PropertyNotFoundException;
	
    void setValue(String name, Serializable value) throws RemoteException, PropertyNotFoundException;
    
    void setValue(String name, String value) throws RemoteException, InvalidPropertyValueException, PropertyNotFoundException;
}
