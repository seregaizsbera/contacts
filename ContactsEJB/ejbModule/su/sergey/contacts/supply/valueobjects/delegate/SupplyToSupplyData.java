package su.sergey.contacts.supply.valueobjects.delegate;

import su.sergey.contacts.dto.SupplyCreateInfo;
import su.sergey.contacts.dto.SupplyUpdateInfo;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

public final class SupplyToSupplyData implements SupplyUpdateInfo, SupplyCreateInfo {
	private SupplyAttributes attributes;

	/**
	 * Constructor for SupplyToSupplyData
	 */
	public SupplyToSupplyData(SupplyAttributes attributes) {
		this.attributes = attributes;
	}

	/**
	 * @see SupplyUpdateInfo#getName()
	 */
	public String getName() {
		return attributes.getName();
	}

	/**
	 * @see SupplyUpdateInfo#getParentName()
	 */
	public String getParentName() {
		return attributes.getParentName();
	}

	/**
	 * @see SupplyUpdateInfo#getKind()
	 */
	public Integer getKind() {
		return attributes.getKind();
	}

	/**
	 * @see SupplyUpdateInfo#getAddress()
	 */
	public String getAddress() {
		return attributes.getAddress();
	}

	/**
	 * @see SupplyUpdateInfo#getUrl()
	 */
	public String getUrl() {
		return attributes.getUrl();
	}

	/**
	 * @see SupplyUpdateInfo#getInn()
	 */
	public String getInn() {
		return attributes.getInn();
	}

	/**
	 * @see SupplyUpdateInfo#getImportant()
	 */
	public Boolean getImportant() {
		return new Boolean(attributes.isImportant());
	}

	/**
	 * @see SupplyUpdateInfo#getNote()
	 */
	public String getNote() {
		return attributes.getNote();
	}
	
	/**
	 * @see SupplyUpdateInfo#getShortName()
	 */
	public String getShortName() {
		return attributes.getShortName();
	}
	
	/**
	 * @see SupplyUpdateInfo#getMetro()
	 */
	public String getMetro() {
		return attributes.getMetro();
	}
	
	/**
	 * @see SupplyUpdateInfo#getPropertyForm()
	 */
	public String getPropertyForm() {
		return attributes.getPropertyForm();
	}
	
	/**
	 * @see SupplyUpdateInfo#getOgrn()
	 */
	public String getOgrn() {
		return attributes.getOgrn();
	}
	
	/**
	 * @see SupplyUpdateInfo#getKpp()
	 */
	public String getKpp() {
		return attributes.getKpp();
	}
}
