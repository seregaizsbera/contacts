package su.sergey.contacts.report;
/**
 * Home interface for Enterprise Bean: Report
 */
public interface ReportHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: Report
	 */
	public su.sergey.contacts.report.Report create() throws javax.ejb.CreateException, java.rmi.RemoteException;
}
