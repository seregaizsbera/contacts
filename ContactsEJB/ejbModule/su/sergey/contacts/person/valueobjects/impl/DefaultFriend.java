package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.person.valueobjects.Friend;

public class DefaultFriend implements Serializable, Friend {
	private String description;

	/**
	 * Gets the description
	 * @return Returns a String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
