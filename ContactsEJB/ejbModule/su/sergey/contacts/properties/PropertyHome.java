package su.sergey.contacts.properties;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: Property
 */
public interface PropertyHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: Property
	 */
	public Property create() throws CreateException, RemoteException;
}
