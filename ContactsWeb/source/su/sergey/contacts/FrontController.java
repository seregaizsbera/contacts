package su.sergey.contacts;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import su.sergey.contacts.inquiry.Inquiry;
import su.sergey.contacts.inquiry.InquiryHome;
import su.sergey.contacts.inquiry.InquiryModes;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.sessionfacade.businessdelegate.impl.DefaultDAOBusinessDelegate;

/**
 * ���� ������� ��������� ������ �������������� Front Controller,
 * �. �. ��� �������� ������� �������� ������� �� ����, ����� ���
 * ������������������ �� ��������������� "���������".
 * ��������� ��������� ������������ ��� ����������� ������� �������� �
 * ������, ����� jsp ����� ������������ ��� ������.
 *
 * @author ������ ��������
 */
public final class FrontController extends DefaultDispatcher implements SessionConstants {
    private static final String ACTION_MAIN_PREFIX = "main";
    private static final String ACTION_DIRECTORY_PREFIX = "directory";
    private static final String ACTION_SYSPROPS_PREFIX = "sysprops";
    private static final String ACTION_PERSON_PREFIX = "person";
    private static final String ACTION_QUERY_PREFIX = "query";
    private static final String ACTION_LOGOUT = "logout";

    /** ��������� ����� �� ������, ���� ��, �� ������������� � ��� <code>DAOBusinessDelegate</code>. */
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
     * ������������ ������.
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
        response.setHeader("expires", "0");
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
        	                   "������� ������� �� ��������������� ������: action = "
                               + action + ". ���������� � ��������������.");
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
    	    		HashMap objects = inquiry.inquireTableAsHash(tableName);
    			    servletContext.setAttribute("inquire_" + tableName + "_" + InquiryModes.HASH, objects);
				}
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
