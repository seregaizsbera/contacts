package su.sergey.contacts.inquiry.businessdelegate;

import java.util.Map;

import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

public interface InquiryBusinessDelegate {
	InquiryObject[] inquireTableAsIds(String tableName);
	InquiryObject[] inquireTableAsNames(String tableName);
	Map inquireTableAsHash(String tableName);
	String getCurrentDatabase();
}
