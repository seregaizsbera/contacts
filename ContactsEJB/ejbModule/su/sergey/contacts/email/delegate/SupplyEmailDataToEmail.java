package su.sergey.contacts.email.delegate;

import su.sergey.contacts.dto.EmailData;
import su.sergey.contacts.dto.SupplyEmailsData;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class SupplyEmailDataToEmail implements EmailAttributes {
	private EmailData emailData;
	private SupplyEmailsData supplyEmailsData;

	/**
	 * Constructor for EmailDataToEmail
	 */
	public SupplyEmailDataToEmail(EmailData emailData, SupplyEmailsData supplyEmailsData) {
		this.emailData = emailData;
		this.supplyEmailsData = supplyEmailsData;
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
		return false;
	}
}
