package su.sergey.contacts.call;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.call.valueobjects.CallExpense2;
import su.sergey.contacts.pageiterator.PageIteratorBase;

/**
 * Remote interface for Enterprise Bean: CallExpensePageIterator
 */
public interface CallExpensePageIterator extends PageIteratorBase, EJBObject {

    CallExpense2[] prev() throws RemoteException;
	
    CallExpense2[] current() throws RemoteException;
	
    CallExpense2[] next() throws RemoteException;
	
    CallExpense2[] goTo(int page) throws RemoteException;

}
