package su.sergey.contacts.report;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.report.commands.BuildPersonsCommand;
import su.sergey.contacts.report.commands.BuildSuppliesCommand;
import su.sergey.contacts.report.commands.PagePersonsCommand;
import su.sergey.contacts.report.commands.PageSuppliesCommand;

public class ReportDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("buildPersons", BuildPersonsCommand.class);
    	actionToCommands.put("buildSupplies", BuildSuppliesCommand.class);
    	actionToCommands.put("pagePersons", PagePersonsCommand.class);
    	actionToCommands.put("pageSupplies", PageSuppliesCommand.class);
    }
    
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return (Class) actionToCommands.get(suffix);
	}
}
