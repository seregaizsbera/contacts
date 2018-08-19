import java.io.*;
import java.math.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.rmi.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import su.sergey.contacts.sessionfacade.*;

class MyInteger extends idl.java.lang.Integer {
    MyInteger(int value) {
        this.value = value;
    }
}

class PersonHandle extends su.sergey.contacts.dto.PersonHandle {
    PersonHandle(int id) {
	this.id = new MyInteger(id);
    }
}

public class SessionFacadeTest {
    
    public static void main(String args[]) {
        try {
	    Properties properties = new Properties();
	    InputStream input = new FileInputStream(args.length == 0 ? "orb.properties" : args[0]);
	    properties.load(input);
	    input.close();
	    ORB orb = ORB.init(args, properties);
	    String nameServiceName = properties.getProperty("test.name.service", "NameService");
	    org.omg.CORBA.Object object = orb.resolve_initial_references(nameServiceName);
	    NamingContext context = NamingContextHelper.narrow(object);
	    System.err.println("1");
	    object = context.resolve(new NameComponent[] {
	                                 new NameComponent("ejb", ""),
	                                 new NameComponent("su", ""),
	                                 new NameComponent("sergey", ""),
	                                 new NameComponent("contacts", ""),
	                                 new NameComponent("sessionfacade", ""),
	                                 new NameComponent("DAOSessionFacadeHome", "")
	                             });
	    System.err.println("2");
	    DAOSessionFacadeHome home = DAOSessionFacadeHomeHelper.narrow(object);
	    System.err.println("3");
	    DAOSessionFacade facade = home.create();
	    System.err.println("4");
	    String queries[] = facade.lastQueries();
	    for (int i = 0; i < queries.length; i++) {
	        System.err.println(queries[i]);
	    }
	    System.err.println("5");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
