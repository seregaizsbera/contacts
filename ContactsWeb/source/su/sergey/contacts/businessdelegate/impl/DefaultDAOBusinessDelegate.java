package su.sergey.contacts.businessdelegate.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.InvalidValueException;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

public class DefaultDAOBusinessDelegate implements DAOBusinessDelegate {
	private final DAOSessionFacade facade;
	
    public DefaultDAOBusinessDelegate() {
    	DAOSessionFacade facadeBean = null;
    	try {
	    	Context context = new InitialContext();
	    	Object object = context.lookup(JNDINames.SESSION_FACADE_REFERENCE);
	    	DAOSessionFacadeHome facadeHomeObject = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
	    	facadeBean = facadeHomeObject.create();
    	} catch (NamingException e) {
    		e.printStackTrace();
    	} catch (CreateException e) {
    		e.printStackTrace();
    	} catch (RemoteException e) {
    		e.printStackTrace();
    	}
    	facade = facadeBean;
    }

	/**
	 * @see DAOBusinessDelegate#findDirectoryMetadata(DirectoryMetadataHandle)
	 */
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle)
			throws ContactsException {
		DirectoryMetadata result = null;
		try {
			result = facade.findDirectoryMetadata(directoryMetadataHandle);
        } catch(RemoteException e) {
            throw new ContactsException(e);
		}
		return result;
	}

	/**
	 * @see DAOBusinessDelegate#updateDirectoryMetadata(DirectoryMetadata)
	 */
	public void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
	                                    DirectoryMetadata directoryMetadata)
			throws ContactsException {
		try {
			facade.updateDirectoryMetadata(directoryMetadataHandle, directoryMetadata);
        } catch(RemoteException e) {
            throw new ContactsException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#findDirectoryRecords(DirectoryRecordSearchParameters, int, int)
	 */
	public Collection findDirectoryRecords(DirectoryRecordSearchParameters searchParameters, int start, int length) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#findDirectoryRecord(DirectoryRecordHandle)
	 */
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#addDirectoryRecord(DirectoryMetadataHandle, DirectoryRecord)
	 */
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) {}

	/**
	 * @see DAOBusinessDelegate#deleteDirectoryRecord(DirectoryRecordHandle)
	 */
	public void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {}

	/**
	 * @see DAOBusinessDelegate#getSystemProperties()
	 */
	public Collection getSystemProperties() {
		return null;
	}
	/**
	 * @see DAOBusinessDelegate#updateDirectoryRecord(DirectoryRecordHandle, DirectoryRecord)
	 */
	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) {}

	/**
	 * @see DAOBusinessDelegate#updateSystemProperty(String, String)
	 */
	public void updateSystemProperty(String name, String value) throws InvalidValueException {}
}
