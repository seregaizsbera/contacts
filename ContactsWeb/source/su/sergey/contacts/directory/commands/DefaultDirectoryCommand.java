package su.sergey.contacts.directory.commands;

import javax.servlet.ServletException;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.businessdelegate.DirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.businessdelegate.impl.DefaultDirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.AbstractCommand;

abstract class DefaultDirectoryCommand extends AbstractCommand implements DirectoryDefinitions {
    protected DirectoryMetadata getDirectoryMetadata(DirectoryHttpServletRequest request, DirectoryMetadataHandle handle)
            throws ContactsException {
        return getDAOBusinessDelegate(request.getRequest()).findDirectoryMetadata(handle);
    }
    
    protected DirectoryRecord getDirectoryRecord(DirectoryHttpServletRequest request, DirectoryMetadataHandle handle)
            throws ContactsException {
        try {
	        Integer primaryKey = request.getRecordPrimaryKey();
	        DirectoryRecordHandle directoryRecordHandle = new DirectoryRecordHandle(handle, primaryKey);
	        return getDAOBusinessDelegate(request.getRequest()).findDirectoryRecord(directoryRecordHandle);
        } catch (FieldValidationException e) {
        	return null;
        }
    }
    
    protected void processRecords(DirectoryHttpServletRequest request, int pageSize) throws ContactsException {
    	try {
	        DirectoryMetadata directoryMetadata =
	                getDirectoryMetadata(request, new DirectoryMetadataHandle(request.getTableName()));
	        DirectoryRecordSearchParameters searchParameters =
	                request.getSearchParameters(directoryMetadata.getColumnMetadata());
	        DirectoryRecordsPageIteratorBusinessDelegate oldIterator =
	                (DirectoryRecordsPageIteratorBusinessDelegate) request.getSessionPageIterator(SESSION_ITERATOR_RECORDS);
	        if (oldIterator != null) {
	        	oldIterator.freeResources();
    	        request.removeSessionPageIterator(SESSION_ITERATOR_RECORDS);
	        }
	        DirectoryRecordsPageIteratorBusinessDelegate iterator =
	                new DefaultDirectoryRecordsPageIteratorBusinessDelegate(searchParameters, pageSize);
            request.setSessionPageIterator(iterator, SESSION_ITERATOR_RECORDS);
            request.setPageIterationInfo(iterator);
            request.setRecords(iterator.current());
            request.setSessionDirectoryRecordSearchParameters(searchParameters);
	        request.setSessionDirectoryMetadata(directoryMetadata);
    	} catch (FieldValidationException e) {
    		throw new ContactsException(e);
    	} catch (ServletException e) {
    		throw new ContactsException(e);
    	}
    }

    /**
     * Обрабатывает показ заданнной страницы с найденными записями справочника
     */
    protected void processRecordsPage(DirectoryHttpServletRequest request) throws ContactsException {
    	try {
	        DirectoryRecordsPageIteratorBusinessDelegate iterator =
	                (DirectoryRecordsPageIteratorBusinessDelegate)request.getSessionPageIterator(SESSION_ITERATOR_RECORDS);
	        Integer page = request.getPage();
	        DirectoryRecord records[];
	        if (page == null) {
	        	records = iterator.current();
	        } else {
	        	records = iterator.goToPage(page.intValue());
	        }
	        request.setRecords(records);
	        request.setDirectoryMetadata();
	        request.setPageIterationInfo(iterator);
    	} catch (FieldValidationException e) {
        	throw new ContactsException(e);
    	} catch (ServletException e) {
        	throw new ContactsException(e);
        }
    }
}
