import SavingsServer.Hello;
import SavingsServer.HelloHelper;
import org.omg.CosNaming.*;
import org.omg.CORBA.ORB;


public class HelloClient {
    public static void main(String args[]) {
        try {
      
            // Create and initialize the ORB
	    ORB orb = ORB.init(args, null);
	    
	    String initialReferences[] = orb.list_initial_services();
	    for (int i = 0; i < initialReferences.length; i++) {
	        System.out.println(initialReferences[i] + ": " + orb.resolve_initial_references(initialReferences[i]).getClass());
	    }

            System.out.println(orb.resolve_initial_references("SecurityCurrent"));
	    
            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);
      
            // Resolve the object reference in naming
            // make sure there are no spaces between ""
            NameComponent nc = new NameComponent("Hello", "");
            NameComponent path[] = {nc};
            Hello helloRef = HelloHelper.narrow(ncRef.resolve(path));
      
            // Call the Hello server object and print results
            String Hello = helloRef.sayHello();
            System.out.println(Hello);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
