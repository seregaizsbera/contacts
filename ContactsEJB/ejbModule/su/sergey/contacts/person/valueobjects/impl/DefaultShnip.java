package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.valueobjects.Shnip;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class DefaultShnip implements Serializable, Shnip {
	private Date graduateDate;
	private String formLetter;
	private PersonHandle formLeader;
	private String description;
	private String graduateDateStr;

	/**
	 * Gets the graduateDate
	 * @return Returns a Date
	 */
	public Date getGraduateDate() {
		return graduateDate;
	}
	
	/**
	 * Sets the graduateDate
	 * @param graduateDate The graduateDate to set
	 */
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	/**
	 * Gets the formLetter
	 * @return Returns a String
	 */
	public String getFormLetter() {
		return formLetter;
	}
	
	/**
	 * Sets the formLetter
	 * @param formLetter The formLetter to set
	 */
	public void setFormLetter(String formLetter) {
		this.formLetter = formLetter;
	}

	/**
	 * Gets the formLeader
	 * @return Returns a PersonHandle
	 */
	public PersonHandle getFormLeader() {
		return formLeader;
	}
	
	/**
	 * Sets the formLeader
	 * @param formLeader The formLeader to set
	 */
	public void setFormLeader(PersonHandle formLeader) {
		this.formLeader = formLeader;
	}

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
	 * @see Shnip#getGraduateDateStr()
	 */
	public String getGraduateDateStr() {
		String result = null;
		if (graduateDate != null) {
    		result = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_YEAR_FORMAT).format(graduateDate);
		}
		return result;
	}
}
