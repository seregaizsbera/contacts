package su.sergey.contacts.supply;

import java.rmi.RemoteException;

import su.sergey.contacts.pageiterator.PageIteratorBase;
import su.sergey.contacts.supply.valueobjects.Supply2;

/**
 * Remote interface for Enterprise Bean: SupplyPageIterator
 */
public interface SupplyPageIterator extends PageIteratorBase {

    Supply2[] prev() throws RemoteException;

    Supply2[] current() throws RemoteException;

    Supply2[] next() throws RemoteException;

    Supply2[] goTo(int page) throws RemoteException;
	
}
