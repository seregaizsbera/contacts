package su.sergey.contacts.sessionfacade.businessdelegate;

import java.io.File;

import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.DuplicateInstanceException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.report.ReportException;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

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
    void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle)
		    throws ContactsException;
		    
    QueryResult performQuery(String sql);
    
    String[] getLastQueries();
    
    PersonHandle createPerson(PersonAttributes person) throws MultipleFieldsValidationException;
    
    void updatePerson(PersonHandle handle, PersonAttributes person) throws MultipleFieldsValidationException;
    
    Person2 findPerson(PersonHandle handle);
    
    void removePerson(PersonHandle handle);
    
   	Phone2[] getPersonPhones(PersonHandle handle);
   	
   	PhoneHandle addPersonPhone(PersonHandle handle, PhoneAttributes phone);
   	
   	void updatePhone(PhoneHandle handle, PhoneAttributes phone);
   	
   	void removePersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle);
   	
   	void setBasicPersonPhone(PersonHandle personHandle, PhoneHandle phoneHandle);
   	
   	Email2[] getPersonEmails(PersonHandle handle);
   	
   	EmailHandle addPersonEmail(PersonHandle handle, EmailAttributes email);
   	
   	void updateEmail(EmailHandle handle, EmailAttributes email);
   	
   	void removePersonEmail(PersonHandle personHandle, EmailHandle emailHandle);
   	
   	void setBasicPersonEmail(PersonHandle personHandle, EmailHandle emailHandle);
   	
	Object getSystemPropertyValue(String name) throws PropertyNotFoundException;
	
    void setSystemPropertyValue(String name, Object value) throws PropertyNotFoundException;
    
    void setSystemPropertyValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException;
	
	Supply2 findSupply(SupplyHandle handle);
	
	SupplyHandle createSupply(SupplyAttributes attributes) throws MultipleFieldsValidationException;
	
	void updateSupply(SupplyHandle handle, SupplyAttributes attributes) throws MultipleFieldsValidationException;
	
	void removeSupply(SupplyHandle handle);
	
	Phone2[] getSupplyPhones(SupplyHandle handle);
	
	PhoneHandle addSupplyPhone(SupplyHandle supplyHandle, PhoneAttributes phone);
	
	void removeSupplyPhone(SupplyHandle supplyHandle, PhoneHandle phoneHandle);
	
	Email2[] getSupplyEmails(SupplyHandle handle);
	
	EmailHandle addSupplyEmail(SupplyHandle supplyHandle, EmailAttributes email);
	
	void removeSupplyEmail(SupplyHandle supplyHandle, EmailHandle emailHandle);
	
	File buildPersonReport(PersonSearchParameters searchParameters, String description) throws ReportException;
	
	File buildSupplyReport(SupplySearchParameters searchParameters, String description) throws ReportException;
	
	CallExpenseData findCallExpense(CallExpenseHandle handle);
	
	CallExpenseHandle createCallExpense(CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException;

	void updateCallExpense(CallExpenseHandle handle, CallExpenseData callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException;
	
	void removeCallExpense(CallExpenseHandle handle);
}
