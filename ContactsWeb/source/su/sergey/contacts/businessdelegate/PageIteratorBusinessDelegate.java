package su.sergey.contacts.businessdelegate;

import su.sergey.contacts.exceptions.ContactsException;

/**
 * Общий интерфейс для всех page iterators,
 * позволяет взять у любого итератора общие параметры
 */
public interface PageIteratorBusinessDelegate {

    boolean hasNext() throws ContactsException;

    boolean hasPrev() throws ContactsException;

    int getCurrentPozition() throws ContactsException;

    int getCurrentPage() throws ContactsException;

    int getPageSize() throws ContactsException;

    int getNumberOfPages() throws ContactsException;
}
