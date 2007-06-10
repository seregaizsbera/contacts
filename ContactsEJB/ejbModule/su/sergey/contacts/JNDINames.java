package su.sergey.contacts;

public interface JNDINames {
	String DEFAULT_DATA_SOURCE_REFERENCE = "java:comp/env/jdbc/DefaultDataSource";
	String CALL_REFERENCE = "java:comp/env/ejb/Call";
	String DIRECTORIES_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/DirectoriesPageIterator";
	String DIRECTORY_REFERENCE = "java:comp/env/ejb/Directory";
	String DIRECTORY_RECORDS_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/DirectoryRecordsPageIterator";
	String PERSON_REFERENCE = "java:comp/env/ejb/Person";
	String QUERY_REFERENCE = "java:comp/env/ejb/Query";
	String SUPPLY_REFERENCE = "java:comp/env/ejb/Supply";
	String INQUIRY_REFERENCE = "java:comp/env/ejb/Inquiry";
	String PHONE_REFERENCE = "java:comp/env/ejb/Phone";
	String EMAIL_REFERENCE = "java:comp/env/ejb/Email";
	String PROPERTY_REFERENCE = "java:comp/env/ejb/Property";
	String PERSON_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/PersonPageIterator";
	String REPORT_REFERENCE = "java:comp/env/ejb/Report";
	String SUPPLY_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/SupplyPageIterator";
	String DAO_SESSION_FACADE_REFERENCE = "java:comp/env/ejb/DAOSessionFacade";
}
