package su.sergey.contacts.phone.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.PhoneCreateInfo;
import su.sergey.contacts.dto.PhoneUpdateInfo;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.phone.valueobjects.*;


public class PhoneToPhoneData implements Serializable, PhoneCreateInfo, PhoneUpdateInfo {
	private PhoneAttributes attributes;

	/**
	 * Constructor for PhoneToPhoneData
	 */
	public PhoneToPhoneData(PhoneAttributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * @see PhoneCreateInfo#getPhone()
	 */
	public String getPhone() {
		return attributes.getPhone();
	}

	/**
	 * @see PhoneCreateInfo#getType()
	 */
	public Integer getType() {
		return attributes.getType();
	}

	/**
	 * @see PhoneCreateInfo#getBasic()
	 */
	public Boolean getBasic() {
		return new Boolean(attributes.isBasic());
	}
	
	/**
	 * @see PhoneCreateInfo#getNote()
	 */
	public String getNote() {
		return attributes.getNote();
	}
}
