package su.sergey.test;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import junit.framework.TestCase;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;
import su.sergey.contacts.util.ServiceUtil;

public class FacadeTest extends TestCase {
	private	Properties properties;

	/**
	 * Constructor for FacadeTest
	 */
	public FacadeTest(String name) {
		super(name);
	}
	
	public void test1() throws NamingException, CreateException, RemoteException {
		Context context = new InitialContext();
		Object object = context.lookup("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome");
		DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
		DAOSessionFacade facade = home.create();
	}
	
	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
		properties.load(input);
		ServiceUtil.login(properties.getProperty("appserver.user"), properties.getProperty("appserver.password"));
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		properties = null;
	}
}
