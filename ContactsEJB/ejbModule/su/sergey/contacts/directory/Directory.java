package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.handles.DirectoryRecordHandle;

/**
 * Remote interface for Enterprise Bean: Directory
 */
public interface Directory extends EJBObject {
	DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle) throws RemoteException;
	
	void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
	                             DirectoryMetadata directoryMetadata) throws RemoteException;
	                             
	DirectoryRecord findDirectoryRecord(DirectoryRecordHandle handle)
			throws RemoteException;
			
	void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord)
			throws RemoteException;
			
	void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle)
			throws RemoteException;

	void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord)
			throws RemoteException;
}
