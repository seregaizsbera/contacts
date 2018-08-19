import java.io.*;
import java.util.*;

public class LoginContextTest {
    public static void main(String args[]) {
        try {
	    Properties properties = new Properties();
	    InputStream input = new FileInputStream(args[0]);
	    properties.load(input);
	    input.close();
	    ORB orb = ORB.init(properties);
	    org.omg.CORBA.Object object = orb.resolve_initial_reference("NameServiceServerRoot");
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
