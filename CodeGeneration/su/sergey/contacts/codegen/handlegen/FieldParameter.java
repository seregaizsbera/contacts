package su.sergey.contacts.codegen.handlegen;

final class FieldParameter {
	String typeName;
	String fieldName;
	
	/**
	 * Constructor for FieldParameter
	 */
	FieldParameter(String typeName, String fieldName) {
		this.typeName = typeName;
		this.fieldName = fieldName;
	}
	
	/**
	 * Gets the fieldName
	 * @return Returns a String
	 */
	String getFieldName() {
		return fieldName;
	}
	
	/**
	 * Gets the typeName
	 * @return Returns a String
	 */
	public String getTypeName() {
		return typeName;
	}	
}
