package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.factory.DefaultCommandFactory;

public class UpdateHeaderCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {

    private String processUpdateHeader(DirectoryHttpServletRequest request) throws ContactsException  {
    	try {
    		DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryMetadata directoryMetadata =
	                getDirectoryMetadata(request, directoryMetadataHandle);
            request.updateDirectoryMetadataFromForm(directoryMetadata);
            getDAOBusinessDelegate(request.getRequest()).updateDirectoryMetadata(directoryMetadataHandle, directoryMetadata);
            request.setMessage(MESSAGE_HEADER_UPDATED);
        } catch (FieldValidationException e) {
            request.setMessage(MESSAGE_HEADER_NOT_UPDATED + "&nbsp;" + e.getMessage());
        }
        Command showHeaderCommand = DefaultCommandFactory.getInstance().getCommand(ShowHeaderCommand.class);
        return showHeaderCommand.execute(request.getRequest());
    }
    
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		String result = processUpdateHeader(new DirectoryHttpServletRequest(request));
		return result;
	}
}
