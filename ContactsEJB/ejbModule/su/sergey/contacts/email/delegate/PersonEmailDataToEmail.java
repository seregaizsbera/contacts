package su.sergey.contacts.email.delegate;

import su.sergey.contacts.dto.EmailData;
import su.sergey.contacts.dto.PersonEmailsData;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class PersonEmailDataToEmail implements EmailAttributes {
	private EmailData emailData;
	private PersonEmailsData personEmailsData;

	/**
	 * Constructor for EmailDataToEmail
	 */
	public PersonEmailDataToEmail(EmailData emailData, PersonEmailsData personEmailsData) {
		this.emailData = emailData;
		this.personEmailsData = personEmailsData;
	}

	/**
	 * @see EmailAttributes#getEmail()
	 */
	public String getEmail() {
		return emailData.getEmail();
	}

	/**
	 * @see EmailAttributes#isBasic()
	 */
	public boolean isBasic() {
		return personEmailsData.getBasic().booleanValue();
	}
}
