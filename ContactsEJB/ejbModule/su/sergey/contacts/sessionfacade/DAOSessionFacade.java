package su.sergey.contacts.sessionfacade;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.exceptions.ContactsException;

/**
 * Remote interface for Enterprise Bean: DAOSessionFacade
 */
public interface DAOSessionFacade extends EJBObject {
	DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle)
			throws RemoteException, ContactsException;
	
	void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
                                 DirectoryMetadata directoryMetadata)
			throws RemoteException, ContactsException;
			
	DirectoryRecord findDirectoryRecord(DirectoryRecordHandle handle)
			throws RemoteException, ContactsException;
			
	void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord)
			throws RemoteException, ContactsException;

	void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle)
			throws RemoteException, ContactsException;

	void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord)
			throws RemoteException, ContactsException;
}
