package su.sergey.contacts.phone.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.dto.SupplyPhonesCreateInfo;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

public class PhoneToSupplyPhonesData implements Serializable, SupplyPhonesCreateInfo {
	private SupplyHandle supplyHandle;
	private PhoneHandle phoneHandle;

	/**
	 * Constructor for PhoneToSupplyPhonesData
	 */
	public PhoneToSupplyPhonesData(SupplyHandle supplyHandle, PhoneHandle phoneHandle) {
		this.supplyHandle = supplyHandle;
		this.phoneHandle = phoneHandle;
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
}
