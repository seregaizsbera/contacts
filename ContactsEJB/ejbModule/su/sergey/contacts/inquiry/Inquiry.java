package su.sergey.contacts.inquiry;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjects;

/**
 * Remote interface for Enterprise Bean: Inquiry
 */
public interface Inquiry extends EJBObject {
	InquiryObjects inquireTable(String alias) throws RemoteException;
	
	String[] inquireTableAliases(int scope) throws RemoteException;
	
	String getCurrentDatabase() throws RemoteException;
}
