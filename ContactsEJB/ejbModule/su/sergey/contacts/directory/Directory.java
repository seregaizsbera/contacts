package su.sergey.contacts.directory;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.EJBObject;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;

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
			
	Properties getPhoneTypes() throws RemoteException;
}
