package su.sergey.contacts.businessdelegate.impl;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINamesForWeb;
import su.sergey.contacts.RuntimeDelegateException;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.dto.MsuDepartmentData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.ExceptionUtil;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;

public class DefaultDAOBusinessDelegate implements DAOBusinessDelegate {
	private final DAOSessionFacade facade;
	
    public DefaultDAOBusinessDelegate() {
    	DAOSessionFacade facadeBean = null;
    	try {
	    	Context context = new InitialContext();
	    	Object object = context.lookup(JNDINamesForWeb.SESSION_FACADE_REFERENCE);
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
		try {
			DirectoryMetadata result = facade.findDirectoryMetadata(directoryMetadataHandle);
			return result;
        } catch(RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
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
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#findDirectoryRecord(DirectoryRecordHandle)
	 */
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			return facade.findDirectoryRecord(directoryRecordHandle);
		} catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#addDirectoryRecord(DirectoryMetadataHandle, DirectoryRecord)
	 */
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord)
			throws ContactsException {
		try {
			facade.addDirectoryRecord(directoryMetadataHandle, directoryRecord);
		} catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#deleteDirectoryRecord(DirectoryRecordHandle)
	 */
	public void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			facade.deleteDirectoryRecord(directoryRecordHandle);
		} catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#updateDirectoryRecord(DirectoryRecordHandle, DirectoryRecord)
	 */
	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord)
			throws ContactsException {
		try {
			facade.updateDirectoryRecord(directoryRecordHandle, directoryRecord);
		} catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#createPerson(PersonAttributes)
	 */
	public PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#updatePerson(PersonHandle, PersonAttributes)
	 */
	public void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException {
	}

	/**
	 * @see DAOBusinessDelegate#findPerson(PersonHandle)
	 */
	public Person2 findPerson(PersonHandle handle) {
		try {
			return facade.findPerson(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#performQuery(String)
	 */
	public QueryResult performQuery(String sql) {
		try {
			return facade.performQuery(sql);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#getLastQueries()
	 */
	public String[] getLastQueries() {
		try {
			return facade.getLastQueries(15);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#getMsuDepartments()
	 */
	public MsuDepartmentData[] getMsuDepartments() {
		try {
    		return facade.getMsuDepartments();
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#inquireTable(String)
	 */
	public InquiryObject[] inquireTable(String tableName) {
		try {
			return facade.inquireTable(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
}
