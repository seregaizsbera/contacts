package su.sergey.contacts.call.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.dto.CallExpenseHandle;

public class CallExpense2 implements Serializable {
	private CallExpenseAttributes attributes;
	private CallExpenseHandle handle;

	/**
	 * Constructor for CallExpense2
	 */
	public CallExpense2(CallExpenseHandle handle, CallExpenseAttributes attributes) {
		this.handle = handle;
		this.attributes = attributes;
	}
	
	/**
	 * Gets the attributes
	 * @return Returns a CallExpenseAttributes
	 */
	public CallExpenseAttributes getAttributes() {
		return attributes;
	}
	
	/**
	 * Gets the handle
	 * @return Returns a CallExpenseHandle
	 */
	public CallExpenseHandle getHandle() {
		return handle;
	}
}
