package su.sergey.contacts.email.valueobjects.impl;

import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class DefaultEmail2 implements Email2 {
	private EmailHandle handle;
	private EmailAttributes attributes;
	
	/**
	 * Gets the attributes
	 * @return Returns a EmailAttributes
	 */
	public EmailAttributes getAttributes() {
		return attributes;
	}
	
	/**
	 * Sets the attributes
	 * @param attributes The attributes to set
	 */
	public void setAttributes(EmailAttributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the handle
	 * @return Returns a EmailHandle
	 */
	public EmailHandle getHandle() {
		return handle;
	}
	
	/**
	 * Sets the handle
	 * @param handle The handle to set
	 */
	public void setHandle(EmailHandle handle) {
		this.handle = handle;
	}
}
