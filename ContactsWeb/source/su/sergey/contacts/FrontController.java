package su.sergey.contacts;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import su.sergey.contacts.admin.Roles;
import su.sergey.contacts.inquiry.businessdelegate.InquiryBusinessDelegate;
import su.sergey.contacts.inquiry.businessdelegate.impl.DefaultInquiryBusinessDelegate;
import su.sergey.contacts.sessionfacade.businessdelegate.impl.DefaultDAOBusinessDelegate;
import su.sergey.contacts.util.ProductInfo;

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
    private static final String ACTION_CALL_PREFIX = "call";
    private static final String ACTION_DIRECTORY_PREFIX = "directory";
    private static final String ACTION_PERSON_PREFIX = "person";
    private static final String ACTION_QUERY_PREFIX = "query";
    private static final String ACTION_SUPPLY_PREFIX = "supply";
    private static final String ACTION_REPORT_PREFIX = "report";
    private static final String ACTION_LOGOUT = "logout";
    private InquiryBusinessDelegate inquiry;

    /** Проверяет новая ли сессия, если да, то устанавливает в нее <code>DAOBusinessDelegate</code>. */
    protected void checkSessionBindings(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.isNew() || (session.getAttribute(FRONT_CONTROLLER_INITIATED_SESSION) == null)) {
            session.setAttribute(DAO_BUSINESS_DELEGATE, new DefaultDAOBusinessDelegate(JNDINames.DAO_SESSION_FACADE_REFERENCE));
            session.setAttribute(INQUIRY_BUSINESS_DELEGATE, inquiry);
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
            saveInquiryData(session);
        }
    }
    
    /**
     * Обрабатывает запрос.
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkSessionBindings(request);
        saveInquiryData(request);
        String nextPage = null;
        String action = getAction(request);
        int timeout = request.getSession().getMaxInactiveInterval();
        timeout += 120;
	String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        response.setHeader("Refresh", timeout + "; url=" + request.getRequestURI() + queryString);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        if (action == null) {
        	super.service(request, response);
        	return;
        } else if (action.equals(ACTION_LOGOUT)) {
            request.getSession().invalidate();
        	if (getServletContext().getServerInfo().indexOf("WebSphere") >= 0) {
                nextPage = PageNames.IBM_LOGOUT_PAGE;
        	} else {
                nextPage = PageNames.WELCOME_PAGE;
        	}
        } else if (action.startsWith(ACTION_MAIN_PREFIX)) {
        	super.service(request, response);
        	return;
        } else if (action.startsWith(ACTION_CALL_PREFIX)) {
            nextPage = DispatcherNames.CALL;
        } else if (action.startsWith(ACTION_DIRECTORY_PREFIX)) {
            nextPage = DispatcherNames.DIRECTORY;
        } else if (action.startsWith(ACTION_PERSON_PREFIX)) {
            nextPage = DispatcherNames.PERSON;
        } else if (action.startsWith(ACTION_QUERY_PREFIX)) {
            nextPage = DispatcherNames.QUERY;
        } else if (action.startsWith(ACTION_SUPPLY_PREFIX)) {
        	nextPage = DispatcherNames.SUPPLY;
        } else if (action.startsWith(ACTION_REPORT_PREFIX)) {
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
		return HomeCommand.class;
	}
	
	/**
	 * @see GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();		
	    ServletContext servletContext = getServletContext();        
        inquiry = new DefaultInquiryBusinessDelegate(JNDINames.INQUIRY_REFERENCE);
        saveInquiryData(servletContext);
		servletContext.setAttribute("currentDatabase", inquiry.getCurrentDatabase());
		InputStream input = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
		if (input != null) {
    		try {
        		Manifest manifest = new Manifest(input);
        		ProductInfo productInfo = new ProductInfo(manifest);
        		servletContext.setAttribute("productInfo", productInfo);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
	}
	
	private void saveInquiryData(ServletContext servletContext) {
		String inquiryAliases[] = inquiry.inquireTableAliases(PageContext.APPLICATION_SCOPE);
		for (int i = 0; i < inquiryAliases.length; i++) {
			String alias = inquiryAliases[i];
		    servletContext.setAttribute(alias, inquiry.inquireTable(alias));
		}
	}
	
	private void saveInquiryData(HttpSession session) {
		String inquiryAliases[] = inquiry.inquireTableAliases(PageContext.SESSION_SCOPE);
		for (int i = 0; i < inquiryAliases.length; i++) {
			String alias = inquiryAliases[i];
		    session.setAttribute(alias, inquiry.inquireTable(alias));
		}
	}
	
	private void saveInquiryData(HttpServletRequest request) {
		String inquiryAliases[] = inquiry.inquireTableAliases(PageContext.REQUEST_SCOPE);
		for (int i = 0; i < inquiryAliases.length; i++) {
			String alias = inquiryAliases[i];
		    request.setAttribute(alias, inquiry.inquireTable(alias));
		}
	}	
}
