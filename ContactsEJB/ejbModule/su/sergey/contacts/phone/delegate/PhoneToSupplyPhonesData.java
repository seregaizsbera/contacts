package su.sergey.contacts.phone.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.dto.SupplyPhonesCreateInfo;
import su.sergey.contacts.dto.SupplyPhonesUpdateInfo;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

public class PhoneToSupplyPhonesData implements Serializable, SupplyPhonesCreateInfo, SupplyPhonesUpdateInfo {
	private SupplyHandle supplyHandle;
	private PhoneHandle phoneHandle;
	private PhoneAttributes attributes;

	/**
	 * Constructor for PhoneToSupplyPhonesData
	 */
	public PhoneToSupplyPhonesData(SupplyHandle supplyHandle, PhoneHandle phoneHandle, PhoneAttributes attributes) {
		this.supplyHandle = supplyHandle;
		this.phoneHandle = phoneHandle;
		this.attributes = attributes;
	}

	/**
	 * @see SupplyPhonesCreateInfo#getSupply()
	 */
	public Integer getSupply() {
		return supplyHandle.getId();
	}

	/**
	 * @see SupplyPhonesCreateInfo#getPhone()
	 */
	public Integer getPhone() {
		return phoneHandle.getId();
	}

	/**
	 * @see SupplyPhonesCreateInfo#getNote()
	 */
	public String getNote() {
		return attributes.getNote();
	}
}
