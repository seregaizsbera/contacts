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
     * Возвращает метаданные таблицы (DirectoryMetadata).
     *
     * @param directoryMetadataHandle структура, содержащая имя таблицы
     * @return DirectoryMetadata метаданные таблицы
     */
    DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle)
		    throws ContactsException;

    /**
     * Обновляет метаданные таблицы
     *
     * @param directoryMetadataHandle дескриптор таблицы
     * @param directoryMetadata метаданные таблицы
     */
    void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle, DirectoryMetadata directoryMetadata)
		    throws ContactsException;

    /**
     * Возвращает список записей таблицы
     *
     * @param searchParameters параметры поиска
     * @param start начальная запись
     * @param length длина выборки
     * @return Collection список записей
     */
    Collection findDirectoryRecords(
        DirectoryRecordSearchParameters searchParameters,
        int start,
        int length);

    /**
     * Возвращает запись таблицы
     *
     * @param directoryRecordHandle имя таблицы и значение primary key записи
     * @return DirectoryRecord запись таблицы
     */
    DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) throws ContactsException;

    /**
     * Обновляет запись таблицы
     *
     * @param directoryRecordHandle структура,
     *                              содержащая имя таблицы и идентификтор записи
     * @param directoryRecord запись таблицы
     */
    void updateDirectoryRecord(
        DirectoryRecordHandle directoryRecordHandle,
        DirectoryRecord directoryRecord) throws ContactsException;

    /**
     * Добавляет запись в таблицу
     *
     * @param directoryMetadataHandle структура, содержащая имя таблицы
     * @param directoryRecord запись таблицы
     */
    void addDirectoryRecord(
        DirectoryMetadataHandle directoryMetadataHandle,
        DirectoryRecord directoryRecord) throws ContactsException;

    /**
     * Удаляет запись из таблицы
     *
     * @param directoryRecordHandle имя таблицы и значение primary key записи
     */
    void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle)
		    throws ContactsException;
		    
    PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException;
    
    void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException;
    
    PersonAttributes findPerson(PersonHandle handle);
    
    QueryResult performQuery(String sql) throws ContactsException;
    
    String[] getLastQueries() throws ContactsException;
}
