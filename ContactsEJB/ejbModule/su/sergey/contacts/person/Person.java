package su.sergey.contacts.person;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;

/**
 * Remote interface for Enterprise Bean: Person
 */
public interface Person extends EJBObject {
	
	Person2 findPerson(PersonHandle handle) throws RemoteException;
	
	PersonHandle createPerson(PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void updatePerson(PersonHandle handle, PersonAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
}
