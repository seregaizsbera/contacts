package su.sergey.contacts.email.valueobjects.impl;

import su.sergey.contacts.email.valueobjects.EmailAttributes;

public class DefaultEmailAttributes implements EmailAttributes {
	private String email;
	private boolean basic;
	
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
	 * Gets the email
	 * @return Returns a String
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
