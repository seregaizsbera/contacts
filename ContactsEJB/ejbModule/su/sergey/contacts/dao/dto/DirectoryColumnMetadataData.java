package su.sergey.contacts.dao.dto;

public class DirectoryColumnMetadataData implements java.io.Serializable, DirectoryColumnMetadataCreateInfo, DirectoryColumnMetadataUpdateInfo {
	private java.math.BigDecimal id;
	private String code;
	private String shortName;
	private String fullName;
	private Integer isKey;
	private Integer isPrimary;
	private String columnName;
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
	public String getShortName() {
		return shortName;
	}
	public void  setShortName(String shortName) {
		this.shortName= shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void  setFullName(String fullName) {
		this.fullName= fullName;
	}
	public Integer getIsKey() {
		return isKey;
	}
	public void  setIsKey(Integer isKey) {
		this.isKey= isKey;
	}
	public Integer getIsPrimary() {
		return isPrimary;
	}
	public void  setIsPrimary(Integer isPrimary) {
		this.isPrimary= isPrimary;
	}
	public String getColumnName() {
		return columnName;
	}
	public void  setColumnName(String columnName) {
		this.columnName= columnName;
	}
	public Integer getSeq() {
		return seq;
	}
	public void  setSeq(Integer seq) {
		this.seq= seq;
	}

}