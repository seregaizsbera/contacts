package su.sergey.contacts.directory.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.businessdelegate.DirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;

public class PageRecordsCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
    /**
     * Обрабатывает показ заданнной страницы с найденными записями справочника
     */
    private void processRecordsPage(DirectoryHttpServletRequest request) throws ContactsException {
    	try {
	        DirectoryRecordsPageIteratorBusinessDelegate iterator =
	                (DirectoryRecordsPageIteratorBusinessDelegate)request.getSessionPageIterator(SESSION_ITERATOR_RECORDS);
	        request.setRecords(iterator.goToPage(request.getPage()));
	        request.setDirectoryMetadata();
	        request.setPageIterationInfo(iterator);
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
		processRecordsPage(myRequest);
        return PageNames.DIRECTORY_SHOW_RECORDS;
	}
}
