package su.sergey.contacts.phone;
/**
 * Home interface for Enterprise Bean: Phone
 */
public interface PhoneHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Phone
	 */
	public su.sergey.contacts.phone.Phone create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
