package su.sergey.contacts.person.valueobjects.handles;

import java.io.Serializable;

public class MsuDepartmentHandle implements Serializable {
	private Integer id;
	
	/**
	 * Constructor for MsuDepartmentHandle
	 */
	public MsuDepartmentHandle(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the id
	 * @return Returns a Integer
	 */
	public Integer getId() {
		return id;
	}
}
