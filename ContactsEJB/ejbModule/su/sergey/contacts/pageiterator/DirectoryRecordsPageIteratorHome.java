package su.sergey.contacts.pageiterator;

import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;
import java.io.Serializable;

/**
 * DirectoryRecordsPageIteratorHome
 * @author 
 * @date 14.08.2002
 * @time 17:27:08
 */

public interface DirectoryRecordsPageIteratorHome extends EJBHome {
    DirectoryRecordsPageIterator create(DirectoryRecordSearchParameters searchParameters, int pageSize) throws RemoteException, CreateException;
}
