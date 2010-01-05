package su.sergey.contacts;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.factory.CommandFactory;
import su.sergey.contacts.util.commands.factory.DefaultCommandFactory;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

/**
 * ������� ����� ��� ���� �����������, ���������� ����� ��� ��� ����������������.
 *
 * @author: ������ ��������
 */
public abstract class DefaultDispatcher extends HttpServlet {
    protected static final String AN_ERROR = "error";

    /**
     * ���������� �������� ������� �� �������� ������������ ���������� ��������.
     */
    public static String getAction(HttpServletRequest request) {
        return ParameterUtil.getString(request, RequestConstants.PN_ACTION);
    }

    /**
     * ���������� ��������� ����� ������, ���������� ������.
     */
    protected static String getSuffix(String action) {
    	if (action == null || action.equals("")) {
    		return "";
    	}
    	StringTokenizer tokenizer = new StringTokenizer(action, ".");
    	String result = "";
    	while (tokenizer.hasMoreTokens()) {
    		result = tokenizer.nextToken();
    	}
    	if (result.equals(action)) {
    		return "";
    	}
        return result;
    }
    
    protected DefaultDispatcher() {}

    /**
     * �������������� �� <code>nextPage</code>.
     */
    protected void redirect(HttpServletRequest request, HttpServletResponse response, String nextPage)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    protected String prepareErrorPage(HttpServletRequest request,
                                      String message, String nextUrl) {

        request.setAttribute(RequestConstants.AN_ERROR_MESSAGE, message);
        request.setAttribute(RequestConstants.AN_NEXT_URL, nextUrl);
        return PageNames.ERROR_PAGE;
    }
    
    protected abstract Class getCommandByActionSuffix(String suffix);

    protected void checkBackURL(HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	RequestHistory history = (RequestHistory) session.getAttribute(SessionConstants.AN_HISTORY);
    	if (history == null) {
    		return;
    	}
    	String backUrl = history.getBackUrl(request);
    	if (backUrl != null) {
    	    request.setAttribute(RequestConstants.AN_BACK_URL, backUrl);
    	}
    }
    
	/**
	 * @see HttpServlet#service(HttpServletRequest, HttpServletResponse)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = null;
        String action = getAction(request);
        CommandFactory factory = DefaultCommandFactory.getInstance();
        Class commandClass = getCommandByActionSuffix(getSuffix(action));
        Command command = factory.getCommand(commandClass);
        if (command == null) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,
        	                   "������� ������� �� ��������������� ������: action = "
                               + action + ". ���������� � ��������������.");
            return;
        }
        try {
        	nextPage = command.execute(request);
            checkBackURL(request);
        } catch (InvalidParameterException e) {
            request.setAttribute(AN_ERROR, e);
            nextPage = PageNames.PARAMETER_ERROR ;
        } catch (ContactsException e) {
            throw new ServletException(e);
        }
        redirect(request, response, nextPage);
	}
}
