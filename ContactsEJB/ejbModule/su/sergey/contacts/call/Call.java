package su.sergey.contacts.call;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.call.valueobjects.CallExpenseAttributes;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.DuplicateInstanceException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;

/**
 * Remote interface for Enterprise Bean: Call
 */
public interface Call extends EJBObject {
	CallExpenseAttributes findCallExpense(CallExpenseHandle handle) throws RemoteException;
	
	CallExpenseHandle createCallExpense(CallExpenseAttributes callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException, RemoteException;

	void updateCallExpense(CallExpenseHandle handle, CallExpenseAttributes callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException, RemoteException;
	
	void removeCallExpense(CallExpenseHandle handle) throws RemoteException;
}
