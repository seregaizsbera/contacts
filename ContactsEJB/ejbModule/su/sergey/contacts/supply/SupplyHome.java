package su.sergey.contacts.supply;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: Supply
 */
public interface SupplyHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: Supply
	 */
	public Supply create() throws CreateException, RemoteException;
}
