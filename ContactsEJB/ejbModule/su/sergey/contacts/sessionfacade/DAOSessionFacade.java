package su.sergey.contacts.sessionfacade;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.EJBObject;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.query.valueobjects.QueryResult;

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
			
	QueryResult performQuery(String sql) throws RemoteException;
	
	String[] getLastQueries(int maxNumberOfQueries) throws RemoteException;
	
	PersonAttributes findPerson(PersonHandle handle) throws RemoteException;
	
	PersonHandle createPerson(PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void updatePerson(PersonHandle handle, PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	Properties getPhoneTypes() throws RemoteException;
}
