package su.sergey.contacts.email;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

/**
 * Remote interface for Enterprise Bean: Email
 */
public interface Email extends EJBObject {
	EmailHandle createEmail(EmailAttributes email) throws RemoteException;
	
	void updateEmail(EmailHandle handle, EmailAttributes email) throws RemoteException;
	
	void removeEmail(EmailHandle handle) throws RemoteException;
}
