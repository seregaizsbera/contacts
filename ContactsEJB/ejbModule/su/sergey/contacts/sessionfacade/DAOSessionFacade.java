package su.sergey.contacts.sessionfacade;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

/**
 * Remote interface for Enterprise Bean: DAOSessionFacade
 */
public interface DAOSessionFacade extends EJBObject {
	DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle)
			throws RemoteException, ContactsException;
	
	void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
                                 DirectoryMetadata directoryMetadata)
			throws RemoteException, ContactsException;
}
