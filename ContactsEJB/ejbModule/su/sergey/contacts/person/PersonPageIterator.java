package su.sergey.contacts.person;

import java.rmi.RemoteException;

import su.sergey.contacts.pageiterator.PageIteratorBase;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.util.dao.DAOException;

/**
 * Remote interface for Enterprise Bean: PersonPageIterator
 */
public interface PersonPageIterator extends PageIteratorBase {
	Person2[] prev() throws DAOException, RemoteException;
	
	Person2[] current() throws DAOException, RemoteException;
	
	Person2[] next() throws DAOException, RemoteException;
	
	Person2[] goTo(int page) throws DAOException, RemoteException;
}
