package su.sergey.contacts.directory.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.directory.DirectoryDefinitions;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.directory.wrappers.FieldValidationException;
import su.sergey.contacts.exceptions.ContactsException;

public class AddRecordCommand extends DefaultDirectoryCommand implements DirectoryDefinitions {
    /**
     * Обрабатывает добавление записи
     */
    private String processAddRecord(DirectoryHttpServletRequest request) throws ContactsException {
        HttpServletRequest request1 = request.getRequest();
        try {
            DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getTableName());
	        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	        DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
	        getDAOBusinessDelegate(request.getRequest()).addDirectoryRecord(directoryMetadataHandle, directoryRecord);
            request1.setAttribute(RequestConstants.AN_MESSAGE, "Запись добавлена");
		    request1.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Продолжить");
     		request1.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request1, 1));
        } catch (FieldValidationException e) {
            request1.setAttribute(RequestConstants.AN_MESSAGE, "Запись не добавлена: " + e.getMessage());
		    request1.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Повторить");
     		request1.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request1, 0));
		    request1.setAttribute(RequestConstants.AN_ALTERNATE_MESSAGE, "Вернуться");
     		request1.setAttribute(RequestConstants.AN_ALTERNATE_URL, getReturnUrl(request1, 1));
        } catch (ContactsException e) {
            request1.setAttribute(RequestConstants.AN_MESSAGE, "Запись не добавлена: " + e.getMessage());
		    request1.setAttribute(RequestConstants.AN_NEXT_MESSAGE, "Повторить");
     		request1.setAttribute(RequestConstants.AN_NEXT_URL, getReturnUrl(request1, 0));
		    request1.setAttribute(RequestConstants.AN_ALTERNATE_MESSAGE, "Вернуться");
     		request1.setAttribute(RequestConstants.AN_ALTERNATE_URL, getReturnUrl(request1, 1));
        }
        return PageNames.MESSAGE_PAGE;
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
