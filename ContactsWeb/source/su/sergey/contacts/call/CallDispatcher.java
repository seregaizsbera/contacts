package su.sergey.contacts.call;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.call.commands.CreateExpenseCommand;
import su.sergey.contacts.call.commands.RemoveExpenseCommand;
import su.sergey.contacts.call.commands.UpdateExpenseCommand;
import su.sergey.contacts.call.commands.ViewExpenseCommand;

public class CallDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", ViewExpenseCommand.class);
    	actionToCommands.put("viewExpense", ViewExpenseCommand.class);
    	actionToCommands.put("createExpense", CreateExpenseCommand.class);
    	actionToCommands.put("updateExpense", UpdateExpenseCommand.class);
    	actionToCommands.put("removeExpense", RemoveExpenseCommand.class);
    }
    
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return (Class) actionToCommands.get(suffix);
	}
}

