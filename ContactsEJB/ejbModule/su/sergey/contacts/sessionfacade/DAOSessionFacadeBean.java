package su.sergey.contacts.sessionfacade;

import java.io.Serializable;
import java.rmi.RemoteException;

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
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.email.Email;
import su.sergey.contacts.email.EmailHome;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.ExceptionUtil;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.inquiry.Inquiry;
import su.sergey.contacts.inquiry.InquiryHome;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.person.Person;
import su.sergey.contacts.person.PersonHome;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.phone.Phone;
import su.sergey.contacts.phone.PhoneHome;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.Property;
import su.sergey.contacts.properties.PropertyHome;
import su.sergey.contacts.properties.PropertyNotFoundException;
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
	private Inquiry inquiry;
	private Phone phone;
	private Email email;
	private Property property;
	
	public Email2[] getPersonEmails(PersonHandle handle) {
		try {
			return person.getPersonEmails(handle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public EmailHandle addPersonEmail(PersonHandle personHandle, EmailAttributes emailHandle) {
		try {
			return person.addEmail(personHandle, emailHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void setBasicPersonEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		try {
			person.setBasicEmail(personHandle, emailHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removePersonEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		try {
			person.removeEmail(personHandle, emailHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void updateEmail(EmailHandle handle, EmailAttributes emailAttributes) {
		try {
			email.updateEmail(handle, emailAttributes);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public Phone2[] getPersonPhones(PersonHandle handle) {
		try {
			return person.getPersonPhones(handle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public PhoneHandle addPersonPhone(PersonHandle personHandle, PhoneAttributes phoneHandle) {
		try {
			return person.addPhone(personHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void setBasicPersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		try {
			person.setBasicPhone(personHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removePersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		try {
			person.removePhone(personHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void updatePhone(PhoneHandle handle, PhoneAttributes phoneAttributes) {
		try {
			phone.updatePhone(handle, phoneAttributes);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
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
	
	public void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			directory.removeDirectoryRecord(directoryRecordHandle);
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
	
	public Person2 findPerson(PersonHandle handle) {
		try {
			return person.findPerson(handle, true);
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
	
	public InquiryObject[] inquireTableAsNames(String tableName) {
		try {
			return inquiry.inquireTableAsNames(tableName);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removePerson(PersonHandle handle) {
		try {
			person.removePerson(handle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public Serializable getSystemPropertyValue(String name) throws PropertyNotFoundException {
		try {
			Serializable result = property.getValue(name);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		} catch (PropertyNotFoundException e) {
			mySessionCtx.setRollbackOnly();
			throw e;
		}
	}
	
    public void setSystemPropertyValue(String name, Serializable value) throws PropertyNotFoundException {
		try {
			property.setValue(name, value);
		} catch (RemoteException e) {
			throw new EJBException(e);
		} catch (ContactsException e) {
			mySessionCtx.setRollbackOnly();
		}
    }
    
    public void setSystemPropertyValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException {
		try {
			property.setValue(name, value);
		} catch (RemoteException e) {
			throw new EJBException(e);
		} catch (ContactsException e) {
			mySessionCtx.setRollbackOnly();
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
			object = context.lookup(JNDINames.INQUIRY_BEAN);
			InquiryHome inquiryHome = (InquiryHome) PortableRemoteObject.narrow(object, InquiryHome.class);
			inquiry = inquiryHome.create();
			object = context.lookup(JNDINames.PHONE_BEAN);
			PhoneHome phoneHome = (PhoneHome) PortableRemoteObject.narrow(object, PhoneHome.class);
			phone = phoneHome.create();
			object = context.lookup(JNDINames.EMAIL_BEAN);
			EmailHome emailHome = (EmailHome) PortableRemoteObject.narrow(object, EmailHome.class);
			email = emailHome.create();
			object = context.lookup(JNDINames.PROPERTY_BEAN);
			PropertyHome propertyHome = (PropertyHome) PortableRemoteObject.narrow(object, PropertyHome.class);
			property = propertyHome.create();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new CreateException(e.detail.getMessage());
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
