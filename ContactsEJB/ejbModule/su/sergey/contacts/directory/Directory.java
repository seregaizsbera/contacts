package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

/**
 * Remote interface for Enterprise Bean: Directory
 */
public interface Directory extends EJBObject {
	DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle) throws RemoteException;
	
	void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
	                             DirectoryMetadata directoryMetadata) throws RemoteException;
}
