package su.sergey.test;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import junit.framework.TestCase;
import org.omg.Security.InvalidCredentialType;
import org.omg.SecurityLevel2.Credentials;
import org.omg.SecurityLevel2.InvalidCredential;
import org.omg.SecurityLevel2.LoginFailed;
import su.sergey.LoginHelper;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;

public class FacadeTest extends TestCase {

	/**
	 * Constructor for FacadeTest
	 */
	public FacadeTest(String name) {
		super(name);
	}
	
	public void test1() throws LoginFailed,
	                           InvalidCredential,
	                           InvalidCredentialType,
	                           NamingException,
	                           CreateException,
	                           RemoteException {
		LoginHelper loginHelper = new LoginHelper();
		Credentials credentials = loginHelper.login("sergey", "changeitxxx");
		loginHelper.setInvocationCredentials(credentials);
		Context context = new InitialContext();
		Object object = context.lookup("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome");
		DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
		DAOSessionFacade facade = home.create();
	}
}
