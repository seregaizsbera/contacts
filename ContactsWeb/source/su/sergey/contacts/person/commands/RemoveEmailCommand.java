package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.email.EmailPacker;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class RemoveEmailCommand extends DefaultPersonCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		EmailPacker emailPacker = new EmailPacker(request);
		EmailHandle emailHandle = emailPacker.getHandle();
		PersonPacker personPacker = new PersonPacker(request);
		PersonHandle personHandle = personPacker.getHandle();
		delegate.removePersonEmail(personHandle, emailHandle);
		Command nextCommand = new ViewEmailsCommand();
		String result = nextCommand.execute(request);
		return result;
	}
}
