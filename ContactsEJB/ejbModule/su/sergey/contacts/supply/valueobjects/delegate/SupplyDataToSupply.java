package su.sergey.contacts.supply.valueobjects.delegate;

import java.io.Serializable;
import java.util.Collection;

import java.util.Date;
import su.sergey.contacts.dto.SupplyData;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

public class SupplyDataToSupply implements Serializable, SupplyAttributes {
	private SupplyData data;
	private Collection phones;
	private Collection emails;
	
	/**
	 * Constructor for SupplyDataToSupplyAttributes
	 */
	public SupplyDataToSupply(SupplyData data, Collection phones, Collection emails) {
	    this.data = data;
	    this.phones = phones;
	    this.emails = emails;
	}

	/**
	 * @see SupplyAttributes#getName()
	 */
	public String getName() {
		return data.getName();
	}

	/**
	 * @see SupplyAttributes#getParentName()
	 */
	public String getParentName() {
		return data.getParentName();
	}

	/**
	 * @see SupplyAttributes#getKind()
	 */
	public Integer getKind() {
		return data.getKind();
	}

	/**
	 * @see SupplyAttributes#getAddress()
	 */
	public String getAddress() {
		return data.getAddress();
	}

	/**
	 * @see SupplyAttributes#getUrl()
	 */
	public String getUrl() {
		return data.getUrl();
	}

	/**
	 * @see SupplyAttributes#getInn()
	 */
	public String getInn() {
		return data.getInn();
	}

	/**
	 * @see SupplyAttributes#isImportant()
	 */
	public boolean isImportant() {
		return data.getImportant().booleanValue();
	}

	/**
	 * @see SupplyAttributes#getNote()
	 */
	public String getNote() {
		return data.getNote();
	}

	/**
	 * @see SupplyAttributes#getPhones()
	 */
	public Collection getPhones() {
		return phones;
	}

	/**
	 * @see SupplyAttributes#getEmails()
	 */
	public Collection getEmails() {
		return emails;
	}
	
	/**
	 * @see SupplyAttributes#getShortName()
	 */
	public String getShortName() {
		return data.getShortName();
	}
	
	/**
	 * @see SupplyAttributes#getMetro()
	 */
	public String getMetro() {
		return data.getMetro();
	}
	
	/**
	 * @see SupplyAttributes#getUpdateTime()
	 */
	public Date getUpdateTime() {
		return data.getUpdateTime();
	}
	
	/**
	 * @see SupplyAttributes#getPropertyForm()
	 */
	public String getPropertyForm() {
		return data.getPropertyForm();
	}
	
	/**
	 * @see SupplyAttributes#getOgrn()
	 */
	public String getOgrn() {
		return data.getOgrn();
	}
	
	/**
	 * @see SupplyAttributes#getKpp()
	 */
	public String getKpp() {
		return data.getKpp();
	}
}
