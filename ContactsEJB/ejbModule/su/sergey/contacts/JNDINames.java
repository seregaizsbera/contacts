package su.sergey.contacts;

public interface JNDINames {
	String DEFAULT_DATA_SOURCE_REFERENCE = "java:comp/env/jdbc/DefaultDataSource";
	String SESSION_FACADE_REFERENCE = "java:comp/env/ejb/DAOSessionFacade";
    String DIRECTORIES_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/DirectoriesPageIterator";
    String DIRECTORY_RECORDS_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/DirectoryRecordsPageIterator";
	String PERSONS_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/PersonsPageIterator";
	String DIRECTORY_BEAN = "ejb/su/sergey/contacts/directory/DirectoryHome";
	String PERSON_BEAN = "ejb/su/sergey/contacts/person/PersonHome";
}
