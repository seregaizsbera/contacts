package su.sergey.contacts.query;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: Query
 */
public interface QueryHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: Query
	 */
	public Query create() throws CreateException, RemoteException;
}
