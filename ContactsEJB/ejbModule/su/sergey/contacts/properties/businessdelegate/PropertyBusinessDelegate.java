package su.sergey.contacts.properties.businessdelegate;

import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyNotFoundException;

public interface PropertyBusinessDelegate {
	Object getValue(String name) throws PropertyNotFoundException;
	
    void setValue(String name, Object value) throws PropertyNotFoundException;
    
    void setValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException;
}
