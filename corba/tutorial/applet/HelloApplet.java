import SavingsServer.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.text.*;
import java.util.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.ORB;

public class HelloApplet extends Applet {
    public void init() {
        try {
            // Create and initialize the ORB 
            // The applet 'this' is passed to make parameters from the tag
            // available to initialize the ORB
            ORB orb = ORB.init(this, null);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            // Resolve the object reference in naming
            NameComponent nc = new NameComponent("Hello", "");
            NameComponent path[] = {nc};
            Hello helloRef = HelloHelper.narrow(ncRef.resolve(path));
      
            // Call the Hello server object and print the results
            message = helloRef.sayHello();    
        } catch(Exception e) {
            System.out.println("HelloApplet exception: " + e);
            e.printStackTrace(System.out);
        }  
    }
    String message = " ";

    public void paint(Graphics g) {
        g.drawString(message, 25, 50);
        Properties properties = System.getProperties();
        for (Iterator i = properties.keySet().iterator(); i.hasNext();) {
            String property = (String) i.next();
            System.out.println(MessageFormat.format("{0}={1}", new Object[]{property, properties.getProperty(property)}));
        }
    }
}
