package su.sergey.contacts;

public interface JNDINames {
	String DEFAULT_DATA_SOURCE_REFERENCE = "java:comp/env/jdbc/DefaultDataSource";
	String DIRECTORY_BEAN = "ejb/su/sergey/contacts/directory/DirectoryHome";
	String PERSON_BEAN = "ejb/su/sergey/contacts/person/PersonHome";
	String QUERY_BEAN = "ejb/su/sergey/contacts/query/QueryHome";
	String SUPPLY_BEAN = "ejb/su/sergey/contacts/supply/SupplyHome";
	String INQUIRY_BEAN = "ejb/su/sergey/contacts/inquiry/InquiryHome";
	String PHONE_BEAN = "ejb/su/sergey/contacts/phone/PhoneHome";
	String EMAIL_BEAN = "ejb/su/sergey/contacts/email/EmailHome";
	String PROPERTY_BEAN = "ejb/su/sergey/contacts/properties/PropertyHome";
	String PERSON_PAGE_ITERATOR_BEAN = "ejb/su/sergey/contacts/person/PersonPageIteratorHome";
	String REPORT_BEAN = "ejb/su/sergey/contacts/report/ReportHome";
	String SUPPLY_PAGE_ITERATOR_BEAN = "ejb/su/sergey/contacts/supply/SupplyPageIteratorHome";
	String DAO_SESSION_FACADE_BEAN = "ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome";
}
