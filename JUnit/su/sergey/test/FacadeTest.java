package su.sergey.test;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import junit.framework.TestCase;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;
import su.sergey.contacts.util.LoginFailedException;
import su.sergey.contacts.util.ServiceUtil;

public class FacadeTest extends TestCase {

	/**
	 * Constructor for FacadeTest
	 */
	public FacadeTest(String name) {
		super(name);
	}
	
	public void test1() throws LoginFailedException,
	                           NamingException,
	                           CreateException,
	                           RemoteException {
		ServiceUtil.login("sergey", "changeitxxx");
		Context context = new InitialContext();
		Object object = context.lookup("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome");
		DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
		DAOSessionFacade facade = home.create();
	}
}
