package su.sergey.contacts.directory.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.businessdelegate.DirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.businessdelegate.impl.DefaultDirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.commands.common.CommandException;

public class ShowDirectoriesCommand extends AbstractCommand implements DirectoryDefinitions {
	
    /**
     * Обрабатывает показ страницы со справочниками (первый раз)
     */
    private String processShowDirectories(DirectoryHttpServletRequest request) throws CommandException  {
    	try {
	        DirectoriesPageIteratorBusinessDelegate iterator =
	            new DefaultDirectoriesPageIteratorBusinessDelegate(DEFAULT_BIG_PAGE_SIZE);
	        if (iterator.current().length > 0) {
	            request.setSessionPageIterator(iterator, SESSION_ITERATOR_DIRECTORIES);
	            request.setFirstPageIterationInfo(iterator);
	            request.setDirectories(iterator.current());
	        }
	        return PageNames.DIRECTORY_SHOW_DIRECTORIES;
    	} catch (ContactsException e) {
    		throw new CommandException(e);
    	} catch (ServletException e) {
    		throw new CommandException(e);
    	}
    }

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws CommandException {
        DirectoryHttpServletRequest directoryRequest = new DirectoryHttpServletRequest(request);
        String result = processShowDirectories(directoryRequest);		
		return result;
	}
}
