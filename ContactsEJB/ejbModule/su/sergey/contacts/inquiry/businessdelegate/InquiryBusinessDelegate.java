package su.sergey.contacts.inquiry.businessdelegate;

import su.sergey.contacts.inquiry.valueobjects.InquiryObjects;

public interface InquiryBusinessDelegate {
	InquiryObjects inquireTable(String alias);
	String[] inquireTableAliases(int scope);
	String getCurrentDatabase();
}
