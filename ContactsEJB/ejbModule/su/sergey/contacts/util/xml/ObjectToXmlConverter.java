package su.sergey.contacts.util.xml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.valueobjects.Currency;

public class ObjectToXmlConverter {
	private final DateFormat timeFormat;
	private final DateFormat dateFormat;
	private final DateFormat dateTimeFormat;
	private final Map beanProperties;
	
	public ObjectToXmlConverter() {
		timeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_TIME_FORMAT);
		dateFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATE_FORMAT);
		dateTimeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATETIME_FORMAT);
		beanProperties = new HashMap();
	}
		
	private Collection getBeanProperties(Class theClass) {
		if (beanProperties.containsKey(theClass)) {
			return (Collection) beanProperties.get(theClass);
		}
		Collection result = new ArrayList();
		try {
			BeanInfo descriptor = Introspector.getBeanInfo(theClass);
			PropertyDescriptor properties[] = descriptor.getPropertyDescriptors();
			for (int i = 0; i < properties.length; i++) {
				String name = properties[i].getName();
				Method method = properties[i].getReadMethod();
				if (name.equals("class") || method == null) {
					continue;
				}
				result.add(new Object[] { name, method });
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		beanProperties.put(theClass, result);
		return result;
	}
							
	public XMLItem makeXMLRecord(String elementName, Object elementValue) {
		XMLItem result = new XMLItem(elementName);
		if (elementValue == null) {
			return result;
		}
		try {
			Class elementValueClass = elementValue.getClass();
			if (elementValue instanceof Collection) {
				addXMLRecordsFromCollection(result, elementName, (Collection) elementValue);
			} else if (elementValueClass.isArray()) {
				addXMLRecordsFromArray(result, elementName, elementValue);
			} else if (elementValue instanceof Date) {
				String value;
				Date date = (Date) elementValue;
				if (elementName.indexOf("Time") >= 0 || elementName.startsWith("time")) {
					value = timeFormat.format(date);
				} else if (elementName.indexOf("Date") >= 0 || elementName.startsWith("date")) {
					value = dateFormat.format(date);
				} else {
					value = dateTimeFormat.format(date);
				}
				result.setValue(value);
			} else if (elementValueClass.isPrimitive() || elementValueClass.getName().startsWith("java") || elementValue instanceof Currency) {
				result.setValue(elementValue.toString());
			} else {
				Collection properties = getBeanProperties(elementValueClass);
				for (Iterator i = properties.iterator(); i.hasNext();) {
					Object propertyPair[] = (Object[]) i.next();
					String name = (String) propertyPair[0];
					Method getter = (Method) propertyPair[1];
					Object propertyValue = getter.invoke(elementValue, null);
					XMLItem item = makeXMLRecord(name, propertyValue);
					result.addChild(item);
				}
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
				
    private void addXMLRecordsFromArray(XMLItem result, String elementName, Object array) {
        String internalElementName = elementName + "_element";
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object o = Array.get(array, i);
            XMLItem item = makeXMLRecord(internalElementName, o);
            result.addChild(item);
        }
    }
    
    public void addXMLRecordsFromCollection(XMLItem result, String elementName, Collection collection) {
        String internalElementName = elementName + "_element";
        for (Iterator i = collection.iterator(); i.hasNext();) {
            Object o = i.next();
            XMLItem item = makeXMLRecord(internalElementName, o);
            result.addChild(item);
        }
    }
}
