package su.sergey.contacts.directory.businessdelegate;

import su.sergey.contacts.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;

public interface DirectoriesPageIteratorBusinessDelegate extends PageIteratorBusinessDelegate {

    DirectoryMetadata[] next() throws ContactsException;

    DirectoryMetadata[] current() throws ContactsException;

    DirectoryMetadata[] prev() throws ContactsException;

    DirectoryMetadata[] goToPage(int number) throws ContactsException;
    
    void freeResources();
}
