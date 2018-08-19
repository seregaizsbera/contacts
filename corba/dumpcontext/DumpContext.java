import java.io.*;
import java.math.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.rmi.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class DumpContext {

    private static void list(Map visitedContexts, NamingContext context, int indent, String path, PrintStream output) throws NotFound, CannotProceed, InvalidName {
        visitedContexts.put(path, context);
        StringBuffer indentStr = new StringBuffer();
        for (int i = 0; i < indent; i++) {
	    indentStr.append("  ");
	}
        BindingListHolder bindingListHolder = new BindingListHolder();
        BindingIteratorHolder bindingIteratorHolder = new BindingIteratorHolder();
        context.list(1024, bindingListHolder, bindingIteratorHolder);
        Binding bindings[] = bindingListHolder.value;
        for (int i = 0; i < bindings.length; i++) {
	    String name = bindings[i].binding_name[0].id;
            output.print(MessageFormat.format("{1}{0}", new Object[]{name, indentStr}));
            org.omg.CORBA.Object object = context.resolve(bindings[i].binding_name);
	    if (bindings[i].binding_type == BindingType.ncontext) {
	        NamingContext newContext = NamingContextHelper.narrow(object);
	        boolean revisited = false;
	        for (Iterator j = visitedContexts.keySet().iterator(); j.hasNext();) {
	            String key = (String) j.next();
	            NamingContext oldContext = (NamingContext) visitedContexts.get(key);
		    if (newContext._is_equivalent(oldContext)) {
		        output.print(" -> " + key);
		        revisited = true;
			break;
		    }
		}
		output.println();
		if (!revisited) {
                    list(visitedContexts, newContext, indent + 1, path + "/" + name, output);
		}
	    } else {
	        output.println();
	    }
	}
    }
	
    private static void list(NamingContext context, String topName, PrintStream output) throws NotFound, CannotProceed, InvalidName {
        Map visitedContexts = new HashMap();
        output.println(topName);
        list(visitedContexts, context, 1, topName, output);
    }
    
    private static String toString(NameComponent names[]) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < names.length; i++) {
	    if (i > 0) {
	        buffer.append('/');
	    }
	    buffer.append(names[i].id);
	}
	String result = buffer.toString();
	return result;
    }
    
    public static void main(String args[]) {
        try {
	    Properties properties = new Properties();
	    InputStream input = new FileInputStream(args.length == 0 ? "orb.properties" : args[0]);
	    properties.load(input);
	    input.close();
	    ORB orb = ORB.init(args, properties);
	    String refs[] = orb.list_initial_services();
	    for (int i = 0; i < refs.length; i++) {
	        System.out.println(refs[i]);
	    }
	    String nameServiceName = properties.getProperty("test.name.service", "NameService");
	    org.omg.CORBA.Object object = orb.resolve_initial_references(nameServiceName);
	    NamingContext context = NamingContextHelper.narrow(object);
	    list(context, nameServiceName, System.out);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
