package su.sergey.contacts.pageiterator;

import java.rmi.RemoteException;

import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.valueobjects.DirectoryRecord;

/**
 * DirectoryRecordsPageIterator
 * @author 
 * @date 14.08.2002
 * @time 17:21:19
 */

public interface DirectoryRecordsPageIterator extends PageIteratorBase {
    DirectoryRecord[] next() throws DAOException, RemoteException;
    DirectoryRecord[] current() throws DAOException, RemoteException;
    DirectoryRecord[] prev() throws DAOException, RemoteException;
    DirectoryRecord[] goTo(int page) throws DAOException, RemoteException;
}
