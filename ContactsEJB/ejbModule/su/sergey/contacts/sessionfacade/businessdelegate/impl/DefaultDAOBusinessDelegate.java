package su.sergey.contacts.sessionfacade.businessdelegate.impl;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
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
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.DuplicateInstanceException;
import su.sergey.contacts.exceptions.ExceptionUtil;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.report.ReportException;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;


public class DefaultDAOBusinessDelegate implements DAOBusinessDelegate {
	private final DAOSessionFacade facade;
	
    public DefaultDAOBusinessDelegate(String facadeName) {
    	try {
	    	Context context = new InitialContext();
	    	Object object = context.lookup(facadeName);
	    	DAOSessionFacadeHome facadeHomeObject = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
	    	facade = facadeHomeObject.create();
    	} catch (NamingException e) {
    		throw new RuntimeDelegateException(e);
    	} catch (CreateException e) {
    		throw new RuntimeDelegateException(e);
    	} catch (RemoteException e) {
    		throw new RuntimeDelegateException(e);
    	}
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
	    	throw new ContactsException(e);
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
	    	throw new ContactsException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#findDirectoryRecord(DirectoryRecordHandle)
	 */
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			return facade.findDirectoryRecord(directoryRecordHandle);
		} catch (RemoteException e) {
	    	throw new ContactsException(e);
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
	    	throw new ContactsException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removeDirectoryRecord(DirectoryRecordHandle)
	 */
	public void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
		try {
			facade.removeDirectoryRecord(directoryRecordHandle);
		} catch (RemoteException e) {
	    	throw new ContactsException(e);
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
	    	throw new ContactsException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#createPerson(PersonAttributes)
	 */
	public PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException {
		try {
			return facade.createPerson(person);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#updatePerson(PersonHandle, PersonAttributes)
	 */
	public void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException {
		try {
			facade.updatePerson(handle, person);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
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
			return facade.getLastQueries();
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#inquireTable(String)
	 */
	public InquiryObject[] inquireTableAsNames(String tableName) {
		try {
			return facade.inquireTableAsNames(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	/**
	 * @see DAOBusinessDelegate#removePerson(PersonHandle)
	 */
	public void removePerson(PersonHandle handle) {
		try {
			facade.removePerson(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#getPersonPhones(PersonHandle)
	 */
	public Phone2[] getPersonPhones(PersonHandle handle) {
		try {
			return facade.getPersonPhones(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#addPersonPhone(PersonHandle, PhoneAttributes)
	 */
	public PhoneHandle addPersonPhone(PersonHandle handle, PhoneAttributes phone) {
		try {
			return facade.addPersonPhone(handle, phone);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#updatePhone(PhoneHandle, PhoneAttributes)
	 */
	public void updatePhone(PhoneHandle handle, PhoneAttributes phone) {
		try {
			facade.updatePhone(handle, phone);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removePersonPhone(PersonHandle, PhoneHandle)
	 */
	public void removePersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		try {
			facade.removePersonPhone(personHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#setBasicPhone(PersonHandle, PhoneHandle)
	 */
	public void setBasicPersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		try {
			facade.setBasicPersonPhone(personHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#getPersonEmails(PersonHandle)
	 */
	public Email2[] getPersonEmails(PersonHandle handle) {
		try {
			return facade.getPersonEmails(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#addPersonEmail(PersonHandle, EmailAttributes)
	 */
	public EmailHandle addPersonEmail(PersonHandle handle, EmailAttributes email) {
		try {
			return facade.addPersonEmail(handle, email);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#updateEmail(EmailHandle, EmailAttributes)
	 */
	public void updateEmail(EmailHandle handle, EmailAttributes email) {
		try {
			facade.updateEmail(handle, email);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removePersonEmail(PersonHandle, EmailHandle)
	 */
	public void removePersonEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		try {
			facade.removePersonEmail(personHandle, emailHandle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#setBasicEmail(PersonHandle, EmailHandle)
	 */
	public void setBasicPersonEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		try {
			facade.setBasicPersonEmail(personHandle, emailHandle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	/**
	 * @see DAOBusinessDelegate#getSystemPropertyValue(String)
	 */
	public Object getSystemPropertyValue(String name) throws PropertyNotFoundException {
		try {
			return facade.getSystemPropertyValue(name);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#setSystemPropertyValue(String, Object)
	 */
	public void setSystemPropertyValue(String name, Object value) throws PropertyNotFoundException {
		try {
			facade.setSystemPropertyValue(name, (Serializable) value);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#setSystemPropertyValue(String, String)
	 */
	public void setSystemPropertyValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException {
		try {
			facade.setSystemPropertyValue(name, value);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#findSupply(SupplyHandle)
	 */
	public Supply2 findSupply(SupplyHandle handle) {
		try {
			return facade.findSupply(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#createSupply(SupplyAttributes)
	 */
	public SupplyHandle createSupply(SupplyAttributes attributes) throws MultipleFieldsValidationException {
		try {
			return facade.createSupply(attributes);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#updateSupply(SupplyHandle, SupplyAttributes)
	 */
	public void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws MultipleFieldsValidationException {
		try {
			facade.updateSupply(handle, attributes);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removeSupply(SupplyHandle)
	 */
	public void removeSupply(SupplyHandle handle) {
		try {
			facade.removeSupply(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#getSupplyPhones(SupplyHandle)
	 */
	public Phone2[] getSupplyPhones(SupplyHandle handle) {
		try {
			return facade.getSupplyPhones(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#addSupplyPhone(SupplyHandle, PhoneAttributes)
	 */
	public PhoneHandle addSupplyPhone(SupplyHandle supplyHandle, PhoneAttributes phone) {
		try {
			return facade.addSupplyPhone(supplyHandle, phone);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removeSupplyPhone(SupplyHandle, PhoneHandle)
	 */
	public void removeSupplyPhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle) {
	    try {
			facade.removeSupplyPhone(supplyHandle, phoneHandle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#getSupplyEmails(SupplyHandle)
	 */
	public Email2[] getSupplyEmails(SupplyHandle handle) {
		try {
			return facade.getSupplyEmails(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#addSupplyEmail(SupplyHandle, EmailAttributes)
	 */
	public EmailHandle addSupplyEmail(SupplyHandle supplyHandle, EmailAttributes email) {
		try {
			return facade.addSupplyEmail(supplyHandle, email);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removeSupplyEmail(SupplyHandle, EmailHandle)
	 */
	public void removeSupplyEmail(SupplyHandle supplyHandle, EmailHandle emailHandle) {
		try {
			facade.removeSupplyEmail(supplyHandle, emailHandle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#buildPersonReport(PersonSearchParameters, String, String)
	 */
	public File buildPersonReport(PersonSearchParameters searchParameters, String description) throws ReportException {
		try {
			return facade.buildPersonReport(searchParameters, description);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#buildSupplyReport(SupplySearchParameters, String, String)
	 */
	public File buildSupplyReport(SupplySearchParameters searchParameters, String description) throws ReportException {
		try {
			return facade.buildSupplyReport(searchParameters, description);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	/**
	 * @see DAOBusinessDelegate#inquireTableAsIds(String)
	 */
	public InquiryObject[] inquireTableAsIds(String tableName) {
		try {
			return facade.inquireTableAsIds(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#inquireTableAsHash(String)
	 */
	public Map inquireTableAsHash(String tableName) {
		try {
			return facade.inquireTableAsHash(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see DAOBusinessDelegate#findCallExpense(CallExpenseHandle)
	 */
	public CallExpenseData findCallExpense(CallExpenseHandle handle) {
		try {
			return facade.findCallExpense(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#createCallExpense(CallExpenseData)
	 */
	public CallExpenseHandle createCallExpense(CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
		try {
			return facade.createCallExpense(callExpense);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#updateCallExpense(CallExpenseHandle, CallExpenseData)
	 */
	public void updateCallExpense(CallExpenseHandle handle, CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
		try {
			facade.updateCallExpense(handle, callExpense);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see DAOBusinessDelegate#removeCallExpense(CallExpenseHandle)
	 */
	public void removeCallExpense(CallExpenseHandle handle) {
		try {
			facade.removeCallExpense(handle);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
}
