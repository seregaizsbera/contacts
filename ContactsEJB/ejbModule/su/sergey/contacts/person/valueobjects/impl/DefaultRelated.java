package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.person.valueobjects.Related;

public class DefaultRelated implements Serializable, Related {

	/**
	 * Constructor for DefaultRelated
	 */
	public DefaultRelated() {
		super();
	}

	/**
	 * @see Related#getRelationShip()
	 */
	public String getRelationShip() {
		return null;
	}

	/**
	 * @see Related#getDescription()
	 */
	public String getDescription() {
		return null;
	}

}

