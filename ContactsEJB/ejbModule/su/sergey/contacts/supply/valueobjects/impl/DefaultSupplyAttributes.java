package su.sergey.contacts.supply.valueobjects.impl;

import java.io.Serializable;
import java.util.Collection;

import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

public class DefaultSupplyAttributes implements Serializable, SupplyAttributes {
	private String name;
	private String parentName;
	private Integer kind;
	private String address;
	private String url;
	private String inn;
	private String note;
	private boolean important;
	private Collection emails;
	private Collection phones;
	private String shortName;

	/**
	 * Gets the address
	 * @return Returns a String
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address
	 * @param address The address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the url
	 * @return Returns a String
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Sets the url
	 * @param url The url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the phones
	 * @return Returns a Collection
	 */
	public Collection getPhones() {
		return phones;
	}

	/**
	 * Sets the phones
	 * @param phones The phones to set
	 */
	public void setPhones(Collection phones) {
		this.phones = phones;
	}

	/**
	 * Gets the parentName
	 * @return Returns a String
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * Sets the parentName
	 * @param parentName The parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * Gets the note
	 * @return Returns a String
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note
	 * @param note The note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets the name
	 * @return Returns a String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the kind
	 * @return Returns a Integer
	 */
	public Integer getKind() {
		return kind;
	}

	/**
	 * Sets the kind
	 * @param kind The kind to set
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}

	/**
	 * Gets the inn
	 * @return Returns a String
	 */
	public String getInn() {
		return inn;
	}

	/**
	 * Sets the inn
	 * @param inn The inn to set
	 */
	public void setInn(String inn) {
		this.inn = inn;
	}

	/**
	 * Gets the important
	 * @return Returns a boolean
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * Sets the important
	 * @param important The important to set
	 */
	public void setImportant(boolean important) {
		this.important = important;
	}

	/**
	 * Gets the emails
	 * @return Returns a Collection
	 */
	public Collection getEmails() {
		return emails;
	}

	/**
	 * Sets the emails
	 * @param emails The emails to set
	 */
	public void setEmails(Collection emails) {
		this.emails = emails;
	}
	
	/**
	 * Gets the shortName
	 * @return Returns a String
	 */
	public String getShortName() {
		return shortName;
	}
	
	/**
	 * Sets the shortName
	 * @param shortName The shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}