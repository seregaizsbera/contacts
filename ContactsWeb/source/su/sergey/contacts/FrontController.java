package su.sergey.contacts;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.admin.Roles;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.businessdelegate.impl.DefaultDAOBusinessDelegate;

/**
 * Этот сервлет реализует шаблон проектирвоания Front Controller,
 * т. е. все входящие запросы попадают сначала на него, затем они
 * переадресовываются на соответствующий "диспетчер".
 * Диспетчер выполняет определенные для конкретного запроса действия и
 * решает, какая jsp будет использована для ответа.
 *
 * @author Сергей Богданов
 */
public final class FrontController extends DefaultDispatcher {
    private static final String ACTION_MAIN_PREFIX = "main";
    private static final String ACTION_DIRECTORY_PREFIX = "directory";
    private static final String ACTION_SYSPROPS_PREFIX = "sysprops";
    private static final String ACTION_PERSON_PREFIX = "person";
    private static final String ACTION_LOGOUT = "logout";

    /** Проверяет новая ли сессия, если да, то устанавливает в нее <code>DAOBusinessDelegate</code>. */
    protected static final void checkSessionBindings(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.isNew()  || (session.getAttribute(SessionConstants.FRONT_CONTROLLER_INITIATED_SESSION) == null)) {
            session.setAttribute(SessionConstants.DAO_BUSINESS_DELEGATE, new DefaultDAOBusinessDelegate());
            session.setAttribute(SessionConstants.FRONT_CONTROLLER_INITIATED_SESSION, new Boolean(true));
            session.setAttribute(SessionConstants.LISTENER, new SessionListener());
            Roles roles = Roles.getInstance();
            Collection roleNames = Roles.getInstance().getRoleNames();
            for(Iterator i = roleNames.iterator(); i.hasNext();) {
                String role = (String)i.next();
                if(request.isUserInRole(role)) {
                    session.setAttribute(role, roles.getRoleDescription(role));
                }
            }
        }
    }
    
    /**
     * Обрабатывает запрос.
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkSessionBindings(request);
        String nextPage = null;
        String action = getAction(request);
        if (action == null) {
            nextPage = PageNames.MAIN;
        } else if (action.equals(ACTION_LOGOUT)) {
            request.getSession().invalidate();
            nextPage = PageNames.LOGOUT_PAGE;
        } else if (action.startsWith(ACTION_MAIN_PREFIX)) {
            nextPage = PageNames.MAIN;
        } else if (action.startsWith(ACTION_DIRECTORY_PREFIX)) {
            nextPage = DispatcherNames.DIRECTORY;
        } else if (action.startsWith(ACTION_SYSPROPS_PREFIX)) {
            nextPage = DispatcherNames.SYSPROPS;
        } else if (action.startsWith(ACTION_PERSON_PREFIX)) {
            nextPage = DispatcherNames.PERSON;
        } else {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,
        	                   "Попытка перейти по несуществующему адресу: action = "
                               + action + ". Обратитесь к администратору.");
            return;
        }
        redirect(request, response, nextPage);
    }
}
