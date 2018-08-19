package launcher;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import billing.FilialFacade;
import billing.FilialFacadeHelper;

public class Client {
	public static void main(String args[]) {
		int retval = 1;
		ORB orb = null;
		FilialFacade facade = null;
		try {
			Properties properties = new Properties();
			//properties.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
			//properties.setProperty("org.omg.CORBA.ORBInitialPort", "1050");
			orb = ORB.init(args, properties);
			System.err.println(orb);
			System.err.println(orb.getClass());
			NamingContext context = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent namingComponent = new NameComponent("billing/FilialFacade", "");
			NameComponent nameComponent[] = { namingComponent };
			facade = FilialFacadeHelper.narrow(context.resolve(nameComponent));
			while (true) {
                System.err.println(facade.doTestResult1());
                try {
                	Thread.sleep(275);
                } catch (InterruptedException e) {
                	e.printStackTrace();
                	break;
                }
			}
			retval = 0;
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (facade != null) {
				facade._release();
			}
			if (orb != null) {
				orb.shutdown(false);
			}
			System.exit(retval);
		}
	}
}