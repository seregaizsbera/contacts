package su.sergey.contacts.properties.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.util.ContactsDateTimeFormat;


public class DatePropertyMaker extends DefaultPropertyMaker {
	private DateFormat format;

	/**
	 * Constructor for DatePropertyMaker
	 */
	public DatePropertyMaker(String format, String className) {
		super(format, className, false);
		this.format = new SimpleDateFormat(format);
	}
	
	/**
	 * @see PropertyMaker#validateValue(String)
	 */
	public boolean validateValue(String value) {
		if (value == null) {
			return true;
		}
		try {
			format.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * @see PropertyMaker#getPropertyStringValue(Object)
	 */
	public String getPropertyStringValue(Object value) {
		String result = format.format((Date) value);
		return result;
	}

	/**
	 * @see PropertyMaker#makePropertyValue(String)
	 */
	public Object makePropertyValue(String value) {
		Object result = null;
		try {
			result = format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
