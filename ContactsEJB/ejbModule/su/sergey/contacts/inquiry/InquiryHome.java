package su.sergey.contacts.inquiry;
/**
 * Home interface for Enterprise Bean: Inquiry
 */
public interface InquiryHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Inquiry
	 */
	public su.sergey.contacts.inquiry.Inquiry create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
