package su.sergey.contacts.sysprops;

import java.io.IOException;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.RequestConstants;
import su.sergey.contacts.exceptions.InvalidValueException;

/**
 * Диспетчер системных опций
 * 
 * @author Сергей Богданов
 */
public class SystemPropertyDispatcher extends DefaultDispatcher {

    public static final String PROPERTY_NAMES = "propertyNames";
    public static final String PROPERTY_NAME_PREFIX = "prop_name_"; //property name is a concatenation of PROPERTY_NAME_PREFIX and concrete property name

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {

        String nextPage = null;
        String action = getAction(request);
        nextPage = PageNames.SYSTEM_PROPERTIES;

        if (action.endsWith("updateProperty")) {
            String names = request.getParameter(PROPERTY_NAMES);
            //names are list of comma delimited property names
            System.err.println("propertyNames=" + names);
            if (names != null && names.length() > 0) {
                StringTokenizer st = new StringTokenizer(names, ",");
                while (st.hasMoreTokens()) {
                    String name = st.nextToken();
                    String propertyName = PROPERTY_NAME_PREFIX + name;
                    System.err.println("property name=" + propertyName);
                    String value = request.getParameter(propertyName);
                    System.err.println("property value=" + value);
                    //update property in DB
                    try {
                        getDAOBusinessDelegate(request).updateSystemProperty(name, value);
                    } catch (InvalidValueException e) {
                        //go to error_page.jsp in case of error
                        nextPage = prepareErrorPage(request,
                                e.getMessage(), "controller?action=sysprops");
                    }
                }
            }

        } else {
            //nothing special need to be done
        }
         //get all properties available
        Collection properties = getDAOBusinessDelegate(request).getSystemProperties();
        System.err.println("at SystemPropertyDispatcher properties.size()=" + properties.size() + " properties=" + properties);

        request.setAttribute(RequestConstants.AN_SYSPROPS, properties);
        redirect(request, response, nextPage);
    }


}
