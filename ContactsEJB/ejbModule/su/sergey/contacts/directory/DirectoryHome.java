package su.sergey.contacts.directory;
/**
 * Home interface for Enterprise Bean: Directory
 */
public interface DirectoryHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Directory
	 */
	public su.sergey.contacts.directory.Directory create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
