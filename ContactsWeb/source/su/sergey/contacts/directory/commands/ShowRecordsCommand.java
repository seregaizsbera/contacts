package su.sergey.contacts.directory.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.businessdelegate.DirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.businessdelegate.impl.DefaultDirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

public class ShowRecordsCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
	
    private void processRecords(DirectoryHttpServletRequest request, int pageSize) throws ContactsException {
    	try {
	        DirectoryMetadata directoryMetadata =
	                getDirectoryMetadata(request, new DirectoryMetadataHandle(request.getTableName()));
	        DirectoryRecordSearchParameters searchParameters =
	                request.getSearchParameters(directoryMetadata.getColumnMetadata());
	        DirectoryRecordsPageIteratorBusinessDelegate iterator =
	                new DefaultDirectoryRecordsPageIteratorBusinessDelegate(searchParameters, pageSize);
	
	        if (iterator.current().length > 0) {
	            request.setSessionPageIterator(iterator, SESSION_ITERATOR_RECORDS);
	            request.setFirstPageIterationInfo(iterator);
	            request.setRecords(iterator.current());
	        }
	        request.setSessionDirectoryMetadata(directoryMetadata);
    	} catch (FieldValidationException e) {
    		throw new ContactsException(e);
    	} catch (ServletException e) {
    		throw new ContactsException(e);
    	}
    }
    
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		processRecords(myRequest, DEFAULT_BIG_PAGE_SIZE);
		return PageNames.INFO;
	}
}
