package su.sergey.contacts.inquiry.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

public class DefaultInquiryObject implements Serializable, InquiryObject {
	private Integer id;
	private String name;

	/**
	 * Constructor for DefaultInquiryObject
	 */
	public DefaultInquiryObject() {
	}

	/**
	 * Constructor for DefaultInquiryObject
	 */
	public DefaultInquiryObject(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Gets the id
	 * @return Returns a Integer
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id
	 * @param id The id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
}
