package su.sergey.contacts.codegen.handlegen;

final class FieldParameter {
	String typeName;
	String fieldName;
	
	/**
	 * Constructor for FieldParameter
	 */
	public FieldParameter(String typeName, String fieldName) {
		this.typeName = typeName;
		this.fieldName = fieldName;
	}
	
	/**
	 * Gets the fieldName
	 * @return Returns a String
	 */
	public String getFieldName() {
		return fieldName;
	}
	
	/**
	 * Sets the fieldName
	 * @param fieldName The fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Gets the typeName
	 * @return Returns a String
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * Sets the typeName
	 * @param typeName The typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
