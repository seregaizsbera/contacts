package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.email.EmailParameters;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class ViewEmailsCommand extends DefaultPersonCommand {
	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws InvalidParameterException {
		PersonPacker packer = new PersonPacker(request);
		PersonHandle handle = packer.getHandle();
		DAOBusinessDelegate businessDelegate = getDAOBusinessDelegate(request);
		Email2 emails[] = businessDelegate.getPersonEmails(handle);
		request.setAttribute(EmailParameters.AN_EMAILS, emails);
		request.setAttribute(AN_PERSON_HANDLE, handle);
		return PageNames.PERSON_EMAILS_PAGE;
	}
}
