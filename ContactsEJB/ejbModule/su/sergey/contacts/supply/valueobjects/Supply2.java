package su.sergey.contacts.supply.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.SupplyHandle;

public interface Supply2 extends Serializable {
	SupplyHandle getHandle();
	SupplyAttributes getAttributes();
}
