package su.sergey.contacts.inquiry.businessdelegate;

import java.util.HashMap;

import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

public interface InquiryBusinessDelegate {
	InquiryObject[] inquireTableAsIds(String tableName);
	InquiryObject[] inquireTableAsNames(String tableName);
	HashMap inquireTableAsHash(String tableName);
}
