package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

public class ShowRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
    /**
     * Обрабатывает обновление заголовка справочника
     */
    private void processShowModifyRecord(DirectoryHttpServletRequest request) throws ContactsException {
    	try {
	        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	        request.setDirectoryMetadata(directoryMetadata);
	        DirectoryRecord directoryRecord = getDirectoryRecord(request, directoryMetadataHandle);
	        request.setRecord(directoryRecord);
    	} catch (FieldValidationException e) {
    		throw new ContactsException(e);
    	}
    }

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		processShowModifyRecord(myRequest);
		return PageNames.DIRECTORY_MODIFY_RECORD;
	}
}
