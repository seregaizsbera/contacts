package su.sergey.contacts.person;

import java.rmi.RemoteException;

import su.sergey.contacts.pageiterator.PageIteratorBase;
import su.sergey.contacts.person.valueobjects.Person2;

/**
 * Remote interface for Enterprise Bean: PersonPageIterator
 */
public interface PersonPageIterator extends PageIteratorBase {
	
	Person2[] prev() throws RemoteException;
	
	Person2[] current() throws RemoteException;
	
	Person2[] next() throws RemoteException;
	
	Person2[] goTo(int page) throws RemoteException;

}
