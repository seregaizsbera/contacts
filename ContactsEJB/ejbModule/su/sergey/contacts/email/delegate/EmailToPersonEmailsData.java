package su.sergey.contacts.email.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PersonEmailsCreateInfo;
import su.sergey.contacts.dto.PersonEmailsUpdateInfo;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class EmailToPersonEmailsData implements PersonEmailsCreateInfo, PersonEmailsUpdateInfo, Serializable {
	private PersonHandle personHandle;
	private EmailHandle emailHandle;
	private EmailAttributes attributes;
	private Boolean basic;

	/**
	 * Constructor for EmailToPersonEmailsData
	 */
	public EmailToPersonEmailsData(PersonHandle personHandle, EmailHandle emailHandle, EmailAttributes attributes) {
		this(personHandle, emailHandle, attributes, attributes.isBasic());
	}

	public EmailToPersonEmailsData(PersonHandle personHandle, EmailHandle emailHandle, EmailAttributes attributes, boolean basic) {
		this.personHandle = personHandle;
		this.emailHandle = emailHandle;
		this.attributes = attributes;
		this.basic = new Boolean(basic);
	}

	/**
	 * @see PersonEmailsCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return personHandle.getId();
	}

	/**
	 * @see PersonEmailsCreateInfo#getEmail()
	 */
	public Integer getEmail() {
		return emailHandle.getId();
	}

	/**
	 * @see PersonEmailsCreateInfo#getBasic()
	 */
	public Boolean getBasic() {
		return basic;
	}
}
