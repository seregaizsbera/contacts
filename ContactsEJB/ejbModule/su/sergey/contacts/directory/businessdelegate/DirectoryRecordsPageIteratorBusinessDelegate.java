package su.sergey.contacts.directory.businessdelegate;

import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.pageiterator.businessdelegate.PageIteratorBusinessDelegate;

/**
 * Интерфейс для управления показом списка записей справочника
 */
public interface DirectoryRecordsPageIteratorBusinessDelegate extends PageIteratorBusinessDelegate {

    DirectoryRecord[] next() throws ContactsException;

    DirectoryRecord[] current() throws ContactsException;

    DirectoryRecord[] prev() throws ContactsException;

    DirectoryRecord[] goToPage(int number) throws ContactsException;
}
