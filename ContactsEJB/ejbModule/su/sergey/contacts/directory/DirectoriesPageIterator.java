package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.pageiterator.PageIteratorBase;

/**
 * DirectoryRecordsPageIterator
 */
public interface DirectoriesPageIterator extends PageIteratorBase {

    DirectoryMetadata[] next() throws RemoteException;
    
    DirectoryMetadata[] current() throws RemoteException;
    
    DirectoryMetadata[] prev() throws RemoteException;
    
    DirectoryMetadata[] goTo(int page) throws RemoteException;
    
}
