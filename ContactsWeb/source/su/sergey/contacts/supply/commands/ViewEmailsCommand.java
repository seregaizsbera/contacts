package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.EmailParameters;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.supply.SupplyPacker;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class ViewEmailsCommand extends DefaultSupplyCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws InvalidParameterException {
		SupplyPacker packer = new SupplyPacker(request);
		SupplyHandle handle = packer.getHandle();
		DAOBusinessDelegate businessDelegate = getDAOBusinessDelegate(request);
		Email2 emails[] = businessDelegate.getSupplyEmails(handle);
		request.setAttribute(EmailParameters.AN_ENTITY, "supply");
		request.setAttribute(EmailParameters.AN_EMAILS, emails);
		request.setAttribute(AN_SUPPLY_HANDLE, handle);
		return PageNames.SUPPLY_EMAILS_PAGE;
	}
}
