package su.sergey.contacts.phone.valueobjects.impl;

import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.phone.valueobjects.*;


public class DefaultPhone2 implements Phone2 {
	private PhoneHandle handle;
	private PhoneAttributes attributes;

	/**
	 * Gets the attributes
	 * @return Returns a PhoneAttributes
	 */
	public PhoneAttributes getAttributes() {
		return attributes;
	}
	
	/**
	 * Sets the attributes
	 * @param attributes The attributes to set
	 */
	public void setAttributes(PhoneAttributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the handle
	 * @return Returns a PhoneHandle
	 */
	public PhoneHandle getHandle() {
		return handle;
	}
	
	/**
	 * Sets the handle
	 * @param handle The handle to set
	 */
	public void setHandle(PhoneHandle handle) {
		this.handle = handle;
	}
}
