package su.sergey.contacts.email;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: Email
 */
public interface EmailHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: Email
	 */
	Email create() throws CreateException, RemoteException;
}
