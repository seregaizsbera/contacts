package su.sergey.contacts.person;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: Person
 */
public interface PersonHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: Person
	 */
	public Person create() throws CreateException, RemoteException;
}
