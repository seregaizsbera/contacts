package su.sergey.contacts.call;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: Call
 */
public interface CallHome extends EJBHome {
	
	/**
	 * Creates a default instance of Session Bean: Call
	 */
	public Call create() throws CreateException, RemoteException;
}
