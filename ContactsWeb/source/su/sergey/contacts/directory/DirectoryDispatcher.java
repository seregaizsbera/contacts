package su.sergey.contacts.directory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.directory.commands.AddRecordCommand;
import su.sergey.contacts.directory.commands.DeleteRecordCommand;
import su.sergey.contacts.directory.commands.PageDirectoriesCommand;
import su.sergey.contacts.directory.commands.PageRecordsCommand;
import su.sergey.contacts.directory.commands.ShowDirectoriesCommand;
import su.sergey.contacts.directory.commands.ShowHeaderCommand;
import su.sergey.contacts.directory.commands.ShowRecordCommand;
import su.sergey.contacts.directory.commands.ShowRecordsCommand;
import su.sergey.contacts.directory.commands.UpdateHeaderCommand;
import su.sergey.contacts.directory.commands.UpdateRecordCommand;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
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
    	actionToCommands.put(ACTION_SHOW_HEADER_SUFFIX, ShowHeaderCommand.class);
    	actionToCommands.put(ACTION_UPDATE_HEADER_SUFFIX, UpdateHeaderCommand.class);
    	actionToCommands.put(ACTION_SHOW_RECORDS_SUFFIX, ShowRecordsCommand.class);
    	actionToCommands.put(ACTION_PAGE_SHOW_RECORDS_SUFFIX, PageRecordsCommand.class);
    	actionToCommands.put(ACTION_SHOW_MODIFY_RECORD_SUFFIX, ShowRecordCommand.class);
    	actionToCommands.put(ACTION_ADD_RECORD_SUFFIX, AddRecordCommand.class);
        actionToCommands.put(ACTION_EDIT_RECORD_SUFFIX, UpdateRecordCommand.class);
        actionToCommands.put(ACTION_DELETE_RECORD_SUFFIX, DeleteRecordCommand.class);
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
        } catch (ContactsException e) {
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
	//
	//
	//
	//    /**
	//     * Устанавливает через бизнес-делегата список колонок текущего справочника
	//     */
	//    private void setDirectoryMetadata(DirectoryHttpServletRequest request, DirectoryMetadata directoryMetadata)
	//            throws SberbankException {
	//        getDAOBusinessDelegate(request.getRequest()).updateDirectoryMetadata(directoryMetadata);
	//    }
}
