package su.sergey.contacts.businessdelegate;

import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.person.valueobjects.Person2;
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
		    
    QueryResult performQuery(String sql);
    
    String[] getLastQueries();
    
    PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException;
    
    void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException;
    
    Person2 findPerson(PersonHandle handle);
    
    void removePerson(PersonHandle handle);
    
   	InquiryObject[] inquireTable(String tableName);
}
