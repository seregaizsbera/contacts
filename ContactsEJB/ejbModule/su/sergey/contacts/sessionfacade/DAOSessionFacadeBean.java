package su.sergey.contacts.sessionfacade;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.directory.Directory;
import su.sergey.contacts.directory.DirectoryHome;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.ExceptionUtil;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.Person;
import su.sergey.contacts.person.PersonHome;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.query.Query;
import su.sergey.contacts.query.QueryHome;
import su.sergey.contacts.query.valueobjects.QueryResult;

/**
 * Bean implementation class for Enterprise Bean: DAOSessionFacade
 */
public class DAOSessionFacadeBean implements SessionBean {
	private SessionContext mySessionCtx;
	private Directory directory;
	private Query query;
	private Person person;
	
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle)
			throws ContactsException {
		DirectoryMetadata result = null;
		try {
			result = directory.findDirectoryMetadata(handle);
	    } catch (RemoteException e) {
			mySessionCtx.setRollbackOnly();
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
			mySessionCtx.setRollbackOnly();
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
			mySessionCtx.setRollbackOnly();
			String message = ExceptionUtil.extractShortMessage(e);
			throw new ContactsException(message, e);
		}
	}
	
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord)
			throws ContactsException {
		try {
			directory.addDirectoryRecord(directoryMetadataHandle, directoryRecord);
		} catch (RemoteException e) {
			mySessionCtx.setRollbackOnly();
			String message = ExceptionUtil.extractShortMessage(e);
			throw new ContactsException(message, e);
		}
	}
	
	public void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			directory.deleteDirectoryRecord(directoryRecordHandle);
		} catch (RemoteException e) {
			mySessionCtx.setRollbackOnly();
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}

	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord)
			throws ContactsException {
		try {
			directory.updateDirectoryRecord(directoryRecordHandle, directoryRecord);
		} catch (RemoteException e) {
			mySessionCtx.setRollbackOnly();
	    	String message = ExceptionUtil.extractShortMessage(e);
	    	throw new ContactsException(message, e);
		}
	}
	
	public QueryResult performQuery(String sql) {
		try {
			return query.performQuery(sql);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public String[] getLastQueries(int maxNumberOfQueries) {
		try {
			return query.getLastQueries(maxNumberOfQueries);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public PersonAttributes findPerson(PersonHandle handle) {
		try {
			return person.findPerson(handle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public PersonHandle createPerson(PersonAttributes attributes) throws MultipleFieldsValidationException {
		try {
			return person.createPerson(attributes);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void updatePerson(PersonHandle handle, PersonAttributes attributes) throws MultipleFieldsValidationException {
		try {
			person.updatePerson(handle, attributes);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public Properties getPhoneTypes() {
		try {
			return directory.getPhoneTypes();
		} catch (RemoteException e) {
			throw new EJBException(e);
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
			object = context.lookup(JNDINames.QUERY_BEAN);
			QueryHome queryHome = (QueryHome) PortableRemoteObject.narrow(object, QueryHome.class);
			query = queryHome.create();
			object = context.lookup(JNDINames.PERSON_BEAN);
			PersonHome personHome = (PersonHome)  PortableRemoteObject.narrow(object, PersonHome.class);
			person = personHome.create();
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
