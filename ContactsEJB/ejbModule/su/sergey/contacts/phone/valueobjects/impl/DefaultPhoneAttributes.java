package su.sergey.contacts.phone.valueobjects.impl;

import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.phone.valueobjects.*;


public class DefaultPhoneAttributes implements PhoneAttributes {
    private String phone;
    private boolean basic;
    private Integer type;
    
	/**
	 * Gets the basic
	 * @return Returns a boolean
	 */
	public boolean isBasic() {
		return basic;
	}
	
	/**
	 * Sets the basic
	 * @param basic The basic to set
	 */
	public void setBasic(boolean basic) {
		this.basic = basic;
	}
	/**
	 * Gets the phone
	 * @return Returns a String
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone
	 * @param phone The phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the type
	 * @return Returns a Integer
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * Sets the type
	 * @param type The type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * @see PhoneAttributes#getNote()
	 */
	public String getNote() {
		return null;
	}
}
