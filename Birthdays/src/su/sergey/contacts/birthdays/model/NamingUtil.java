package su.sergey.contacts.birthdays.model;

import java.util.ArrayList;
import java.util.Collection;

import org.omg.CosNaming.NameComponent;

public class NamingUtil {

    public static NameComponent[] toCorbaName(String jndiName) {
        String splitName[] = split(jndiName, '/');
	NameComponent result[] = new NameComponent[splitName.length];
	for (int i = 0; i < splitName.length; i++) {
	    result[i] = new NameComponent(splitName[i], "");
	}
	return result;
    }
    
    public static String[] split(String str, char separator) {
        if (str == null) {
	    return new String[0];
	}
        Collection result = new ArrayList();
	StringBuffer buf = new StringBuffer();
	int state = 0;
	char string[] = str.toCharArray();
	int pos = 0;
	String tmp;
	while (true) {
	    if (pos >= string.length) {
	        break;
	    }
	    char c = string[pos++];
	    switch (state) {
	        case 0:
		    if (c != separator) {
		        buf.append(c);
			state = 1;
		    }
		    break;
		case 1:
		    if (c == separator) {
		        tmp = buf.toString();
			if (!tmp.equals("")) {
                            result.add(tmp);
			}
			buf.setLength(0);
		    } else {
		        buf.append(c);
			if (pos >= string.length) {
			    tmp = buf.toString();
			    if (!tmp.equals("")) {
			        result.add(tmp);
			    }
			}
		    }
		    break;
	    }
	}
	return (String[]) result.toArray(new String[0]);
    }
}
