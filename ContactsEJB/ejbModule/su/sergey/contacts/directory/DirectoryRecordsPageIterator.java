package su.sergey.contacts.directory;

import java.rmi.RemoteException;

import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.pageiterator.PageIteratorBase;

/**
 * DirectoryRecordsPageIterator
 *
 * @author Сергей Богданов
 */
public interface DirectoryRecordsPageIterator extends PageIteratorBase {

    DirectoryRecord[] next() throws RemoteException;
    
    DirectoryRecord[] current() throws RemoteException;
    
    DirectoryRecord[] prev() throws RemoteException;
    
    DirectoryRecord[] goTo(int page) throws RemoteException;
    
}
