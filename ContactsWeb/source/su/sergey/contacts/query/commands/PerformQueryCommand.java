package su.sergey.contacts.query.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class PerformQueryCommand extends DefaultQueryCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		String query = request.getParameter(QUERY_TEXT);
		QueryResult result = delegate.performQuery(query);
		HttpSession session = request.getSession(false);
		session.setAttribute(QUERY_RESULT, result);
		processHistory(request);
		return PageNames.QUERY_PAGE;
	}
}
