package su.sergey.contacts;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.admin.Roles;
import su.sergey.contacts.inquiry.InquiryModes;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.inquiry.businessdelegate.InquiryBusinessDelegate;
import su.sergey.contacts.inquiry.businessdelegate.impl.DefaultInquiryBusinessDelegate;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.sessionfacade.businessdelegate.impl.DefaultDAOBusinessDelegate;

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
    private static final String ACTION_PERSON_PREFIX = "person";
    private static final String ACTION_QUERY_PREFIX = "query";
    private static final String ACTION_SUPPLY_PREFIX = "supply";
    private static final String ACTION_REPORT_SUFFIX = "report";
    private static final String ACTION_LOGOUT = "logout";

    /** Проверяет новая ли сессия, если да, то устанавливает в нее <code>DAOBusinessDelegate</code>. */
    protected static final void checkSessionBindings(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.isNew()  || (session.getAttribute(FRONT_CONTROLLER_INITIATED_SESSION) == null)) {
            session.setAttribute(DAO_BUSINESS_DELEGATE, new DefaultDAOBusinessDelegate(JNDINamesForWeb.SESSION_FACADE_REFERENCE));
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
            InquiryBusinessDelegate inquiry = new DefaultInquiryBusinessDelegate(JNDINamesForWeb.INQUIRY_REFERENCE);
			session.setAttribute(AN_SUPPLY_KINDS_BY_NAMES, inquiry.inquireTableAsNames(TableNames.SUPPLY_KINDS));
			session.setAttribute(AN_SUPPLY_KINDS_HASH, inquiry.inquireTableAsHash(TableNames.SUPPLY_KINDS));
        }
    }
    
    private void checkBackURL(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	RequestHistory history = (RequestHistory) session.getAttribute(AN_HISTORY);
    	if (history == null) {
    		return;
    	}
    	String backUrl = history.getBackUrl(request);
    	if (backUrl != null) {
    	    request.setAttribute(RequestConstants.AN_BACK_URL, backUrl);
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
        checkBackURL(request);
        int timeout = request.getSession().getMaxInactiveInterval();
        timeout += 120;
        response.setHeader("Refresh", timeout + "; url=" + request.getRequestURI());
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        if (action == null) {
            nextPage = PageNames.MAIN;
        } else if (action.equals(ACTION_LOGOUT)) {
            request.getSession().invalidate();
            String prop = System.getProperty("com.sun.enterprise.appname");
            if (prop != null && prop.equals("j2ee")) {
                nextPage = PageNames.J2EE_LOGOUT_PAGE;
            } else {
                nextPage = PageNames.LOGOUT_PAGE;
            }
        } else if (action.startsWith(ACTION_MAIN_PREFIX)) {
            nextPage = PageNames.MAIN;
        } else if (action.startsWith(ACTION_DIRECTORY_PREFIX)) {
            nextPage = DispatcherNames.DIRECTORY;
        } else if (action.startsWith(ACTION_PERSON_PREFIX)) {
            nextPage = DispatcherNames.PERSON;
        } else if (action.startsWith(ACTION_QUERY_PREFIX)) {
            nextPage = DispatcherNames.QUERY;
        } else if (action.startsWith(ACTION_SUPPLY_PREFIX)) {
        	nextPage = DispatcherNames.SUPPLY;
        } else if (action.startsWith(ACTION_REPORT_SUFFIX)) {
        	nextPage = DispatcherNames.REPORT;
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
		// System.getProperties().list(System.err);
        InquiryBusinessDelegate inquiry = new DefaultInquiryBusinessDelegate(JNDINamesForWeb.INQUIRY_REFERENCE);
		Map nsiTables = TableNames.getNsiTableNames();
		Collection tableNames = nsiTables.keySet();
	    ServletContext servletContext = getServletContext();
		for (Iterator i = tableNames.iterator(); i.hasNext();) {
			String tableName = (String) i.next();
			int mode = ((Integer) nsiTables.get(tableName)).intValue();
			if ((mode & InquiryModes.ID_SORTED) != 0) {
	    		InquiryObject objects[] = inquiry.inquireTableAsIds(tableName);
			    servletContext.setAttribute("inquire_" + tableName + "_" + InquiryModes.ID_SORTED, objects);
			}
			if ((mode & InquiryModes.NAME_SORTED) != 0) {
	    		InquiryObject objects[] = inquiry.inquireTableAsNames(tableName);
			    servletContext.setAttribute("inquire_" + tableName + "_" + InquiryModes.NAME_SORTED, objects);
			}
			if ((mode & InquiryModes.HASH) != 0) {
	    		Map objects = inquiry.inquireTableAsHash(tableName);
			    servletContext.setAttribute("inquire_" + tableName + "_" + InquiryModes.HASH, objects);
			}
		}
	}
}
