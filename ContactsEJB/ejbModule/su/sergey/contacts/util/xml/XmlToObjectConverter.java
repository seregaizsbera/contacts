package su.sergey.contacts.util.xml;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class XmlToObjectConverter {
	private final DateFormat timeFormat;
	private final DateFormat dateFormat;
	private final DateFormat dateTimeFormat;
	private final Map beanProperties;

	public XmlToObjectConverter() {
        timeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_TIME_FORMAT);
        dateFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATE_FORMAT);
        dateTimeFormat = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATETIME_FORMAT);
        beanProperties = new HashMap();
	}

	private static String getText(Element element) {
		NodeList childs = element.getChildNodes();
		if (childs.getLength() == 0) {
			return null;
		}
		Text text = (Text) element.getChildNodes().item(0);
		String result = text.getData();
		return result;
	}

	private static Object makePrimitiveObject(Class theClass, Element element) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object array = Array.newInstance(theClass, 1);
		Class wrapperClass = Array.get(array, 0).getClass();
		Object result = makeBaseObject(wrapperClass, element);
		return result;
	}
	
	private static Object makeBaseObject(Class theClass, Element element) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		String value = getText(element);
		if (value == null) {
			return null;
		}
		Constructor constructor = theClass.getConstructor(new Class[] { String.class });
		Object result = constructor.newInstance(new Object[] { value });
		return result;
	}

	private Date makeDate(Element element) throws ParseException {
		String name = element.getTagName();
		String value = getText(element);
		Date result = null;
		if (value != null) {
			if (name.indexOf("Time") >= 0 || name.startsWith("time")) {
				result = makeTime(value);
			} else if (name.indexOf("Date") >= 0 || name.startsWith("date")) {
				result = makeDate(value);
			} else {
				result = makeTimeStamp(value);
			}
		}
		return result;
	}

	private Date makeDate(String value) throws ParseException {
		Date result = dateFormat.parse(value);
		return result;
	}

	private Date makeTime(String value) throws ParseException {
		Date result = timeFormat.parse(value);
		return result;
	}

	private Date makeTimeStamp(String value) throws ParseException {
		Date result = dateTimeFormat.parse(value);
		return result;
	}
	
	private Object makeArray(Class componentClass, Element element) throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
		String name = element.getTagName();
		NodeList childs = element.getChildNodes();
		if (childs.getLength() == 0) {
			return null;
		}
		NodeList elements = element.getElementsByTagName(name + "_element");
		int numberOfElements = elements.getLength();
		Object result = Array.newInstance(componentClass, numberOfElements);
		for (int i = 0; i < numberOfElements; i++) {
			Object value = makeObject(componentClass, (Element) elements.item(i));
            Array.set(result, i, value);
		}
		return result;
	}
	
	private Object makeComplexObject(Class theClass, Element element) throws InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
		Object result = theClass.newInstance();
		Map propertiesData = getPropertiesData(theClass);
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (!(child instanceof Element)) {
				continue;
			}
		    Element childElement = (Element) child;
		    String name = childElement.getTagName();
		    if (!propertiesData.containsKey(name)) {
		    	continue;
		    }
		    Object propertyData[] = (Object[]) propertiesData.get(name);
		    Method writeMethod = (Method) propertyData[0];
		    Object value = makeObject((Class) propertyData[1], childElement);
		    writeMethod.invoke(result, new Object[] { value });
		}
		return result;
	}

	private Map getPropertiesData(Class theClass) throws IntrospectionException {
		if (beanProperties.containsKey(theClass)) {
			return (Map) beanProperties.get(theClass);
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(theClass);
		PropertyDescriptor properties[] = beanInfo.getPropertyDescriptors();
		Map result = new HashMap();
		for (int i = 0; i < properties.length; i++) {
			String name = properties[i].getName();
			if (name.equals("class")) {
				continue;
			}
			Method writeMethod = properties[i].getWriteMethod();
			if (writeMethod == null) {
				continue;
			}
			result.put(name, new Object[] { writeMethod, properties[i].getPropertyType() });
		}
		beanProperties.put(theClass, result);
		return result;
	}

	private Object makeObject(Class theClass, Element element) {
		Object result = null;
		try {
			String className = theClass.getName();
			if (theClass.isPrimitive()) {
				result = makePrimitiveObject(theClass, element);
    		} else if (className.startsWith("java.lang.")) {
				result = makeBaseObject(theClass, element);
			} else if (theClass.equals(Date.class)) {
				result = makeDate(element);
			} else if (theClass.isArray()) {
				result = makeArray(theClass.getComponentType(), element);
			} else {
				result = makeComplexObject(theClass, element);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        return result;
	}
	
	public Object makeObject(Class theClass, String xml) {
		Object result = null;
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			InputSource input = new InputSource(new StringReader(xml));
			Document document = builder.parse(input);
			Element root = document.getDocumentElement();
			result = makeObject(theClass, root);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
