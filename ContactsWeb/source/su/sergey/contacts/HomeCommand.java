package su.sergey.contacts;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class HomeCommand implements Command {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		return PageNames.MAIN;
	}
}

