package su.sergey.contacts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class SessionListener implements HttpSessionBindingListener {
	private DateFormat dateTimeFormat;
	
	public SessionListener() {
		dateTimeFormat = new SimpleDateFormat(ContactsDateTimeFormat.WEBSPHERE_DATE_TIME_FORMAT);
	}

    /**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent e) {
    	String message = "Session with id=" + e.getSession().getId() + " seems to have been created";
    	System.err.println(dateTimeFormat.format(Calendar.getInstance().getTime()) + " " + message);
    }

    /**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent e) {
    	String message = "Session with id=" + e.getSession().getId() + " seems to have been destroyed";
    	System.err.println(dateTimeFormat.format(Calendar.getInstance().getTime()) + " " + message);
    }
}
