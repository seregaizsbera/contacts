package su.sergey.contacts.directory.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.businessdelegate.DirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.businessdelegate.impl.DefaultDirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.AbstractCommand;

public class PageDirectoriesCommand extends AbstractCommand implements DirectoryDefinitions {
	
    /**
     * ������������ ����� �������� � ���������
     */
    private String processPageDirectories(DirectoryHttpServletRequest request) throws ContactsException  {
    	try {
	        DirectoriesPageIteratorBusinessDelegate iterator =
	            (DefaultDirectoriesPageIteratorBusinessDelegate) request.getSessionPageIterator(SESSION_ITERATOR_DIRECTORIES);
            request.setDirectories(iterator.goToPage(request.getPage()));
            request.setPageIterationInfo(iterator);
	        return PageNames.DIRECTORY_SHOW_DIRECTORIES;
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
        DirectoryHttpServletRequest directoryRequest = new DirectoryHttpServletRequest(request);
        String result = processPageDirectories(directoryRequest);		
		return result;
	}
}
