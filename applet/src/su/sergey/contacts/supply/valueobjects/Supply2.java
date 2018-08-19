package su.sergey.contacts.supply.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;

public class Supply2 implements Serializable {
	private SupplyHandle handle;
	private SupplyAttributes attributes;

	/**
	 * Constructor for Supply2
	 */
	public Supply2(SupplyHandle handle, SupplyAttributes attributes) {
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
