package su.sergey.contacts.inquiry.valueobjects.impl;

import java.util.HashMap;
import java.util.Map;

import su.sergey.contacts.inquiry.valueobjects.InquiryObjectsAsMap;

public class InquiryObjectsAsHashMap extends HashMap implements InquiryObjectsAsMap {

	/**
	 * Constructor for InquiryObjectsAsHashMap
	 */
	public InquiryObjectsAsHashMap(int arg0, float arg1) {
		super(arg0, arg1);
	}

	/**
	 * Constructor for InquiryObjectsAsHashMap
	 */
	public InquiryObjectsAsHashMap(int arg0) {
		super(arg0);
	}

	/**
	 * Constructor for InquiryObjectsAsHashMap
	 */
	public InquiryObjectsAsHashMap() {
		super();
	}

	/**
	 * Constructor for InquiryObjectsAsHashMap
	 */
	public InquiryObjectsAsHashMap(Map arg0) {
		super(arg0);
	}
}
