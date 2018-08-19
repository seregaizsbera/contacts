import SavingsServer.HelloImpl;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.ORB;

public class HelloServer {
    public static void main(String args[]) {
        try {

            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // Create the servant and register it with the ORB
            HelloImpl helloRef = new HelloImpl();
	    orb.connect(helloRef);
      
            // Get the root naming context
	    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	    NamingContext ncRef = NamingContextHelper.narrow(objRef);
      
            // Bind the object reference in naming
            // Make sure there are no spaces between ""
            NameComponent nc = new NameComponent("Hello", "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, helloRef);
	    
	    System.out.println("Savings Server has been started.");
      
            // Wait for invocations from clients
            Object sync = new java.lang.Object();
            synchronized (sync) {
                sync.wait();
            }      
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
}
