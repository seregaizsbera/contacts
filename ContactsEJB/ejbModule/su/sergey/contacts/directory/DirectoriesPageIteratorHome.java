package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * DirectoriesPageIteratorHome
 * @author 
 * @date 14.08.2002
 * @time 17:27:08
 */

public interface DirectoriesPageIteratorHome extends EJBHome {
    DirectoriesPageIterator create(int pageSize) throws RemoteException, CreateException;
}
