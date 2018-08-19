package launcher;

import impl.FilialFacadeImpl;
import java.util.Properties;

import javax.naming.Context;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import su.sergey.contacts.util.ServiceUtil;

import billing.FilialFacade;

public class Server {
	public static void main(String args[]) {
		int retval = 0;
		ORB orb = null;
		try {
			Properties properties = new Properties();
			//properties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
			//properties.setProperty("org.omg.CORBA.ORBInitialPort", "1050");
		    properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
		    properties.setProperty(Context.PROVIDER_URL, "iiop://localhost:2810");
			orb = ORB.init(args, properties);
			System.err.println(orb);
			System.err.println(orb.getClass());
			FilialFacade filialFacade = new FilialFacadeImpl(properties);
			orb.connect(filialFacade);
			NamingContext context = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent namingComponent = new NameComponent("billing/FilialFacade", "");
			NameComponent nameComponent[] = { namingComponent };
			context.rebind(nameComponent, filialFacade);
			ServiceUtil.login("appserveradmin", "appserveradmin1");
			orb.run();
		} catch (Throwable e) {
			retval = 1;
			e.printStackTrace();
		} finally {
			System.err.println("server: Finish");
			if (orb != null) {
				try {
					orb.shutdown(false);
				} catch (RuntimeException e) {
					retval = 1;
					e.printStackTrace();
				}
			}
			System.exit(retval);
		}
	}
}