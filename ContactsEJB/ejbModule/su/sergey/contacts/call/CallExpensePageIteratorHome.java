package su.sergey.contacts.call;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Home interface for Enterprise Bean: CallExpensePageIterator
 */
public interface CallExpensePageIteratorHome extends EJBHome {
    /**
     * Creates a default instance of Session Bean: CallExpensePageIterator
     */
    public CallExpensePageIterator create() throws CreateException, RemoteException;
}
