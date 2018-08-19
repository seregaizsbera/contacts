package su.sergey.contacts.phone.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

public class Phone2 implements Serializable {
	private PhoneHandle handle;
	private PhoneAttributes attributes;
	
	public Phone2(PhoneHandle handle, PhoneAttributes attributes) {
	    this.handle = handle;
	    this.attributes = attributes;
	}

	/**
	 * Gets the attributes
	 * @return Returns a PhoneAttributes
	 */
	public PhoneAttributes getAttributes() {
		return attributes;
	}
	
	/**
	 * Gets the handle
	 * @return Returns a PhoneHandle
	 */
	public PhoneHandle getHandle() {
		return handle;
	}
}
