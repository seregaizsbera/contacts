package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.valueobjects.Shnip;

public class DefaultShnip implements Serializable, Shnip {

	/**
	 * Constructor for DefaultShnip
	 */
	public DefaultShnip() {
		super();
	}

	/**
	 * @see Shnip#getGraduateDate()
	 */
	public Date getGraduateDate() {
		return null;
	}

	/**
	 * @see Shnip#getFormLetter()
	 */
	public String getFormLetter() {
		return null;
	}

	/**
	 * @see Shnip#getFormLeader()
	 */
	public PersonHandle getFormLeader() {
		return null;
	}

	/**
	 * @see Shnip#getDescription()
	 */
	public String getDescription() {
		return null;
	}

}

