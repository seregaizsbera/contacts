package su.sergey.contacts.inquiry.valueobjects.impl;

import java.util.ArrayList;
import java.util.Collection;

import su.sergey.contacts.inquiry.valueobjects.InquiryObjectsAsCollection;

public class InquiryObjectsAsArrayList extends ArrayList implements InquiryObjectsAsCollection {

	/**
	 * Constructor for InquiryObjectsAsArrayList
	 */
	public InquiryObjectsAsArrayList(int arg0) {
		super(arg0);
	}

	/**
	 * Constructor for InquiryObjectsAsArrayList
	 */
	public InquiryObjectsAsArrayList() {
		super();
	}

	/**
	 * Constructor for InquiryObjectsAsArrayList
	 */
	public InquiryObjectsAsArrayList(Collection arg0) {
		super(arg0);
	}

}

