package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.EmailPacker;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class RemoveEmailCommand extends DefaultSupplyCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		EmailPacker emailPacker = new EmailPacker(request);
		EmailHandle emailHandle = emailPacker.getHandle();
		SupplyPacker supplyPacker = new SupplyPacker(request);
		SupplyHandle supplyHandle = supplyPacker.getHandle();
		delegate.removeSupplyEmail(supplyHandle, emailHandle);
		Command nextCommand = new ViewEmailsCommand();
		String result = nextCommand.execute(request);
		return result;
	}
}
