package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;

public class UpdateRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {

    /**
     * Обрабатывает редактирование записи
     */
    private String processEditRecord(DirectoryHttpServletRequest request) throws ContactsException {
    	try {
	        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
            DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
            Integer oid = request.getRecordPrimaryKey();
            DirectoryRecordHandle recordHandle = new DirectoryRecordHandle(directoryMetadataHandle, oid);
            getDAOBusinessDelegate(request.getRequest()).updateDirectoryRecord(recordHandle, directoryRecord);
            request.setMessage(MESSAGE_RECORD_UPDATED);
        } catch (FieldValidationException e) {
            request.setMessage(MESSAGE_RECORD_NOT_UPDATED + ": " + e.getMessage());
        }
        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
        return PageNames.DIRECTORY_SHOW_RECORDS;
    }
    
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		String result = processEditRecord(myRequest);
		return result;
	}
}
