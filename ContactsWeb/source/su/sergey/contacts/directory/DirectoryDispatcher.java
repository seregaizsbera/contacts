package su.sergey.contacts.directory;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.directory.commands.AddRecordCommand;
import su.sergey.contacts.directory.commands.PageDirectoriesCommand;
import su.sergey.contacts.directory.commands.PageRecordsCommand;
import su.sergey.contacts.directory.commands.RemoveRecordCommand;
import su.sergey.contacts.directory.commands.ShowDirectoriesCommand;
import su.sergey.contacts.directory.commands.ShowHeaderCommand;
import su.sergey.contacts.directory.commands.ShowRecordCommand;
import su.sergey.contacts.directory.commands.ShowRecordsCommand;
import su.sergey.contacts.directory.commands.UpdateRecordCommand;

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
    	actionToCommands.put(ACTION_SHOW_RECORDS_SUFFIX, ShowRecordsCommand.class);
    	actionToCommands.put(ACTION_PAGE_SHOW_RECORDS_SUFFIX, PageRecordsCommand.class);
    	actionToCommands.put(ACTION_SHOW_MODIFY_RECORD_SUFFIX, ShowRecordCommand.class);
    	actionToCommands.put(ACTION_ADD_RECORD_SUFFIX, AddRecordCommand.class);
        actionToCommands.put(ACTION_EDIT_RECORD_SUFFIX, UpdateRecordCommand.class);
        actionToCommands.put(ACTION_REMOVE_RECORD_SUFFIX, RemoveRecordCommand.class);
        actionToCommands.put(ACTION_SEARCH_RECORDS_SUFFIX, ShowRecordsCommand.class);
    }
	
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return (Class) actionToCommands.get(suffix);
	}
}
