package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.person.valueobjects.Coworker;
import su.sergey.contacts.person.valueobjects.Friend;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.Related;
import su.sergey.contacts.person.valueobjects.Shnip;

public class DefaultPersonAttributes implements Serializable, PersonAttributes {
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String note;
	private Date birthday;
	private Collection emails;
	private Collection phones;
	private String icq;
	private Friend friendInfo;
	private Related relatedInfo;
	private Shnip shnipInfo;
	private Msu msuInfo;
	private Coworker coworkerInfo;

	/**
	 * Constructor for DefaultPerson
	 */
	public DefaultPersonAttributes() {}
	
	public DefaultPersonAttributes(PersonAttributes person) {
        this.address = person.getAddress();
        this.birthday = person.getBirthday();
        this.coworkerInfo = person.getCoworkerInfo();
        this.emails = person.getEmails();
        this.firstName = person.getFirstName();
        this.friendInfo = person.getFriendInfo();
        this.icq = person.getIcq();
        this.lastName = person.getLastName();
        this.middleName = person.getMiddleName();
        this.msuInfo = person.getMsuInfo();
        this.phones = person.getPhones();
        this.relatedInfo = person.getRelatedInfo();
        this.shnipInfo = person.getShnipInfo();
	}

	/**
	 * Gets the shnipInfo
	 * @return Returns a Shnip
	 */
	public Shnip getShnipInfo() {
		return shnipInfo;
	}
	
	/**
	 * Sets the shnipInfo
	 * @param shnipInfo The shnipInfo to set
	 */
	public void setShnipInfo(Shnip shnipInfo) {
		this.shnipInfo = shnipInfo;
	}

	/**
	 * Gets the relatedInfo
	 * @return Returns a Related
	 */
	public Related getRelatedInfo() {
		return relatedInfo;
	}
	
	/**
	 * Sets the relatedInfo
	 * @param relatedInfo The relatedInfo to set
	 */
	public void setRelatedInfo(Related relatedInfo) {
		this.relatedInfo = relatedInfo;
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
	 * Gets the msuInfo
	 * @return Returns a Msu
	 */
	public Msu getMsuInfo() {
		return msuInfo;
	}
	
	/**
	 * Sets the msuInfo
	 * @param msuInfo The msuInfo to set
	 */
	public void setMsuInfo(Msu msuInfo) {
		this.msuInfo = msuInfo;
	}

	/**
	 * Gets the middleName
	 * @return Returns a String
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * Sets the middleName
	 * @param middleName The middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the lastName
	 * @return Returns a String
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the lastName
	 * @param lastName The lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the icq
	 * @return Returns a String
	 */
	public String getIcq() {
		return icq;
	}
	
	/**
	 * Sets the icq
	 * @param icq The icq to set
	 */
	public void setIcq(String icq) {
		this.icq = icq;
	}

	/**
	 * Gets the friendInfo
	 * @return Returns a Friend
	 */
	public Friend getFriendInfo() {
		return friendInfo;
	}
	
	/**
	 * Sets the friendInfo
	 * @param friendInfo The friendInfo to set
	 */
	public void setFriendInfo(Friend friendInfo) {
		this.friendInfo = friendInfo;
	}

	/**
	 * Gets the firstName
	 * @return Returns a String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the firstName
	 * @param firstName The firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * Gets the coworkerInfo
	 * @return Returns a Coworker
	 */
	public Coworker getCoworkerInfo() {
		return coworkerInfo;
	}
	
	/**
	 * Sets the coworkerInfo
	 * @param coworkerInfo The coworkerInfo to set
	 */
	public void setCoworkerInfo(Coworker coworkerInfo) {
		this.coworkerInfo = coworkerInfo;
	}

	/**
	 * Gets the birthday
	 * @return Returns a Date
	 */
	public Date getBirthday() {
		return birthday;
	}
	
	/**
	 * Sets the birthday
	 * @param birthday The birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @see Person#isFriend()
	 */
	public boolean isFriend() {
		return friendInfo != null;
	}

	/**
	 * @see Person#isRelated()
	 */
	public boolean isRelated() {
		return relatedInfo != null;
	}

	/**
	 * @see Person#isShnip()
	 */
	public boolean isShnip() {
		return shnipInfo != null;
	}

	/**
	 * @see Person#isMsu()
	 */
	public boolean isMsu() {
		return msuInfo != null;
	}

	/**
	 * @see Person#isCoworker()
	 */
	public boolean isCoworker() {
		return coworkerInfo != null;
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
	 * Gets the basicPhone
	 * @return Returns a PhoneData
	 */
	public PhoneData getBasicPhone() {
		PhoneData result = null;
		if (phones == null) {
			return result;
		}
		Iterator i = phones.iterator();
		if (i.hasNext()) {
			result = (PhoneData) i.next();
		}
		while (i.hasNext()) {
			PhoneData data = (PhoneData) i.next();
			if (data.getBasic().booleanValue()) {
				result = data;
			}
		}
		return result;
	}
}
