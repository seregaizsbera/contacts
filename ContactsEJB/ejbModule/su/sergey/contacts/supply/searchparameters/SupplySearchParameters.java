package su.sergey.contacts.supply.searchparameters;

import java.io.Serializable;

public class SupplySearchParameters implements Serializable {
	private String name;
	private String parentName;
	private String shortName;
	private String url;
	private String inn;
	private String address;
	private String phone;
	private String email;
	private String note;
	private Integer kind;
	private boolean fullData;
	private boolean importantOnly;	
	
	public SupplySearchParameters() {
		fullData = false;
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
	 * Gets the fullData
	 * @return Returns a boolean
	 */
	public boolean isFullData() {
		return fullData;
	}
	
	/**
	 * Sets the fullData
	 * @param fullData The fullData to set
	 */
	public void setFullData(boolean fullData) {
		this.fullData = fullData;
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

	/**
	 * Gets the importantOnly
	 * @return Returns a boolean
	 */
	public boolean getImportantOnly() {
		return importantOnly;
	}
	
	/**
	 * Sets the importantOnly
	 * @param importantOnly The importantOnly to set
	 */
	public void setImportantOnly(boolean importantOnly) {
		this.importantOnly = importantOnly;
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
	
	public boolean needEmails() {
		boolean result = email != null;
		return result;
	}
	
	public boolean needPhones() {
		boolean result = phone != null;
		return result;
	}	
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
