package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectorySearchParameters;

/**
 * DirectoriesPageIteratorHome
 * @author 
 * @date 14.08.2002
 * @time 17:27:08
 */

public interface DirectoriesPageIteratorHome extends EJBHome {
    DirectoriesPageIterator create(DirectorySearchParameters searchParameters, int pageSize) throws RemoteException, CreateException;
}
