package su.sergey.contacts.dao.dto;

public class DirectoryMetadataData implements java.io.Serializable, DirectoryMetadataCreateInfo, DirectoryMetadataUpdateInfo {
	private java.math.BigDecimal id;
	private String code;
	private String name;
	private String description;
	private String schemaName;
	private String tableName;
	private Integer seq;


	public java.math.BigDecimal getId() {
		return id;
	}
	public void  setId(java.math.BigDecimal id) {
		this.id= id;
	}
	public String getCode() {
		return code;
	}
	public void  setCode(String code) {
		this.code= code;
	}
	public String getName() {
		return name;
	}
	public void  setName(String name) {
		this.name= name;
	}
	public String getDescription() {
		return description;
	}
	public void  setDescription(String description) {
		this.description= description;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void  setSchemaName(String schemaName) {
		this.schemaName= schemaName;
	}
	public String getTableName() {
		return tableName;
	}
	public void  setTableName(String tableName) {
		this.tableName= tableName;
	}
	public Integer getSeq() {
		return seq;
	}
	public void  setSeq(Integer seq) {
		this.seq= seq;
	}

}