package su.sergey.contacts.supply.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.supply.valueobjects.Supply2;

public class DefaultSupply2 implements Serializable, Supply2 {
	private SupplyHandle handle;
	private SupplyAttributes attributes;

	/**
	 * Constructor for DefaultSupply2
	 */
	public DefaultSupply2(SupplyHandle handle, SupplyAttributes attributes) {
		this.handle = handle;
		this.attributes = attributes;
	}

	/**
	 * Gets the handle
	 * @return Returns a SupplyHandle
	 */
	public SupplyHandle getHandle() {
		return handle;
	}
	
	/**
	 * Gets the attributes
	 * @return Returns a Supply
	 */
	public SupplyAttributes getAttributes() {
		return attributes;
	}
}
