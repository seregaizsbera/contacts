package su.sergey.contacts.inquiry;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

/**
 * Remote interface for Enterprise Bean: Inquiry
 */
public interface Inquiry extends EJBObject {
	InquiryObject[] inquireTable(String tableName) throws RemoteException;
}
