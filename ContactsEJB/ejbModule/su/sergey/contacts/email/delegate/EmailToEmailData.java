package su.sergey.contacts.email.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.EmailCreateInfo;
import su.sergey.contacts.dto.EmailUpdateInfo;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class EmailToEmailData implements EmailCreateInfo, EmailUpdateInfo, Serializable {
	private EmailAttributes attributes;

	/**
	 * Constructor for EmailToEmailData
	 */
	public EmailToEmailData(EmailAttributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * @see EmailCreateInfo#getEmail()
	 */
	public String getEmail() {
		return attributes.getEmail();
	}
}
