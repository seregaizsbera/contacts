package su.sergey.contacts.businessdelegate;

import su.sergey.contacts.exceptions.ContactsException;

/**
 * ����� ��������� ��� ���� page iterators,
 * ��������� ����� � ������ ��������� ����� ���������
 */
public interface PageIteratorBusinessDelegate {

    boolean hasNext() throws ContactsException;

    boolean hasPrev() throws ContactsException;

    int getCurrentPozition() throws ContactsException;

    int getCurrentPage() throws ContactsException;

    int getPageSize() throws ContactsException;

    int getNumberOfPages() throws ContactsException;
}
