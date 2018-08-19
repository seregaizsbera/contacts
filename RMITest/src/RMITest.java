import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;

public class RMITest {
	public static void main(String args[]) {
		int retval = 1;
		try {
			Properties properties = new Properties();
			// properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
			// properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.inprise.j2ee.jndi.CtxFactory");
			// properties.setProperty(Context.PROVIDER_URL, "iiop://localhost:900");
			// properties.setProperty(Context.URL_PKG_PREFIXES, "com.inprise.j2ee.jndi");
			properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			properties.setProperty(Context.PROVIDER_URL, "iiop://localhost:2811");
			Context context = new InitialContext(properties);
			Object object = context.lookup("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome1");
			System.err.println(object);
			DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
			System.err.println(home.getEJBMetaData().getRemoteInterfaceClass());
			// DAOSessionFacade DAOSessionFacade = home.create();
			// System.err.println(DAOSessionFacade.getSystemPropertyValue("report_folder"));
			context.close();
			retval = 0;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			System.exit(retval);
		}
	}
}