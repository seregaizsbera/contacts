package su.sergey.contacts.inquiry;

import java.rmi.RemoteException;
import java.util.HashMap;

import javax.ejb.EJBObject;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

/**
 * Remote interface for Enterprise Bean: Inquiry
 */
public interface Inquiry extends EJBObject {
	InquiryObject[] inquireTableAsIds(String tableName) throws RemoteException;
	InquiryObject[] inquireTableAsNames(String tableName) throws RemoteException;
	HashMap inquireTableAsHash(String tableName) throws RemoteException;
}
