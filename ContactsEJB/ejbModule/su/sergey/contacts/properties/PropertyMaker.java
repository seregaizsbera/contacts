package su.sergey.contacts.properties;

public interface PropertyMaker {
	boolean validateValue(String value);
	Object makePropertyValue(String value);
	String getPropertyStringValue(Object value);
}
