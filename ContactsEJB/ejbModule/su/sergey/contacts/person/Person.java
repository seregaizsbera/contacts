package su.sergey.contacts.person;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

/**
 * Remote interface for Enterprise Bean: Person
 */
public interface Person extends EJBObject {
	
	Person2 findPerson(PersonHandle handle) throws RemoteException;
	
	PersonHandle createPerson(PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void updatePerson(PersonHandle handle, PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void removePerson(PersonHandle handle) throws RemoteException;
	
	Phone2[] getPersonPhones(PersonHandle handle) throws RemoteException;
	
	PhoneHandle addPhone(PersonHandle personHandle, PhoneAttributes phone) throws RemoteException;
	
	void setBasicPhone(PersonHandle personHandle, PhoneHandle phoneHandle) throws RemoteException;
	
	void removePhone(PersonHandle personHandle, PhoneHandle phoneHandle) throws RemoteException;
}
