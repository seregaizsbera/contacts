package su.sergey.contacts.person;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;

/**
 * Home interface for Enterprise Bean: PersonPageIterator
 */
public interface PersonPageIteratorHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: PersonPageIterator
	 */
	public PersonPageIterator create(PersonSearchParameters searchParameters, int pageSize) throws CreateException, RemoteException;
}
