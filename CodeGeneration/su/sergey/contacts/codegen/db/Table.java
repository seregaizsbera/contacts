package su.sergey.contacts.codegen.db;

/**
 * Table
 * 
 * @author Сергей Богданов
 */
public class Table {
    private String schema = null;
    private String table = null;
    private String remarks = "";

    public Table() {}

    public Table(String schema, String table) {
        this.schema = schema;
        this.table = table;
    }

    public Table(String schema, String table, String remarks) {
        this.schema = schema;
        this.table = table;
        this.remarks = remarks;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getQualifiedName() {
    	return schema == null ? table : schema + "." + table;
    }
    
	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return getQualifiedName();
	}
}
