package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

public class ShowHeaderCommand extends AbstractCommand implements DirectoryDefinitions {
    /**
     * Обрабатывает показ заголовка справочника
     */
    private String processShowHeader(DirectoryHttpServletRequest request) throws ContactsException  {
    	try {
		    DirectoryMetadata directoryMetadata =
		            getDirectoryMetadata(request, new DirectoryMetadataHandle(request.getTableName()));
		    request.setDirectoryMetadata(directoryMetadata);		
    	} catch (FieldValidationException e) {
    		throw new ContactsException(e);
    	}
	    return PageNames.DIRECTORY_SHOW_HEADER;
    }
    
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		String pageName = processShowHeader(new DirectoryHttpServletRequest(request));
		return pageName;
	}
}

