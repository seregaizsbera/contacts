package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;

public class RemoveRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
    /**
     * Обрабатывает удаление записи
     */
    private String processRemoveRecord(DirectoryHttpServletRequest request) throws ContactsException {
    	HttpServletRequest request1 = request.getRequest();
        try {
	        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryRecordHandle directoryRecordHandle =
	                new DirectoryRecordHandle(directoryMetadataHandle, request.getRecordPrimaryKey());
            getDAOBusinessDelegate(request.getRequest()).removeDirectoryRecord(directoryRecordHandle);
		    request1.setAttribute(RequestConstants.AN_MESSAGE, "Запись удалена");
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
		request.setAttribute(RequestConstants.AN_MESSAGE, "Запись не удалена: " + e.getMessage());
		request.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request, 1));
		request.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Продолжить");
    }
    
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException {
		DirectoryHttpServletRequest myRequest = new DirectoryHttpServletRequest(request);
		String result = processRemoveRecord(myRequest);
		return result;
	}
}
