package su.sergey.contacts.dao.dto;

public class DirectoryMetadataDataDelegate {
		private DirectoryMetadataData _value;

	public java.math.BigDecimal getId() {
		return _value.getId();
	}
	public String getCode() {
		return _value.getCode();
	}
	public String getName() {
		return _value.getName();
	}
	public String getDescription() {
		return _value.getDescription();
	}
	public String getSchemaName() {
		return _value.getSchemaName();
	}
	public String getTableName() {
		return _value.getTableName();
	}
	public Integer getSeq() {
		return _value.getSeq();
	}



	public void  setId(java.math.BigDecimal id) {
		_value.setId(id);
	}
	public void  setCode(String code) {
		_value.setCode(code);
	}
	public void  setName(String name) {
		_value.setName(name);
	}
	public void  setDescription(String description) {
		_value.setDescription(description);
	}
	public void  setSchemaName(String schemaName) {
		_value.setSchemaName(schemaName);
	}
	public void  setTableName(String tableName) {
		_value.setTableName(tableName);
	}
	public void  setSeq(Integer seq) {
		_value.setSeq(seq);
	}

}