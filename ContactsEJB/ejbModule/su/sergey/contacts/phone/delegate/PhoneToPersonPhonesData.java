package su.sergey.contacts.phone.delegate;

import java.io.Serializable;

import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PersonPhonesCreateInfo;
import su.sergey.contacts.dto.PersonPhonesUpdateInfo;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;

public class PhoneToPersonPhonesData implements Serializable, PersonPhonesCreateInfo, PersonPhonesUpdateInfo {
	private PersonHandle personHandle;
	private PhoneHandle phoneHandle;
	private PhoneAttributes attributes;
	private Boolean basic;

	/**
	 * Constructor for PhoneToPersonPhonesData
	 */
	public PhoneToPersonPhonesData(PersonHandle personHandle, PhoneHandle phoneHandle, PhoneAttributes attributes) {
		this(personHandle, phoneHandle, attributes, attributes.isBasic());
	}
	
	/**
	 * Constructor for PhoneToPersonPhonesData
	 */
	public PhoneToPersonPhonesData(PersonHandle personHandle, PhoneHandle phoneHandle, PhoneAttributes attributes, boolean basic) {
		this.personHandle = personHandle;
		this.phoneHandle = phoneHandle;
		this.attributes = attributes;
		this.basic = new Boolean(basic);
	}

	/**
	 * @see PersonPhonesCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return personHandle.getId();
	}

	/**
	 * @see PersonPhonesCreateInfo#getPhone()
	 */
	public Integer getPhone() {
		return phoneHandle.getId();
	}

	/**
	 * @see PersonPhonesCreateInfo#getBasic()
	 */
	public Boolean getBasic() {
		return basic;
	}
}
