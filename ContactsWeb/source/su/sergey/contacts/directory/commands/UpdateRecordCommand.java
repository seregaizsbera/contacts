package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
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
    	HttpServletRequest request1 = request.getRequest();
    	try {
	        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
            DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
            Integer oid = request.getRecordPrimaryKey();
            DirectoryRecordHandle recordHandle = new DirectoryRecordHandle(directoryMetadataHandle, oid);
            getDAOBusinessDelegate(request.getRequest()).updateDirectoryRecord(recordHandle, directoryRecord);
		    request1.setAttribute(RequestConstants.AN_MESSAGE, "Запись обновлена");
		    request1.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request1, 1));
		    request1.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Продолжить");
        } catch (FieldValidationException e) {
		    setError(request1, e);
        } catch (ContactsException e) {
		    setError(request1, e);
        }
        return PageNames.MESSAGE_PAGE;
    }

    private void setError(HttpServletRequest request, Exception e) {
	    request.setAttribute(RequestConstants.AN_MESSAGE, "Запись не обновлена: " + e.getMessage());
	    request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 0));
	    request.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Повторить");
	    request.setAttribute(RequestConstants.AN_ALTERNATE_URL, getReturnUrl(request, 1));
	    request.setAttribute(RequestConstants.AN_ALTERNATE_MESSAGE, "Отказаться");
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
