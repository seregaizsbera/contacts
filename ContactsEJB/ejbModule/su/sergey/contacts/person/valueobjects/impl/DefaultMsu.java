package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import su.sergey.contacts.person.valueobjects.Friend;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.person.valueobjects.Person;
import su.sergey.contacts.person.valueobjects.Related;
import su.sergey.contacts.person.valueobjects.Shnip;

public class DefaultMsu implements Serializable, Person {

	/**
	 * Constructor for DefaultMsu
	 */
	public DefaultMsu() {
		super();
	}

	/**
	 * @see Person#getFirstName()
	 */
	public String getFirstName() {
		return null;
	}

	/**
	 * @see Person#getMiddleName()
	 */
	public String getMiddleName() {
		return null;
	}

	/**
	 * @see Person#getLastName()
	 */
	public String getLastName() {
		return null;
	}

	/**
	 * @see Person#getPhones()
	 */
	public Collection getPhones() {
		return null;
	}

	/**
	 * @see Person#getAddress()
	 */
	public String getAddress() {
		return null;
	}

	/**
	 * @see Person#getEmails()
	 */
	public Collection getEmails() {
		return null;
	}

	/**
	 * @see Person#getIcq()
	 */
	public String getIcq() {
		return null;
	}

	/**
	 * @see Person#isFriend()
	 */
	public boolean isFriend() {
		return false;
	}

	/**
	 * @see Person#isRelated()
	 */
	public boolean isRelated() {
		return false;
	}

	/**
	 * @see Person#isShnip()
	 */
	public boolean isShnip() {
		return false;
	}

	/**
	 * @see Person#isMsu()
	 */
	public boolean isMsu() {
		return false;
	}

	/**
	 * @see Person#getBirthday()
	 */
	public Date getBirthday() {
		return null;
	}

	/**
	 * @see Person#getFriendInfo()
	 */
	public Friend getFriendInfo() {
		return null;
	}

	/**
	 * @see Person#getShnipInfo()
	 */
	public Shnip getShnipInfo() {
		return null;
	}

	/**
	 * @see Person#getMsuInfo()
	 */
	public Msu getMsuInfo() {
		return null;
	}

	/**
	 * @see Person#getRelatedInfo()
	 */
	public Related getRelatedInfo() {
		return null;
	}

}

