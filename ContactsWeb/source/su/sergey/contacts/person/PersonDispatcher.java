package su.sergey.contacts.person;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.email.commands.UpdateEmailCommand;
import su.sergey.contacts.person.commands.AddEmailCommand;
import su.sergey.contacts.person.commands.AddPhoneCommand;
import su.sergey.contacts.person.commands.CreateCommand;
import su.sergey.contacts.person.commands.PageSearchCommand;
import su.sergey.contacts.person.commands.RemoveCommand;
import su.sergey.contacts.person.commands.RemoveEmailCommand;
import su.sergey.contacts.person.commands.RemovePhoneCommand;
import su.sergey.contacts.person.commands.SearchCommand;
import su.sergey.contacts.person.commands.SearchPageCommand;
import su.sergey.contacts.person.commands.SetBasicEmailCommand;
import su.sergey.contacts.person.commands.SetBasicPhoneCommand;
import su.sergey.contacts.person.commands.UpdateCommand;
import su.sergey.contacts.person.commands.ViewCommand;
import su.sergey.contacts.person.commands.ViewEmailsCommand;
import su.sergey.contacts.person.commands.ViewPhonesCommand;
import su.sergey.contacts.phone.commands.UpdatePhoneCommand;

public class PersonDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", SearchPageCommand.class);
    	actionToCommands.put("search", SearchCommand.class);
    	actionToCommands.put("pageSearch", PageSearchCommand.class);
    	actionToCommands.put("view", ViewCommand.class);
    	actionToCommands.put("create", CreateCommand.class);
    	actionToCommands.put("update", UpdateCommand.class);
    	actionToCommands.put("remove", RemoveCommand.class);
    	actionToCommands.put("phones", ViewPhonesCommand.class);
    	actionToCommands.put("updatePhone", UpdatePhoneCommand.class);
    	actionToCommands.put("setBasicPhone", SetBasicPhoneCommand.class);
    	actionToCommands.put("removePhone", RemovePhoneCommand.class);
    	actionToCommands.put("addPhone", AddPhoneCommand.class);
    	actionToCommands.put("emails", ViewEmailsCommand.class);
    	actionToCommands.put("updateEmail", UpdateEmailCommand.class);
    	actionToCommands.put("setBasicEmail", SetBasicEmailCommand.class);
    	actionToCommands.put("removeEmail", RemoveEmailCommand.class);
    	actionToCommands.put("addEmail", AddEmailCommand.class);
    }
    
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return (Class) actionToCommands.get(suffix);
	}
}
