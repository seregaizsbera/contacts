package su.sergey.contacts.dao.dto;

public interface DirectoryColumnMetadataCreateInfo {


	String getCode();
	String getShortName();
	String getFullName();
	Integer getIsKey();
	Integer getIsPrimary();
	String getColumnName();
	Integer getSeq();

}