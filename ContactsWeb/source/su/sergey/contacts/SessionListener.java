package su.sergey.contacts;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;

public class SessionListener implements HttpSessionBindingListener {
	
	private DAOBusinessDelegate getDAOBusinessDelegate(HttpSessionBindingEvent e) {
		HttpSession session = e.getSession();
		return (DAOBusinessDelegate)session.getAttribute(SessionConstants.DAO_BUSINESS_DELEGATE);		
	}

    /**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent e) {
    	System.err.println("Session with id=" + e.getSession().getId() + " seems to have been created");
    }

    /**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent e) {
    	System.err.println("Session with id=" + e.getSession().getId() + " seems to have been destroyed");
    }
}
