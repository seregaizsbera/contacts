package su.sergey.test;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import junit.framework.TestCase;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;
import su.sergey.contacts.util.ServiceUtil;

public class SystemPropertyTest extends TestCase {
	private DAOSessionFacade facade;

	/**
	 * Constructor for SystemPropertyTest
	 */
	public SystemPropertyTest(String name) {
		super(name);
	}
	
	public void test() throws RemoteException, PropertyNotFoundException, InvalidPropertyValueException {
		String name = "last_birthdays_check";
		Object object = facade.getSystemPropertyValue(name);
		System.err.println(object.getClass() + ": " + object);
		facade.setSystemPropertyValue(name, "29.09.1982");
		object = facade.getSystemPropertyValue(name);
		System.err.println(object.getClass() + ": " + object);
		facade.setSystemPropertyValue(name, Calendar.getInstance().getTime());
		object = facade.getSystemPropertyValue(name);
		System.err.println(object.getClass() + ": " + object);
	}
	
	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		ServiceUtil.login("sergey", "changeitxxx");
		Properties properties = new Properties();
		properties.setProperty(Context.PROVIDER_URL, "iiop://localhost:2809");
		Context context = new InitialContext(properties);
		Object object = context.lookup("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome");
		DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
		facade = home.create();
	}
}
