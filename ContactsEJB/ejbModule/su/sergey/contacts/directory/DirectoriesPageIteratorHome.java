package su.sergey.contacts.directory;

import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;
import java.io.Serializable;

/**
 * DirectoriesPageIteratorHome
 * @author 
 * @date 14.08.2002
 * @time 17:27:08
 */

public interface DirectoriesPageIteratorHome extends EJBHome {
    DirectoriesPageIterator create(int pageSize) throws RemoteException, CreateException;
}
