package su.sergey.contacts.query.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.commands.common.AbstractCommand;

public abstract class DefaultQueryCommand extends AbstractCommand {
	protected final String QUERY_HISTORY = "queryHistory";
	protected final String QUERY_RESULT = "queryResult";
	protected final String QUERY_TEXT = "queryText";

    protected void processHistory(HttpServletRequest request) throws ContactsException {
    	HttpSession session = request.getSession();
    	DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
    	String history[] = delegate.getLastQueries();
    	session.setAttribute(QUERY_HISTORY, history);
    }

}

