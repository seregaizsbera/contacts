package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.admin.RoleNames;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.util.InquiryAliases;
import su.sergey.contacts.person.PersonPacker;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;

public class ViewCommand extends DefaultPersonCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) {
		String result = PageNames.PERSON_SHOW_PERSON;
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		// if (request.isUserInRole(RoleNames.SERGEY))
		if (request.getSession(false).getAttribute(RoleNames.SERGEY) != null) {
		    saveInquiryData(request, InquiryAliases.SHNIPPERS);
		}
  		PersonHandle handle = new PersonPacker(request).getHandle();
  		if (handle != null) {
            Person2 person = delegate.findPerson(handle);
            if (person != null) {
         		request.setAttribute(AN_PERSON, person);
            } else {
            	request.setAttribute(RequestConstants.AN_MESSAGE, "�������� �� �������");
            	result = PageNames.MESSAGE_PAGE;
            }
  		}
		return result;
	}
}
