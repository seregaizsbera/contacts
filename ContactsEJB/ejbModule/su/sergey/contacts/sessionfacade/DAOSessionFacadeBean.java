package su.sergey.contacts.sessionfacade;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.directory.Directory;
import su.sergey.contacts.directory.DirectoryHome;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.ExceptionUtil;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.handles.DirectoryRecordHandle;

/**
 * Bean implementation class for Enterprise Bean: DAOSessionFacade
 */
public class DAOSessionFacadeBean implements SessionBean {
	private SessionContext mySessionCtx;
	private Directory directory;
	
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle)
			throws ContactsException {
		DirectoryMetadata result = null;
		try {
			result = directory.findDirectoryMetadata(handle);
	    } catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
		return result;
	}
	
	public void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
	                                    DirectoryMetadata directoryMetadata) throws ContactsException {
	    try {
	    	directory.updateDirectoryMetadata(directoryMetadataHandle, directoryMetadata);
	    } catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
	    }
    }
    
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle handle)
			throws ContactsException {
		try {
			DirectoryRecord result = directory.findDirectoryRecord(handle);
			return result;
		} catch (RemoteException e) {
			String message = ExceptionUtil.extractShortMessage(e);
			throw new ContactsException(message, e);
		}
	}
	
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord)
			throws ContactsException {
		try {
			directory.addDirectoryRecord(directoryMetadataHandle, directoryRecord);
		} catch (RemoteException e) {
			String message = ExceptionUtil.extractShortMessage(e);
			throw new ContactsException(message, e);
		}
	}
	
	public void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			directory.deleteDirectoryRecord(directoryRecordHandle);
		} catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}

	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord)
			throws ContactsException {
		try {
			directory.updateDirectoryRecord(directoryRecordHandle, directoryRecord);
		} catch (RemoteException e) {
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}
	
	//---------------------------------------------------------------------------------------
			
	/**
	 * getSessionContext
	 */
	public SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(SessionContext ctx) {
		mySessionCtx = ctx;
	}
	
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {}
	
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws CreateException {
		try {
			Context context = new InitialContext();
			Object object = context.lookup(JNDINames.DIRECTORY_BEAN);
			DirectoryHome directoryHome = (DirectoryHome) PortableRemoteObject.narrow(object, DirectoryHome.class);
			directory = directoryHome.create();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {}
}
