package su.sergey.contacts.phone;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.phone.commands.UpdatePhoneCommand;

public class PhoneDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("updatePhone", UpdatePhoneCommand.class);
    }
    
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return (Class) actionToCommands.get(suffix);
	}
}
