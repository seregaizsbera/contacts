package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class UpdateEmailCommand extends DefaultSupplyCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		Command processCommand = new su.sergey.contacts.email.commands.UpdateEmailCommand();
		Command nextCommand = new ViewEmailsCommand();
		processCommand.execute(request);
		String result = nextCommand.execute(request);
		return result;
	}
}

