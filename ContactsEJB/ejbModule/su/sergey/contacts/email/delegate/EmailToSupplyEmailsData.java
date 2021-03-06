package su.sergey.contacts.email.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.SupplyEmailsCreateInfo;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class EmailToSupplyEmailsData implements SupplyEmailsCreateInfo, Serializable {
	private SupplyHandle supplyHandle;
	private EmailHandle emailHandle;

	/**
	 * Constructor for EmailToSupplyEmailsData
	 */
	public EmailToSupplyEmailsData(SupplyHandle supplyHandle, EmailHandle emailHandle) {
		this.supplyHandle = supplyHandle;
		this.emailHandle = emailHandle;
	}

	/**
	 * @see SupplyEmailsCreateInfo#getSupply()
	 */
	public Integer getSupply() {
		return supplyHandle.getId();
	}

	/**
	 * @see SupplyEmailsCreateInfo#getEmail()
	 */
	public Integer getEmail() {
		return emailHandle.getId();
	}
}
