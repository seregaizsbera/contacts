package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import su.sergey.contacts.pageiterator.PageIteratorBase;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.valueobjects.DirectoryRecord;

/**
 * DirectoryRecordsPageIterator
 *
 * @author Сергей Богданов
 */
public interface DirectoryRecordsPageIterator extends PageIteratorBase {
    DirectoryRecord[] next() throws DAOException, RemoteException;
    DirectoryRecord[] current() throws DAOException, RemoteException;
    DirectoryRecord[] prev() throws DAOException, RemoteException;
    DirectoryRecord[] goTo(int page) throws DAOException, RemoteException;
}
