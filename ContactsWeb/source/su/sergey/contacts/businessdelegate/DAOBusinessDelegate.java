package su.sergey.contacts.businessdelegate;

import java.util.Collection;

import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.query.valueobjects.QueryResult;

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
		    
    PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException;
    
    void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException;
    
    PersonAttributes findPerson(PersonHandle handle);
    
    QueryResult performQuery(String sql) throws ContactsException;
    
    String[] getLastQueries() throws ContactsException;
}
