package su.sergey.contacts;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.factory.CommandFactory;
import su.sergey.contacts.util.commands.factory.DefaultCommandFactory;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

/**
 * Базовый класс для всех диспетчеров, содержащий общую для них функциональность.
 *
 * @author: Сергей Богданов
 */
public abstract class DefaultDispatcher extends HttpServlet {
    protected static final String AN_ERROR = "error";

    /**
     * Возвращает <code>DAOBusinessDelegate</code>.
     */
    protected static DAOBusinessDelegate getDAOBusinessDelegate(HttpServletRequest request) {
        return (DAOBusinessDelegate) request.getSession().getAttribute(SessionConstants.DAO_BUSINESS_DELEGATE);
    }

    /**
     * Возвращает параметр запроса по которому определяются дальнейшие действия.
     */
    protected static String getAction(HttpServletRequest request) {
        return request.getParameter(RequestConstants.PN_ACTION);
    }

    /**
     * Возвращает последнюю часть строки, отделенную точкой.
     */
    protected static String getSuffix(String action) {
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
    
    /** Создает диспатчер. */
    protected DefaultDispatcher() {}

    /**
     * Перенаправляет на <code>nextPage</code>.
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
        	                   "Попытка перейти по несуществующему адресу: action = "
                               + action + ". Обратитесь к администратору.");
            return;
        }
        try {
        	nextPage = command.execute(request);
        } catch (InvalidParameterException e) {
            request.setAttribute(AN_ERROR, e);
            nextPage = PageNames.PARAMETER_ERROR ;
        } catch (ContactsException e) {
            throw new ServletException(e);
        }
        redirect(request, response, nextPage);
	}
}
