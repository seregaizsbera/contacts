package su.sergey.contacts;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.admin.Roles;
import su.sergey.contacts.businessdelegate.impl.DefaultDAOBusinessDelegate;
import su.sergey.contacts.inquiry.Inquiry;
import su.sergey.contacts.inquiry.InquiryHome;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

/**
 * Этот сервлет реализует шаблон проектирвоания Front Controller,
 * т. е. все входящие запросы попадают сначала на него, затем они
 * переадресовываются на соответствующий "диспетчер".
 * Диспетчер выполняет определенные для конкретного запроса действия и
 * решает, какая jsp будет использована для ответа.
 *
 * @author Сергей Богданов
 */
public final class FrontController extends DefaultDispatcher implements SessionConstants {
    private static final String ACTION_MAIN_PREFIX = "main";
    private static final String ACTION_DIRECTORY_PREFIX = "directory";
    private static final String ACTION_SYSPROPS_PREFIX = "sysprops";
    private static final String ACTION_PERSON_PREFIX = "person";
    private static final String ACTION_QUERY_PREFIX = "query";
    private static final String ACTION_LOGOUT = "logout";

    /** Проверяет новая ли сессия, если да, то устанавливает в нее <code>DAOBusinessDelegate</code>. */
    protected static final void checkSessionBindings(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.isNew()  || (session.getAttribute(FRONT_CONTROLLER_INITIATED_SESSION) == null)) {
            session.setAttribute(DAO_BUSINESS_DELEGATE, new DefaultDAOBusinessDelegate());
            session.setAttribute(FRONT_CONTROLLER_INITIATED_SESSION, new Boolean(true));
            session.setAttribute(LISTENER, new SessionListener());
            session.setAttribute(AN_HISTORY, new RequestHistory());
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
    
    private void checkBackURL(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	RequestHistory history = (RequestHistory) session.getAttribute(AN_HISTORY);
    	String backUrl = history.getBackUrl(request);
    	session.removeAttribute(BACK_URL);
    	if (backUrl != null) {
    	    session.setAttribute(BACK_URL, backUrl);
    	}
    }
    
    /**
     * Обрабатывает запрос.
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkSessionBindings(request);
        checkBackURL(request);
        String nextPage = null;
        String action = getAction(request);
        int timeout = request.getSession().getMaxInactiveInterval();
        timeout += 120;
        response.setHeader("Refresh", timeout + "; url=" + request.getRequestURI());
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
        } else if (action.startsWith(ACTION_QUERY_PREFIX)) {
            nextPage = DispatcherNames.QUERY;
        } else {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,
        	                   "Попытка перейти по несуществующему адресу: action = "
                               + action + ". Обратитесь к администратору.");
            return;
        }
        redirect(request, response, nextPage);
    }
    
	/**
	 * @see DefaultDispatcher#getCommandByActionSuffix(String)
	 */
	protected Class getCommandByActionSuffix(String suffix) {
		return null;
	}
	
	/**
	 * @see GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();
		try {
			Context context = new InitialContext();
			Object object = context.lookup(JNDINamesForWeb.INQUIRY_REFERENCE);
			InquiryHome home = (InquiryHome) PortableRemoteObject.narrow(object, InquiryHome.class);
			Inquiry inquiry = home.create();
			Collection tableNames = TableNames.getNsiTableNames().keySet();
		    ServletContext servletContext = getServletContext();
			for (Iterator i = tableNames.iterator(); i.hasNext();) {
				String tableName = (String) i.next();
	    		InquiryObject objects[] = inquiry.inquireTable(tableName);
			    servletContext.setAttribute("su.sergey.contacts.inquiry." + tableName, objects);
			}
		} catch (NamingException e) {
			throw new ServletException(e);
		} catch (CreateException e) {
			throw new ServletException(e);
		} catch (RemoteException e) {
			throw new ServletException(e);
		}
	}
}
