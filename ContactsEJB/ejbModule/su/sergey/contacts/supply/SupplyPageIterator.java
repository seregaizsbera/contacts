package su.sergey.contacts.supply;

import java.rmi.RemoteException;

import su.sergey.contacts.pageiterator.PageIteratorBase;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.util.dao.DAOException;

/**
 * Remote interface for Enterprise Bean: SupplyPageIterator
 */
public interface SupplyPageIterator extends PageIteratorBase {
	Supply2[] prev() throws DAOException, RemoteException;
	
	Supply2[] current() throws DAOException, RemoteException;
	
	Supply2[] next() throws DAOException, RemoteException;
	
	Supply2[] goTo(int page) throws DAOException, RemoteException;
}
