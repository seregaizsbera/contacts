package su.sergey.contacts.person;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.person.commands.PageSearchPersonCommand;
import su.sergey.contacts.person.commands.SearchPersonCommand;
import su.sergey.contacts.person.commands.SearchPersonPageCommand;

public class PersonDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", SearchPersonPageCommand.class);
    	actionToCommands.put("searchPersons", SearchPersonCommand.class);
    	actionToCommands.put("pageSearchPersons", PageSearchPersonCommand.class);
    }
    
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return (Class) actionToCommands.get(suffix);
	}
}
