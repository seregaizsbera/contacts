package su.sergey.contacts.util.xml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.valueobjects.Currency;

public class ObjectToXmlConverter {
	private static final DateFormat timeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_TIME_FORMAT);
	private static final DateFormat dateFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATE_FORMAT);
	private static final DateFormat dateTimeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATETIME_FORMAT);
	
    public static Map getBeanProperties(Object elementValue) {
        Map result = new HashMap();
        try {
            Class elementValueClass = elementValue.getClass();
            BeanInfo descriptor = Introspector.getBeanInfo(elementValueClass);
            PropertyDescriptor properties[] = descriptor.getPropertyDescriptors();
            for (int i = 0; i < properties.length; i++) {
                try {
                    String name = properties[i].getName();
                    Method method = properties[i].getReadMethod();
                    if (name.equals("class") || method == null) {
                        continue;
                    }
                    Object value = method.invoke(elementValue, null);
                    result.put(name, value);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static XMLItem makeXMLRecord(String elementName, Object elementValue) {
        XMLItem result = new XMLItem(elementName);
        if (elementValue == null) {
            return result;
        }
        Class elementValueClass = elementValue.getClass();
        if (elementValue instanceof Collection) {
            addXMLRecordsFromCollection(result, elementName, (Collection) elementValue);
        } else if (elementValue instanceof Date) {
            String value;
            Date date = (Date) elementValue;
            if (elementName.indexOf("ime") >= 0) {
                value = timeFormat.format(date);
            } else if (elementName.indexOf("ate") >= 0) {
                value = dateFormat.format(date);
            } else {
                value = dateTimeFormat.format(date);
            }
            result.setValue(value);
        } else if (elementValueClass.isPrimitive() || elementValueClass.getName().startsWith("java") || elementValue instanceof Currency) {
            result.setValue(elementValue.toString());
        } else {
            Map properties = getBeanProperties(elementValue);
            for (Iterator i = properties.keySet().iterator(); i.hasNext();) {
                String key = i.next().toString();
                XMLItem item = makeXMLRecord(key, properties.get(key));
                result.addChild(item);
            }
        }
        return result;
    }

    public static void addXMLRecordsFromCollection(XMLItem result, String elementName, Collection collection) {
        String internalElementName = elementName + "_element";
        for (Iterator i = collection.iterator(); i.hasNext();) {
            Object o = i.next();
            XMLItem item = makeXMLRecord(internalElementName, o);
            result.addChild(item);
        }
    }
}
