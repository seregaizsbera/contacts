package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;

public class AddRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
    /**
     * ������������ ���������� ������
     */
    private String processAddRecord(DirectoryHttpServletRequest request) throws ContactsException {
        try {
	        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	        DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
	        getDAOBusinessDelegate(request.getRequest()).addDirectoryRecord(directoryMetadataHandle, directoryRecord);
	        request.setMessage(MESSAGE_RECORD_ADDED);
        } catch (FieldValidationException e) {
	        request.setMessage(MESSAGE_RECORD_NOT_ADDED + ": " + e.getMessage());
        } catch (ContactsException e) {
	        request.setMessage(MESSAGE_RECORD_NOT_ADDED + ": " + e.getMessage());
        }
        processRecordsPage(request);
        return PageNames.DIRECTORY_SHOW_RECORDS;
    }

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		String result = processAddRecord(myRequest);
        return result;
    }	
}