package su.sergey.contacts.phone.delegate;

import su.sergey.contacts.dto.PersonPhonesData;
import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.phone.valueobjects.*;


public class PhoneDataToPhone implements PhoneAttributes {
	private PhoneData data;
	private PersonPhonesData personPhonesData;

	/**
	 * Constructor for PhoneDataToPhone
	 */
	public PhoneDataToPhone(PhoneData data, PersonPhonesData personPhonesData) {
		this.data = data;
		this.personPhonesData = personPhonesData;
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
		return personPhonesData.getBasic().booleanValue();
	}
}
