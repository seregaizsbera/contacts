package su.sergey.contacts.person.searchparamters;

import java.io.Serializable;
import java.util.Date;

public class PersonSearchParameters implements Serializable {
	public static final int GROUP_IGNORE = 0;
	public static final int PERSON_IN_GROUP = 1;
	public static final int PERSON_NOT_IN_GROUP = 2;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private Date birthday;
	private int coworker;
	private int shnip;
	private int friend;
	private int msu;
	private int related;
	private String email;
	private String icq;
	private String address;
	
	/**
	 * Gets the shnip
	 * @return Returns a int
	 */
	public int getShnip() {
		return shnip;
	}
	
	/**
	 * Sets the shnip
	 * @param shnip The shnip to set
	 */
	public void setShnip(int shnip) {
		this.shnip = shnip;
	}

	/**
	 * Gets the related
	 * @return Returns a int
	 */
	public int getRelated() {
		return related;
	}
	
	/**
	 * Sets the related
	 * @param related The related to set
	 */
	public void setRelated(int related) {
		this.related = related;
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
	 * Gets the msu
	 * @return Returns a int
	 */
	public int getMsu() {
		return msu;
	}
	
	/**
	 * Sets the msu
	 * @param msu The msu to set
	 */
	public void setMsu(int msu) {
		this.msu = msu;
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
	 * Gets the friend
	 * @return Returns a int
	 */
	public int getFriend() {
		return friend;
	}
	
	/**
	 * Sets the friend
	 * @param friend The friend to set
	 */
	public void setFriend(int friend) {
		this.friend = friend;
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
	 * Gets the coworker
	 * @return Returns a int
	 */
	public int getCoworker() {
		return coworker;
	}
	
	/**
	 * Sets the coworker
	 * @param coworker The coworker to set
	 */
	public void setCoworker(int coworker) {
		this.coworker = coworker;
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
}
