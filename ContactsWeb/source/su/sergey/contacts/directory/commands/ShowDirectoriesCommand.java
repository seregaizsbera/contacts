package su.sergey.contacts.directory.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.businessdelegate.DirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.businessdelegate.impl.DefaultDirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectorySearchParameters;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.pageiterator.businessdelegate.PageIteratorBusinessDelegate;

public class ShowDirectoriesCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
	
    /**
     * Обрабатывает показ страницы с таблицами (первый раз)
     */
    private String processShowDirectories(DirectoryHttpServletRequest request) throws ContactsException  {
    	try {
	        PageIteratorBusinessDelegate oldIterator = request.getSessionPageIterator(SESSION_ITERATOR_DIRECTORIES);
	        if (oldIterator != null) {
	        	oldIterator.freeResources();
	        	request.removeSessionPageIterator(SESSION_ITERATOR_DIRECTORIES);
	        }
	        DirectorySearchParameters searchParameters = request.getSearchParameters();
	        DirectoriesPageIteratorBusinessDelegate iterator = new DefaultDirectoriesPageIteratorBusinessDelegate(JNDINames.DIRECTORIES_PAGE_ITERATOR_REFERENCE, searchParameters, DEFAULT_BIG_PAGE_SIZE);
            request.setSessionDirectorySearchParameters(searchParameters);
	        if (iterator.current().length > 0) {
	            request.setSessionPageIterator(iterator, SESSION_ITERATOR_DIRECTORIES);
	            request.setPageIterationInfo(iterator);
	            request.setDirectories(iterator.current());
	        }
	        return PageNames.DIRECTORY_SHOW_DIRECTORIES;
    	} catch (ServletException e) {
    		throw new ContactsException(e);
    	}
    }

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
        DirectoryHttpServletRequest directoryRequest = new DirectoryHttpServletRequest(request);
        String result = processShowDirectories(directoryRequest);		
		return result;
	}
}
