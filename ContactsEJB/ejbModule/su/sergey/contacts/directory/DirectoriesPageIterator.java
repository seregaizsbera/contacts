package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.pageiterator.PageIteratorBase;
import su.sergey.contacts.util.dao.DAOException;

/**
 * DirectoryRecordsPageIterator
 * 
 * @author ElenaKh
 */
public interface DirectoriesPageIterator extends PageIteratorBase {
    DirectoryMetadata[] next() throws DAOException, RemoteException;
    DirectoryMetadata[] current() throws DAOException, RemoteException;
    DirectoryMetadata[] prev() throws DAOException, RemoteException;
    DirectoryMetadata[] goTo(int page) throws DAOException, RemoteException;
}
