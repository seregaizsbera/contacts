package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class UpdatePhoneCommand extends DefaultPersonCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		Command processCommand = new su.sergey.contacts.phone.commands.UpdatePhoneCommand();
		Command nextCommand = new ViewPersonPhonesCommand();
		processCommand.execute(request);
		String result = nextCommand.execute(request);
		return result;
	}
}

