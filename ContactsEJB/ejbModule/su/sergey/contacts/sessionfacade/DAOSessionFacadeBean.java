package su.sergey.contacts.sessionfacade;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.security.Principal;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.call.Call;
import su.sergey.contacts.call.CallHome;
import su.sergey.contacts.directory.Directory;
import su.sergey.contacts.directory.DirectoryHome;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.Email;
import su.sergey.contacts.email.EmailHome;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.DuplicateInstanceException;
import su.sergey.contacts.exceptions.ExceptionUtil;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.inquiry.Inquiry;
import su.sergey.contacts.inquiry.InquiryHome;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.person.Person;
import su.sergey.contacts.person.PersonHome;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.phone.Phone;
import su.sergey.contacts.phone.PhoneHome;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.Property;
import su.sergey.contacts.properties.PropertyHome;
import su.sergey.contacts.properties.PropertyNames;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.query.Query;
import su.sergey.contacts.query.QueryHome;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.report.Report;
import su.sergey.contacts.report.ReportException;
import su.sergey.contacts.report.ReportHome;
import su.sergey.contacts.supply.Supply;
import su.sergey.contacts.supply.SupplyHome;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.valueobjects.Reports;

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
	private Report report;
	private Supply supply;
	private Call call;
	
	private String getUserName() {
		Principal user = mySessionCtx.getCallerPrincipal();
		String result = user.getName();
		return result;
	}
	
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
			return query.performQuery(sql, getUserName());
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public String[] getLastQueries() {
		try {
			Integer maxNumberOfQueriesValue = (Integer) property.getValue(PropertyNames.QUERY_HISTORY_SIZE);
			int maxNumberOfQueries = maxNumberOfQueriesValue != null ? maxNumberOfQueriesValue.intValue() : 15;
			return query.getLastQueries(maxNumberOfQueries, getUserName());
		} catch (PropertyNotFoundException e) {
		    throw new EJBException(e);
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
	
	public InquiryObject[] inquireTableAsIds(String tableName) {
		try {
			return inquiry.inquireTableAsIds(tableName);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public HashMap inquireTableAsHash(String tableName) {
		try {
			return inquiry.inquireTableAsHash(tableName);
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
    
	public Supply2 findSupply(SupplyHandle handle) {
		try {
			Supply2 result = supply.findSupply(handle, true);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public SupplyHandle createSupply(SupplyAttributes attributes) throws MultipleFieldsValidationException {
		try {
			SupplyHandle result = supply.createSupply(attributes);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws MultipleFieldsValidationException {
		try {
			supply.updateSupply(handle, attributes);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removeSupply(SupplyHandle handle) {
		try {
			supply.removeSupply(handle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public PhoneHandle addSupplyPhone(SupplyHandle supplyHandle, PhoneAttributes phone) {
		try {
			PhoneHandle result = supply.addPhone(supplyHandle, phone);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removeSupplyPhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle) {
		try {
			supply.removePhone(supplyHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public Phone2[] getSupplyPhones(SupplyHandle handle) {
		try {
			Phone2 result[] = supply.getSupplyPhones(handle);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public EmailHandle addSupplyEmail(SupplyHandle supplyHandle, EmailAttributes email) {
		try {
			EmailHandle result = supply.addEmail(supplyHandle, email);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removeSupplyEmail(SupplyHandle supplyHandle, EmailHandle emailHandle) {
		try {
			supply.removeEmail(supplyHandle, emailHandle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public Email2[] getSupplyEmails(SupplyHandle handle) {
		try {
			Email2 result[] = supply.getSupplyEmails(handle);
			return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public File buildPersonReport(PersonSearchParameters searchParameters, String description) throws ReportException {
		try {
    		File result = report.buildReport(Reports.PERSONS, description, searchParameters);
    		return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public File buildSupplyReport(SupplySearchParameters searchParameters, String description) throws ReportException {
		try {
    		File result = report.buildReport(Reports.SUPPLIES, description, searchParameters);
    		return result;
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public CallExpenseData findCallExpense(CallExpenseHandle handle) {
		try {
    		return call.findCallExpense(handle);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public CallExpenseHandle createCallExpense(CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
		try {
    		return call.createCallExpense(callExpense);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}

	public void updateCallExpense(CallExpenseHandle handle, CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
		try {
    		call.updateCallExpense(handle, callExpense);
		} catch (RemoteException e) {
			throw new EJBException(e);
		}
	}
	
	public void removeCallExpense(CallExpenseHandle handle) {
		try {
    		call.removeCallExpense(handle);
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
	public void ejbActivate() {
	}
	
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
			object = context.lookup(JNDINames.SUPPLY_BEAN);
			SupplyHome supplyHome = (SupplyHome) PortableRemoteObject.narrow(object, SupplyHome.class);
			supply = supplyHome.create();
			object = context.lookup(JNDINames.REPORT_BEAN);
			ReportHome reportHome = (ReportHome) PortableRemoteObject.narrow(object, ReportHome.class);
			report = reportHome.create();
			object = context.lookup(JNDINames.CALL_BEAN);
			CallHome callHome = (CallHome) PortableRemoteObject.narrow(object, CallHome.class);
			call = callHome.create();
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
