package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;

public class DeleteRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
    /**
     * Обрабатывает удаление записи
     */
    private String processDeleteRecord(DirectoryHttpServletRequest request) throws ContactsException {
        try {
	        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryRecordHandle directoryRecordHandle =
	                new DirectoryRecordHandle(directoryMetadataHandle, request.getRecordPrimaryKey());
            getDAOBusinessDelegate(request.getRequest()).deleteDirectoryRecord(directoryRecordHandle);
            request.setMessage(MESSAGE_RECORD_DELETED);
        } catch (FieldValidationException e) {
            request.setMessage(MESSAGE_RECORD_NOT_DELETED + ": " + e.getMessage());
        } catch (ContactsException e) {
            request.setMessage(MESSAGE_RECORD_NOT_DELETED + ": " + e.getMessage());
        }
        processRecordsPage(request);
        return PageNames.DIRECTORY_SHOW_RECORDS;
    }


	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		String result = processDeleteRecord(myRequest);
		return result;
	}
}
