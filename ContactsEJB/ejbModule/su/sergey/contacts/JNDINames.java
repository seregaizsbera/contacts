package su.sergey.contacts;

public interface JNDINames {
	String DEFAULT_DATA_SOURCE_REFERENCE = "java:comp/env/jdbc/DefaultDataSource";
	String DIRECTORY_BEAN = "ejb/su/sergey/contacts/directory/DirectoryHome";
	String PERSON_BEAN = "ejb/su/sergey/contacts/person/PersonHome";
	String QUERY_BEAN = "ejb/su/sergey/contacts/query/QueryHome";
	String INQUIRY_BEAN = "ejb/su/sergey/contacts/inquiry/InquiryHome";
}
