package su.sergey.contacts.directory.valueobjects.searchparameters;

import java.io.Serializable;

public class DirectorySearchParameters implements Serializable {
    private String tableName;
    private String schemaName;
    
	/**
	 * Gets the tableName
	 * @return Returns a String
	 */
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * Sets the tableName
	 * @param tableName The tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the schemaName
	 * @return Returns a String
	 */
	public String getSchemaName() {
		return schemaName;
	}
	
	/**
	 * Sets the schemaName
	 * @param schemaName The schemaName to set
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
