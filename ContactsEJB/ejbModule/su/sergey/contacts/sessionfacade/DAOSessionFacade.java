package su.sergey.contacts.sessionfacade;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;

import javax.ejb.EJBObject;
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
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.report.ReportException;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

/**
 * Remote interface for Enterprise Bean: DAOSessionFacade
 */
public interface DAOSessionFacade extends EJBObject {
	DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle) throws RemoteException, ContactsException;

	void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle, DirectoryMetadata directoryMetadata) throws RemoteException, ContactsException;

	DirectoryRecord findDirectoryRecord(DirectoryRecordHandle handle) throws RemoteException, ContactsException;

	void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) throws RemoteException, ContactsException;

	void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws RemoteException, ContactsException;

	void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) throws RemoteException, ContactsException;

	QueryResult performQuery(String sql) throws RemoteException;

	String[] getLastQueries() throws RemoteException;

	Person2 findPerson(PersonHandle handle) throws RemoteException;

	PersonHandle createPerson(PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;

	void updatePerson(PersonHandle handle, PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;

	void removePerson(PersonHandle handle) throws RemoteException;

	Phone2[] getPersonPhones(PersonHandle handle) throws RemoteException;
	
	PhoneHandle addPersonPhone(PersonHandle personHandle, PhoneAttributes phoneHandle) throws RemoteException;
	
	void setBasicPersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) throws RemoteException;
	
	void removePersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) throws RemoteException;
	
	void updatePhone(PhoneHandle handleHandle, PhoneAttributes phoneAttributes) throws RemoteException;
	
	Email2[] getPersonEmails(PersonHandle handle) throws RemoteException;
	
	EmailHandle addPersonEmail(PersonHandle personHandle, EmailAttributes emailHandle) throws RemoteException;
	
	void setBasicPersonEmail(PersonHandle personHandle, EmailHandle emailHandle) throws RemoteException;
	
	void removePersonEmail(PersonHandle personHandle, EmailHandle emailHandle) throws RemoteException;
	
	void updateEmail(EmailHandle handleHandle, EmailAttributes emailAttributes) throws RemoteException;

	Serializable getSystemPropertyValue(String name) throws RemoteException, PropertyNotFoundException;
	
    void setSystemPropertyValue(String name, Serializable value) throws RemoteException, PropertyNotFoundException;
    
    void setSystemPropertyValue(String name, String value) throws RemoteException, InvalidPropertyValueException, PropertyNotFoundException;
	
	Supply2 findSupply(SupplyHandle handle) throws RemoteException;
	
	SupplyHandle createSupply(SupplyAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void removeSupply(SupplyHandle handle) throws RemoteException;
	
	Phone2[] getSupplyPhones(SupplyHandle handle) throws RemoteException;
	
	PhoneHandle addSupplyPhone(SupplyHandle supplyHandle, PhoneAttributes phone) throws RemoteException;
	
	void removeSupplyPhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle) throws RemoteException;
	
	Email2[] getSupplyEmails(SupplyHandle handle) throws RemoteException;
	
	EmailHandle addSupplyEmail(SupplyHandle supplyHandle, EmailAttributes email) throws RemoteException;
	
	void removeSupplyEmail(SupplyHandle supplyHandle, EmailHandle emailHandle) throws RemoteException;
	
	File buildPersonReport(PersonSearchParameters searchParameters, String description) throws RemoteException, ReportException;
	
	File buildSupplyReport(SupplySearchParameters searchParameters, String description) throws RemoteException, ReportException;

	CallExpenseData findCallExpense(CallExpenseHandle handle) throws RemoteException;
	
	CallExpenseHandle createCallExpense(CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException, RemoteException;

	void updateCallExpense(CallExpenseHandle handle, CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException, RemoteException;
	
	void removeCallExpense(CallExpenseHandle handle) throws RemoteException;
}
