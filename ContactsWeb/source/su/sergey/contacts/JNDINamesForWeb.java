package su.sergey.contacts;

public interface JNDINamesForWeb {
	String SESSION_FACADE_REFERENCE = "java:comp/env/ejb/DAOSessionFacade";
    String DIRECTORIES_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/DirectoriesPageIterator";
    String DIRECTORY_RECORDS_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/DirectoryRecordsPageIterator";
	String PERSON_PAGE_ITERATOR_REFERENCE = "java:comp/env/ejb/PersonPageIterator";
	String INQUIRY_REFERENCE = "java:comp/env/ejb/Inquiry";
}
