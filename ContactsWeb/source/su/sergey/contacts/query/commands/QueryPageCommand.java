package su.sergey.contacts.query.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class QueryPageCommand extends DefaultQueryCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		HttpSession session = request.getSession();
		session.removeAttribute(QUERY_RESULT);
		processHistory(request);
		return PageNames.QUERY_PAGE;
	}
}
