package su.sergey.contacts.properties.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyMaker;

public class DefaultPropertyMaker implements PropertyMaker {
	private String format;
	private String className;
	private Class propertyClass;
	private Constructor constructor;
	private RE regexp;

	/**
	 * Constructor for DefaultPropertyMaker
	 */
	protected DefaultPropertyMaker(String format, String className, boolean validate) {
		this.format = format;
		this.className = className;
		try {
			propertyClass = DefaultPropertyMaker.class.getClassLoader().loadClass(className);
			Class args[] = new Class[1];
			args[0] = String.class;
			constructor = propertyClass.getConstructor(args);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(className);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(className);
		}
		regexp = null;
		if (validate) {
			try {
				String fullFormat = RE.simplePatternToFullRegularExpression(format);
				regexp = new RE(fullFormat);
			} catch (RESyntaxException e) {
				throw new IllegalArgumentException(format);
			}
		}
	}
	
	public DefaultPropertyMaker(String format, String className) {
		this(format, className, true);
	}

	/**
	 * @see PropertyMaker#makePropertyValue(String)
	 */
	public Object makePropertyValue(String value) {
		if (value == null) {
			return null;
		}
		Object args[] = {value};
		Object result = null;
		try {
			result = constructor.newInstance(args);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @see PropertyMaker#getPropertyStringValue(Object)
	 */
	public String getPropertyStringValue(Object value) {
		if (value == null) {
			return null;
		}
		String result = value.toString();
		return result;
	}

	/**
	 * @see PropertyMaker#validateValue(String)
	 */
	public boolean validateValue(String value) {
		if (value == null || regexp == null) {
			return true;
		}
		boolean result = regexp.match(value);
		return result;
	}
	
	/**
	 * Gets the format
	 * @return Returns a String
	 */
	public String getFormat() {
		return format;
	}
	
	/**
	 * Gets the className
	 * @return Returns a String
	 */
	public String getClassName() {
		return className;
	}
}
