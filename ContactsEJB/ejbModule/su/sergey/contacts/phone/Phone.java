package su.sergey.contacts.phone;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

/**
 * Remote interface for Enterprise Bean: Phone
 */
public interface Phone extends javax.ejb.EJBObject {
	PhoneHandle createPhone(PhoneAttributes phone) throws RemoteException;
	
	void updatePhone(PhoneHandle handle, PhoneAttributes phone) throws RemoteException;
	
	void removePhone(PhoneHandle handle) throws RemoteException;
}
