package su.sergey.contacts.directory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.directory.commands.PageDirectoriesCommand;
import su.sergey.contacts.directory.commands.ShowDirectoriesCommand;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.common.CommandException;
import su.sergey.contacts.util.commands.factory.CommandFactory;
import su.sergey.contacts.util.commands.factory.DefaultCommandFactory;

/**
 * Диспетчер таблиц.
 * 
 * @author: Сергей Богданов
 */
public class DirectoryDispatcher extends DefaultDispatcher implements DirectoryDefinitions {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", ShowDirectoriesCommand.class);
    	actionToCommands.put(ACTION_SHOW_DIRECTORIES_SUFFIX, ShowDirectoriesCommand.class);
    	actionToCommands.put(ACTION_PAGE_DIRECTORIES_SUFFIX, PageDirectoriesCommand.class);
    }
	
    /**
     * Обрабатывает запрос.
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = null;
        String action = getAction(request);
        CommandFactory factory = DefaultCommandFactory.getInstance();
        Class commandClass = (Class) actionToCommands.get(getSuffix(action));
        Command command = factory.getCommand(commandClass);
        if (command == null) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,
        	                   "Попытка перейти по несуществующему адресу: action = "
                               + action + ". Обратитесь к администратору.");
            return;
        }
        try {
        	nextPage = command.execute(request);
        } catch (CommandException e) {
            throw new ServletException(e);
        }
        redirect(request, response, nextPage);
    }

	//    /**
	//     * Обрабатывает поиск записей по указанным параметрам
	//     */
	//    private String processSearchRecords (DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        return processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//    }
	//
	//    /**
	//     * Обрабатывает показ страницы с записями справочника (первый раз)
	//     */
	//    private String processRecords(DirectoryHttpServletRequest request, int pageSize) throws SberbankException, ServletException {
	//        DirectoryMetadata directoryMetadata =
	//                getDirectoryMetadata(request, new DirectoryMetadataHandle(request.getDirectoryCode()));
	//        DirectoryRecordSearchParameters searchParameters =
	//                request.getSearchParameters(directoryMetadata.getColumnMetadata());
	//        IDirectoryRecordsPageIteratorBusinessDelegate iterator =
	//                new DirectoryRecordsPageIteratorBusinessDelegate(searchParameters, pageSize);
	//
	//        if (iterator.current().length > 0) {
	//            request.setSessionPageIterator(iterator, SESSION_ITERATOR_RECORDS);
	//            request.setFirstPageIterationInfo(iterator);
	//            request.setRecords(iterator.current());
	//        }
	//        request.setSessionDirectoryMetadata(directoryMetadata);
	//
	//        return PageNames.DIRECTORY_SELECT_RECORDS;
	//    }
	//
	//    /**
	//     * Обрабатывает показ заданнной страницы с найденными записями справочника
	//     */
	//    private String processRecordsPage(DirectoryHttpServletRequest request) throws SberbankException, ServletException  {
	//        IDirectoryRecordsPageIteratorBusinessDelegate iterator =
	//                (IDirectoryRecordsPageIteratorBusinessDelegate)request.getSessionPageIterator(SESSION_ITERATOR_RECORDS);
	//
	//        request.setRecords(iterator.goToPage(request.getPage()));
	//        request.setDirectoryMetadata();
	//        request.setPageIterationInfo(iterator);
	//
	//        return PageNames.DIRECTORY_SELECT_RECORDS;
	//    }
	//
	//    /**
	//     * Обрабатывает показ заголовка справочника
	//     */
	//    private String processShowHeader(DirectoryHttpServletRequest request) throws SberbankException, ServletException  {
	//        DirectoryMetadata directoryMetadata =
	//                getDirectoryMetadata(request, new DirectoryMetadataHandle(request.getDirectoryCode()));
	//
	//        request.setDirectoryMetadata(directoryMetadata);
	//
	//        return PageNames.DIRECTORY_SHOW_HEADER;
	//    }
	//
	//    /**
	//     * Обрабатывает обновление заголовка справочника
	//     */
	//    private String processUpdateHeader(DirectoryHttpServletRequest request) throws SberbankException, ServletException  {
	//        DirectoryMetadata directoryMetadata =
	//                getDirectoryMetadata(request, new DirectoryMetadataHandle(request.getDirectoryCode()));
	//
	//        try {
	//            request.updateDirectoryMetadataFromForm(directoryMetadata);
	//            getDAOBusinessDelegate(request.getRequest()).updateDirectoryMetadata(directoryMetadata);
	//        } catch (IllegalArgumentException e) {
	//            request.setMessage(MESSAGE_HEADER_NOT_UPDATED + "&nbsp;" + e.getLocalizedMessage());
	//        }
	//
	//        return processShowHeader(request);
	//    }
	//
	//    /**
	//     * Обрабатывает обновление заголовка справочника
	//     */
	//    private String processShowModifyRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	//
	//        request.setDirectoryMetadata(directoryMetadata);
	//        try {
	//            DirectoryRecord directoryRecord = getDirectoryRecord(request, directoryMetadataHandle);
	//            request.setRecord(directoryRecord);
	//        } catch (IllegalArgumentException e) {
	//            // Если не удалось взять Pk или найти такую запись в базе - ничего не делаем, показываем страницу для добавления
	//        };
	//
	//        return PageNames.DIRECTORY_MODIFY_RECORD;
	//    }
	//
	//    /**
	//     * Обрабатывает добавление записи
	//     */
	//    private String processAddRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	//
	//        try {
	//            DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
	//            getDAOBusinessDelegate(request.getRequest()).addDirectoryRecord(directoryMetadataHandle, directoryRecord);
	//            request.setMessage(MESSAGE_RECORD_ADDED);
	//        } catch (Exception e) {
	//            request.setMessage(MESSAGE_RECORD_NOT_ADDED);
	//        }
	//
	//        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//        return PageNames.DIRECTORY_SHOW_RECORDS;
	//    }
	//
	//    /**
	//     * Обрабатывает редактирование записи
	//     */
	//    private String processEditRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryMetadata directoryMetadata = getDirectoryMetadata(request, directoryMetadataHandle);
	//
	//        try {
	//            DirectoryRecord directoryRecord = request.getDirectoryRecordFromForm(directoryMetadata.getColumnMetadata());
	//            getDAOBusinessDelegate(request.getRequest()).updateDirectoryRecord(directoryMetadataHandle, directoryRecord);
	//            request.setMessage(MESSAGE_RECORD_UPDATED);
	//        } catch (Exception e) {
	//            request.setMessage(MESSAGE_RECORD_NOT_UPDATED);
	//        }
	//        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//
	//        return PageNames.DIRECTORY_SHOW_RECORDS;
	//    }
	//
	//    /**
	//     * Обрабатывает удаление записи
	//     */
	//    private String processDeleteRecord(DirectoryHttpServletRequest request) throws SberbankException, ServletException {
	//        DirectoryMetadataHandle directoryMetadataHandle = new DirectoryMetadataHandle(request.getDirectoryCode());
	//        DirectoryRecordHandle directoryRecordHandle =
	//                new DirectoryRecordHandle(directoryMetadataHandle, request.getRecordPrimaryKey());
	//
	//        try {
	//            getDAOBusinessDelegate(request.getRequest()).deleteDirectoryRecord(directoryRecordHandle);
	//            request.setMessage(MESSAGE_RECORD_DELETED);
	//        } catch (Exception e) {
	//            request.setMessage(MESSAGE_RECORD_NOT_DELETED);
	//        }
	//        processRecords(request, DEFAULT_BIG_PAGE_SIZE);
	//
	//        return PageNames.DIRECTORY_SHOW_RECORDS;
	//    }
	//
	//    /**
	//     * Находит через бизнес-делегата запись с primary ключом, который содержится в request
	//     */
	//    private DirectoryRecord getDirectoryRecord(DirectoryHttpServletRequest request, DirectoryMetadataHandle handle)
	//            throws SberbankException {
	//        String primaryKey = request.getRecordPrimaryKey();
	//        DirectoryRecordHandle directoryRecordHandle = new DirectoryRecordHandle(handle, primaryKey);
	//        return getDAOBusinessDelegate(request.getRequest()).findDirectoryRecord(directoryRecordHandle);
	//    }
	//
	//
	//    /**
	//     * Берет через бизнес-делегата список колонок текущего справочника
	//     */
	//    private DirectoryMetadata getDirectoryMetadata(DirectoryHttpServletRequest request, DirectoryMetadataHandle handle)
	//            throws SberbankException {
	//        return getDAOBusinessDelegate(request.getRequest()).findDirectoryMetadata(handle);
	//    }
	//
	//    /**
	//     * Устанавливает через бизнес-делегата список колонок текущего справочника
	//     */
	//    private void setDirectoryMetadata(DirectoryHttpServletRequest request, DirectoryMetadata directoryMetadata)
	//            throws SberbankException {
	//        getDAOBusinessDelegate(request.getRequest()).updateDirectoryMetadata(directoryMetadata);
	//    }
}
