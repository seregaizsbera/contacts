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

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ObjectToXmlConverter {
	private final Attributes attributes;
	private final DateFormat timeFormat;
	private final DateFormat dateFormat;
	private final DateFormat dateTimeFormat;
	private final Map beanProperties;
	private final boolean skipNulls;
	
	public ObjectToXmlConverter(boolean skipNulls) {
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		beanProperties = new HashMap();
		attributes = new  AttributesImpl();
		this.skipNulls = skipNulls;
	}
		
	public ObjectToXmlConverter() {
		this(false);
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
							
	public void makeXMLDocument(ContentHandler output, String rootElement, Object object) throws SAXException {
		output.startDocument();
		if (object != null) {
		    makeXMLRecord(output, rootElement, object);
		} else {
			output.startElement("", "", rootElement, attributes);
			output.endElement("", "", rootElement);
		}
		output.endDocument();
	}
	
	public void makeXMLRecord(ContentHandler output, String elementName, Object elementValue) throws SAXException {
		if (skipNulls && elementValue == null) {
			return;
		}
		output.startElement("", "", elementName, attributes);
		try {
		    if (elementValue == null) {
			    return;
		    }
			Class elementValueClass = elementValue.getClass();
			if (elementValue instanceof Collection) {
				addXMLRecordsFromCollection(output, elementName, (Collection) elementValue);
			} else if (elementValueClass.isArray()) {
				addXMLRecordsFromArray(output, elementName, elementValue);
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
				char chars[] = value.toCharArray();
				output.characters(chars, 0, chars.length);
			} else if (elementValueClass.isPrimitive() || elementValueClass.getName().startsWith("java")) {
                String strValue = elementValue.toString();
                if (strValue != null) {
				    char chars[] = strValue.toCharArray();
				    output.characters(chars, 0, chars.length);
                }
			} else {
				Collection properties = getBeanProperties(elementValueClass);
				for (Iterator i = properties.iterator(); i.hasNext();) {
					Object propertyPair[] = (Object[]) i.next();
					String name = (String) propertyPair[0];
					Method getter = (Method) propertyPair[1];
					Object propertyValue = getter.invoke(elementValue, null);
					makeXMLRecord(output, name, propertyValue);
				}
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			output.endElement("", "", elementName);
		}
	}
				
    private void addXMLRecordsFromArray(ContentHandler output, String elementName, Object array) throws SAXException {
        String internalElementName = elementName + "_element";
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object o = Array.get(array, i);
            makeXMLRecord(output, internalElementName, o);
        }
    }
    
    public void addXMLRecordsFromCollection(ContentHandler output, String elementName, Collection collection) throws SAXException {
        String internalElementName = elementName + "_element";
        for (Iterator i = collection.iterator(); i.hasNext();) {
            Object o = i.next();
            makeXMLRecord(output, internalElementName, o);
        }
    }
}
