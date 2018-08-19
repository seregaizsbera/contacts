package su.sergey.contacts.email.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class Email2 implements Serializable {
	private EmailHandle handle;
	private EmailAttributes attributes;
	
	public Email2(EmailHandle handle, EmailAttributes attributes) {
	    this.handle = handle;
	    this.attributes = attributes;
	}
	
	/**
	 * Gets the attributes
	 * @return Returns a EmailAttributes
	 */
	public EmailAttributes getAttributes() {
		return attributes;
	}
	
	/**
	 * Gets the handle
	 * @return Returns a EmailHandle
	 */
	public EmailHandle getHandle() {
		return handle;
	}	
}
