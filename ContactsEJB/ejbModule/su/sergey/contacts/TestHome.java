package su.sergey.contacts;
/**
 * Home interface for Enterprise Bean: Test
 */
public interface TestHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Test
	 */
	public su.sergey.contacts.Test create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
