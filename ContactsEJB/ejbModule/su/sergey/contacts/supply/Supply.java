package su.sergey.contacts.supply;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

/**
 * Remote interface for Enterprise Bean: Supply
 */
public interface Supply extends EJBObject {
	
	Supply2 findSupply(SupplyHandle handle, boolean fullData) throws RemoteException;
	
	SupplyHandle createSupply(SupplyAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws RemoteException, MultipleFieldsValidationException;
	
	void removeSupply(SupplyHandle handle) throws RemoteException;
	
	Phone2[] getSupplyPhones(SupplyHandle handle) throws RemoteException;
	
	PhoneHandle addPhone(SupplyHandle supplyHandle, PhoneAttributes phone) throws RemoteException;
	
	void removePhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle) throws RemoteException;
	
	Email2[] getSupplyEmails(SupplyHandle handle) throws RemoteException;
	
	EmailHandle addEmail(SupplyHandle supplyHandle, EmailAttributes email) throws RemoteException;
	
	void removeEmail(SupplyHandle supplyHandle, EmailHandle emailHandle) throws RemoteException;
}
