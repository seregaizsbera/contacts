package su.sergey.contacts.sessionfacade;
/**
 * Home interface for Enterprise Bean: DAOSessionFacade
 */
public interface DAOSessionFacadeHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: DAOSessionFacade
	 */
	public su.sergey.contacts.sessionfacade.DAOSessionFacade create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
