package su.sergey.contacts.person;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import su.sergey.contacts.person.searchparamters.PersonSearchParameters;

/**
 * Home interface for Enterprise Bean: PersonsPageIterator
 */
public interface PersonsPageIteratorHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: PersonsPageIterator
	 */
	public PersonsPageIterator create(PersonSearchParameters searchParameters) throws CreateException, RemoteException;
}
