package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.person.valueobjects.Related;

public class DefaultRelated implements Serializable, Related {
	private String relationship;
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

	/**
	 * Gets the relationship
	 * @return Returns a String
	 */
	public String getRelationship() {
		return relationship;
	}
	
	/**
	 * Sets the relationship
	 * @param relationship The relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}
