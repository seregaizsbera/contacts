package su.sergey.contacts.sessionfacade;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: DAOSessionFacade
 */
public interface DAOSessionFacadeHome extends EJBHome {
	/**
	 * Creates a default instance of Session Bean: DAOSessionFacade
	 */
	public DAOSessionFacade create() throws CreateException, RemoteException;
}
