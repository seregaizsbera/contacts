package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.phone.PhonePacker;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class RemovePhoneCommand extends DefaultPersonCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		PhonePacker phonePacker = new PhonePacker(request);
		PhoneHandle phoneHandle = phonePacker.getHandle();
		PersonPacker personPacker = new PersonPacker(request);
		PersonHandle personHandle = personPacker.getHandle();
		delegate.removePersonPhone(personHandle, phoneHandle);
		Command nextCommand = new ViewPersonPhonesCommand();
		String result = nextCommand.execute(request);
		return result;
	}
}
