package su.sergey.contacts.businessdelegate;

import java.util.Collection;

import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.InvalidValueException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

public interface DAOBusinessDelegate {

    /**
     * ���������� ���������� ������� (DirectoryMetadata).
     *
     * @param directoryMetadataHandle ���������, ���������� ��� �������
     * @return DirectoryMetadata ���������� �������
     */
    DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle)
		    throws ContactsException;

    /**
     * ��������� ���������� �������
     *
     * @param directoryMetadataHandle ���������� �������
     * @param directoryMetadata ���������� �������
     */
    void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle, DirectoryMetadata directoryMetadata)
		    throws ContactsException;

    /**
     * ���������� ������ ������� �������
     *
     * @param searchParameters ��������� ������
     * @param start ��������� ������
     * @param length ����� �������
     * @return Collection ������ �������
     */
    Collection findDirectoryRecords(
        DirectoryRecordSearchParameters searchParameters,
        int start,
        int length);

    /**
     * ���������� ������ �������
     *
     * @param directoryRecordHandle ��� ������� � �������� primary key ������
     * @return DirectoryRecord ������ �������
     */
    DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException;

    /**
     * ��������� ������ �������
     *
     * @param directoryRecordHandle ���������,
     *                              ���������� ��� ������� � ������������ ������
     * @param directoryRecord ������ �������
     */
    void updateDirectoryRecord(
        DirectoryRecordHandle directoryRecordHandle,
        DirectoryRecord directoryRecord) throws ContactsException;

    /**
     * ��������� ������ � �������
     *
     * @param directoryMetadataHandle ���������, ���������� ��� �������
     * @param directoryRecord ������ �������
     */
    void addDirectoryRecord(
        DirectoryMetadataHandle directoryMetadataHandle,
        DirectoryRecord directoryRecord) throws ContactsException;

    /**
     * ������� ������ �� �������
     *
     * @param directoryRecordHandle ��� ������� � �������� primary key ������
     */
    void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle)
		    throws ContactsException;

    /**
     * ���������� ���������, ���������� ��� �������� �� SYSPROP �������
     */
    Collection getSystemProperties();
    
    /**
     * ������ �������� ���������� ���������
     * 
     * @param name ��� ���������
     * @param value �������� ���������
     */
    void updateSystemProperty(String name, String value) throws InvalidValueException;
}
