package su.sergey.contacts.phone.delegate;

import su.sergey.contacts.dto.SupplyPhonesData;
import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;


public class SupplyPhoneDataToPhone implements PhoneAttributes {
	private PhoneData data;
	private SupplyPhonesData supplyPhonesData;

	/**
	 * Constructor for PhoneDataToPhone
	 */
	public SupplyPhoneDataToPhone(PhoneData data, SupplyPhonesData supplyPhonesData) {
		this.data = data;
		this.supplyPhonesData = supplyPhonesData;
	}

	/**
	 * @see PhoneAttributes#getPhone()
	 */
	public String getPhone() {
		return data.getPhone();
	}

	/**
	 * @see PhoneAttributes#getType()
	 */
	public Integer getType() {
		return data.getType();
	}

	/**
	 * @see PhoneAttributes#isBasic()
	 */
	public boolean isBasic() {
		return false;
	}
	
	/**
	 * @see PhoneAttributes#getNote()
	 */
	public String getNote() {
		return data.getNote();
	}
}
