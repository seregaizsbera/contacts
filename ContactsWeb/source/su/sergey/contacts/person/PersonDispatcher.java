package su.sergey.contacts.person;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.person.commands.AddEmailCommand;
import su.sergey.contacts.person.commands.AddPhoneCommand;
import su.sergey.contacts.person.commands.CreatePersonCommand;
import su.sergey.contacts.person.commands.PageSearchPersonCommand;
import su.sergey.contacts.person.commands.RemoveEmailCommand;
import su.sergey.contacts.person.commands.RemovePersonCommand;
import su.sergey.contacts.person.commands.RemovePhoneCommand;
import su.sergey.contacts.person.commands.SearchPersonCommand;
import su.sergey.contacts.person.commands.SearchPersonPageCommand;
import su.sergey.contacts.person.commands.SetBasicEmailCommand;
import su.sergey.contacts.person.commands.SetBasicPhoneCommand;
import su.sergey.contacts.person.commands.UpdateEmailCommand;
import su.sergey.contacts.person.commands.UpdatePersonCommand;
import su.sergey.contacts.person.commands.UpdatePhoneCommand;
import su.sergey.contacts.person.commands.ViewEmailsCommand;
import su.sergey.contacts.person.commands.ViewPersonCommand;
import su.sergey.contacts.person.commands.ViewPersonPhonesCommand;

public class PersonDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", SearchPersonPageCommand.class);
    	actionToCommands.put("search", SearchPersonCommand.class);
    	actionToCommands.put("pageSearch", PageSearchPersonCommand.class);
    	actionToCommands.put("view", ViewPersonCommand.class);
    	actionToCommands.put("create", CreatePersonCommand.class);
    	actionToCommands.put("update", UpdatePersonCommand.class);
    	actionToCommands.put("remove", RemovePersonCommand.class);
    	actionToCommands.put("phones", ViewPersonPhonesCommand.class);
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
