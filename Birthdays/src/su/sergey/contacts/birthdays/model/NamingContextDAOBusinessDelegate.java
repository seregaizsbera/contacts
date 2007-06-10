package su.sergey.contacts.birthdays.model;

import java.io.*;
import java.rmi.*;

import javax.ejb.*;
import javax.rmi.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import su.sergey.contacts.call.valueobjects.*;
import su.sergey.contacts.directory.valueobjects.*;
import su.sergey.contacts.directory.valueobjects.handles.*;
import su.sergey.contacts.dto.*;
import su.sergey.contacts.email.valueobjects.*;
import su.sergey.contacts.exceptions.*;
import su.sergey.contacts.person.searchparameters.*;
import su.sergey.contacts.person.valueobjects.*;
import su.sergey.contacts.phone.valueobjects.*;
import su.sergey.contacts.properties.*;
import su.sergey.contacts.query.valueobjects.*;
import su.sergey.contacts.report.*;
import su.sergey.contacts.sessionfacade.*;
import su.sergey.contacts.sessionfacade.businessdelegate.*;
import su.sergey.contacts.supply.searchparameters.*;
import su.sergey.contacts.supply.valueobjects.*;

public class NamingContextDAOBusinessDelegate implements DAOBusinessDelegate {
    private DAOSessionFacade facade;
    
    public NamingContextDAOBusinessDelegate(NamingContext context, String jndiName) {
        try {
	    org.omg.CORBA.Object object = context.resolve(NamingUtil.toCorbaName(jndiName));
	    DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
	    facade = home.create();
	} catch (InvalidName e) {
	    throw new RuntimeDelegateException(e);
	} catch (CannotProceed e) {
	    throw new RuntimeDelegateException(e);
	} catch (NotFound e) {
	    throw new RuntimeDelegateException(e);
	} catch (CreateException e) {
	    throw new RuntimeDelegateException(e);
	} catch (RemoteException e) {
	    throw new RuntimeDelegateException(e);
	}
    }    

    public Object getSystemPropertyValue(String name) throws PropertyNotFoundException {
        try {
	    return facade.getSystemPropertyValue(name);
	} catch (RemoteException e) {
	    throw new RuntimeDelegateException(e);
	}
    }

    public void setSystemPropertyValue(String name, Object value) throws PropertyNotFoundException {
        try {
	    facade.setSystemPropertyValue(name, (Serializable) value);
	} catch (RemoteException e) {
	    throw new RuntimeDelegateException(e);
	}
    }
    
    public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle) throws ContactsException {
        throw new UnsupportedOperationException();
    }

    public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
        throw new UnsupportedOperationException();
    }

    public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) throws ContactsException {
        throw new UnsupportedOperationException();
    }

    public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) throws ContactsException {
        throw new UnsupportedOperationException();
    }

    public void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException {
        throw new UnsupportedOperationException();
    }
		    
    public QueryResult performQuery(String sql) {
        throw new UnsupportedOperationException();
    }
    
    public String[] getLastQueries() {
        throw new UnsupportedOperationException();
    }
    
    public PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException {
        throw new UnsupportedOperationException();
    }
    
    public void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException {
        throw new UnsupportedOperationException();
    }
    
    public Person2 findPerson(PersonHandle handle) {
        throw new UnsupportedOperationException();
    }
    
    public void removePerson(PersonHandle handle) {
        throw new UnsupportedOperationException();
    }
    
    public Phone2[] getPersonPhones(PersonHandle handle) {
        throw new UnsupportedOperationException();
    }
   	
    public PhoneHandle addPersonPhone(PersonHandle handle, PhoneAttributes phone) {
        throw new UnsupportedOperationException();
    }

    public void updatePhone(PhoneHandle handle, PhoneAttributes phone) {
        throw new UnsupportedOperationException();
    }

    public void removePersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
        throw new UnsupportedOperationException();
    }

    public void setBasicPersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
        throw new UnsupportedOperationException();
    }

    public Email2[] getPersonEmails(PersonHandle handle) {
        throw new UnsupportedOperationException();
    }

    public EmailHandle addPersonEmail(PersonHandle handle, EmailAttributes email) {
        throw new UnsupportedOperationException();
    }

    public void updateEmail(EmailHandle handle, EmailAttributes email) {
        throw new UnsupportedOperationException();
    }

    public void removePersonEmail(PersonHandle personHandle, EmailHandle emailHandle) {
        throw new UnsupportedOperationException();
    }

    public void setBasicPersonEmail(PersonHandle personHandle, EmailHandle emailHandle) {
        throw new UnsupportedOperationException();
    }

    public void setSystemPropertyValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException {
        throw new UnsupportedOperationException();
    }

    public Supply2 findSupply(SupplyHandle handle) {
        throw new UnsupportedOperationException();
    }

    public SupplyHandle createSupply(SupplyAttributes attributes) throws MultipleFieldsValidationException {
        throw new UnsupportedOperationException();
    }

    public void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws MultipleFieldsValidationException {
        throw new UnsupportedOperationException();
    }

    public void removeSupply(SupplyHandle handle) {
        throw new UnsupportedOperationException();
    }

    public Phone2[] getSupplyPhones(SupplyHandle handle) {
        throw new UnsupportedOperationException();
    }

    public PhoneHandle addSupplyPhone(SupplyHandle supplyHandle, PhoneAttributes phone) {
        throw new UnsupportedOperationException();
    }

    public void removeSupplyPhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle) {
        throw new UnsupportedOperationException();
    }

    public Email2[] getSupplyEmails(SupplyHandle handle) {
        throw new UnsupportedOperationException();
    }

    public EmailHandle addSupplyEmail(SupplyHandle supplyHandle, EmailAttributes email) {
        throw new UnsupportedOperationException();
    }

    public void removeSupplyEmail(SupplyHandle supplyHandle, EmailHandle emailHandle) {
        throw new UnsupportedOperationException();
    }

    public File buildPersonReport(PersonSearchParameters searchParameters, String description) throws ReportException {
        throw new UnsupportedOperationException();
    }

    public File buildSupplyReport(SupplySearchParameters searchParameters, String description) throws ReportException {
        throw new UnsupportedOperationException();
    }

    public CallExpenseAttributes findCallExpense(CallExpenseHandle handle) {
        throw new UnsupportedOperationException();
    }

    public CallExpenseHandle createCallExpense(CallExpenseAttributes callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
        throw new UnsupportedOperationException();
    }

    public void updateCallExpense(CallExpenseHandle handle, CallExpenseAttributes callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
        throw new UnsupportedOperationException();
    }

    public void removeCallExpense(CallExpenseHandle handle) {
        throw new UnsupportedOperationException();
    }
}
