package su.sergey.contacts;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;

/**
 * Базовый класс для всех диспетчеров, содержащий общую для них функциональность.
 *
 * @author: Сергей Богданов
 */
public class DefaultDispatcher extends HttpServlet {

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

}
