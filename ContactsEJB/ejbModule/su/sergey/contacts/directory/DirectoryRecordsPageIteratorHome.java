package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;

/**
 * DirectoryRecordsPageIteratorHome
 *
 * @author Сергей Богданов
 */
public interface DirectoryRecordsPageIteratorHome extends EJBHome {
    DirectoryRecordsPageIterator create(DirectoryRecordSearchParameters searchParameters, int pageSize) throws RemoteException, CreateException;
}
