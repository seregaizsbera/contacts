package su.sergey.contacts.properties.businessdelegate.impl;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.Property;
import su.sergey.contacts.properties.PropertyHome;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.properties.businessdelegate.PropertyBusinessDelegate;

public class DefaultPropertyBusinessDelegate implements PropertyBusinessDelegate {
	private Property property;
	
	public DefaultPropertyBusinessDelegate(String jndiName) {
		try {
			Context context = new InitialContext();
		    Object object = context.lookup(jndiName);;
		    PropertyHome home = (PropertyHome) PortableRemoteObject.narrow(object, PropertyHome.class);
		    property = home.create();
		} catch (NamingException e) {
			throw new RuntimeDelegateException(e);
		} catch (CreateException e) {
			throw new RuntimeDelegateException(e);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see PropertyBusinessDelegate#getValue(String)
	 */
	public Object getValue(String name) throws PropertyNotFoundException {
		try {
			Object result = (Object) property.getValue(name);
			return result;
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see PropertyBusinessDelegate#setValue(String, Object)
	 */
	public void setValue(String name, Object value) throws PropertyNotFoundException {
		try {
			property.setValue(name, (Serializable) value);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see PropertyBusinessDelegate#setValue(String, String)
	 */
	public void setValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException {
		try {
			property.setValue(name, value);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
}
