package su.sergey.contacts.report;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * Remote interface for Enterprise Bean: Report
 */
public interface Report extends EJBObject {
	File buildReport(Integer reportType, String description, Serializable parameters) throws RemoteException, ReportException;
}
