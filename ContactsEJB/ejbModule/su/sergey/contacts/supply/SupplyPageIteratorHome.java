package su.sergey.contacts.supply;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;

/**
 * Home interface for Enterprise Bean: SupplyPageIterator
 */
public interface SupplyPageIteratorHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: SupplyPageIterator
	 */
	public SupplyPageIterator create(SupplySearchParameters searchParameters, int pageSize) throws CreateException, RemoteException;
}
