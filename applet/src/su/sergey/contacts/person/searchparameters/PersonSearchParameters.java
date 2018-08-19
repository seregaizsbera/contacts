package su.sergey.contacts.person.searchparameters;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.valueobjects.PersonSearchGroupModes;

public class PersonSearchParameters implements Serializable {
	public static final int GROUP_IGNORE = 0;
	public static final int PERSON_IN_GROUP = 1;
	public static final int PERSON_NOT_IN_GROUP = 2;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private Date beforeBirthdayDay;
	private Date afterBirthdayDay;
	private Date yearOfBirthday;
	private int monthOfBirthday;
	private int coworker;
	private int shnip;
	private int friend;
	private int msu;
	private int related;
	private String email;
	private String icq;
	private String address;
	private Integer gender;
	private Integer groupMode;
	private boolean fullData;
	private boolean sorted;
	private String note;
	
	public PersonSearchParameters() {
		monthOfBirthday = -1;
	}
	
	public PersonSearchParameters(String firstName,
	                              String middleName,
	                              String lastName,
	                              String phone,
	                              Date yearOfBirthday,
	                              int monthOfBirthday,
	                              String email,
	                              String icq,
	                              String address,
	                              Integer gender,
	                              Integer groupMode,
	                              String note,
	                              boolean fullData) {
	    this.firstName = firstName;
	    this.middleName = middleName;
	    this.lastName = lastName;
	    this.phone = phone;
	    this.yearOfBirthday = yearOfBirthday;
	    this.monthOfBirthday = monthOfBirthday;
	    this.email = email;
	    this.icq = icq;
	    this.address = address;
	    this.gender = gender;
		this.coworker = GROUP_IGNORE;
		this.shnip = GROUP_IGNORE;
		this.friend = GROUP_IGNORE;
		this.msu = GROUP_IGNORE;
		this.related = GROUP_IGNORE;
		this.note = note;
		this.fullData = fullData;
		setGroupMode(groupMode);
	}
	
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
	 * Gets the monthOfBirthday
	 * @return Returns a int
	 */
	public int getMonthOfBirthday() {
		return monthOfBirthday;
	}
	
	/**
	 * Sets the monthOfBirthday
	 * @param monthOfBirthday The monthOfBirthday to set
	 */
	public void setMonthOfBirthday(int monthOfBirthday) {
		this.monthOfBirthday = monthOfBirthday;
	}
	
	/**
	 * Gets the gender
	 * @return Returns a Integer
	 */
	public Integer getGender() {
		return gender;
	}
	
	/**
	 * Sets the gender
	 * @param gender The gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * Gets the groupMode
	 * @return Returns a Integer
	 */
	public Integer getGroupMode() {
		return groupMode;
	}
	
	/**
	 * Sets the groupMode
	 * @param groupMode The groupMode to set
	 */
	public void setGroupMode(Integer groupMode) {
		this.groupMode = groupMode;
		if (groupMode != null) {
			if (groupMode.equals(PersonSearchGroupModes.COWORKER)) {
			    this.coworker = PERSON_IN_GROUP;
			} else if (groupMode.equals(PersonSearchGroupModes.FRIEND)) {
				this.friend = PERSON_IN_GROUP;
			} else if (groupMode.equals(PersonSearchGroupModes.MSU)) {
				this.msu = PERSON_IN_GROUP;
			} else if (groupMode.equals(PersonSearchGroupModes.RELATED)) {
				this.related = PERSON_IN_GROUP;
			} else if (groupMode.equals(PersonSearchGroupModes.SHNIP)) {
				this.shnip = PERSON_IN_GROUP;
			}
		}
	}
	
	public boolean isFullData() {
		return fullData;
	}
	
	public void setFullData(boolean fullData) {
		this.fullData = fullData;
	}
	
	/**
	 * Gets the beforeBirthdayDay
	 * @return Returns a Date
	 */
	public Date getBeforeBirthdayDay() {
		return beforeBirthdayDay;
	}
	
	/**
	 * Sets the beforeBirthdayDay
	 * @param beforeBirthdayDay The beforeBirthdayDay to set
	 */
	public void setBeforeBirthdayDay(Date beforeBirthdayDay) {
		this.beforeBirthdayDay = beforeBirthdayDay;
	}

	/**
	 * Gets the afterBirthdayDay
	 * @return Returns a Date
	 */
	public Date getAfterBirthdayDay() {
		return afterBirthdayDay;
	}
	
	/**
	 * Sets the afterBirthdayDay
	 * @param afterBirthdayDay The afterBirthdayDay to set
	 */
	public void setAfterBirthdayDay(Date afterBirthdayDay) {
		this.afterBirthdayDay = afterBirthdayDay;
	}
	
	public boolean needBirthdays() {
		boolean result = false;
		result |= afterBirthdayDay != null;
		result |= beforeBirthdayDay != null;
		result |= yearOfBirthday != null;
		result |= monthOfBirthday >= 0;
		return result;
	}
	
	public boolean needPhones() {
		boolean result = phone != null;
		return result;
	}
	
	public boolean needEmails() {
		boolean result = email != null;
		return result;
	}
	
	public boolean needAddress() {
		boolean result = address != null;
		return result;
	}
	
	public boolean needIcq() {
		boolean result = icq != null;
		return result;
	}
	
	public boolean needBirthdaySort() {
		boolean result = afterBirthdayDay != null;
		result |= beforeBirthdayDay != null;
		return result;
	}
	
	public boolean isSorted() {
		return sorted;
	}
	
	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}
	
	/**
	 * Gets the yearOfBirthday
	 * @return Returns a Date
	 */
	public Date getYearOfBirthday() {
		return yearOfBirthday;
	}
	
	/**
	 * Sets the yearOfBirthday
	 * @param yearOfBirthday The yearOfBirthday to set
	 */
	public void setYearOfBirthday(Date yearOfBirthday) {
		this.yearOfBirthday = yearOfBirthday;
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
}
