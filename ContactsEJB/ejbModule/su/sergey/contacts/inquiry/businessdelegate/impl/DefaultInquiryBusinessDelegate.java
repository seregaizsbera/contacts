package su.sergey.contacts.inquiry.businessdelegate.impl;

import java.rmi.RemoteException;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.inquiry.Inquiry;
import su.sergey.contacts.inquiry.InquiryHome;
import su.sergey.contacts.inquiry.businessdelegate.InquiryBusinessDelegate;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;

public class DefaultInquiryBusinessDelegate implements InquiryBusinessDelegate {
	private Inquiry inquiry;

	/**
	 * Constructor for DefaultInquiryBusinessDelegate
	 */
	public DefaultInquiryBusinessDelegate(String inquiryJndiName) {
		try {
			Context context = new InitialContext();
			Object homeObject = context.lookup(inquiryJndiName);
			InquiryHome home = (InquiryHome) PortableRemoteObject.narrow(homeObject, InquiryHome.class);
			inquiry = home.create();
		} catch (NamingException e) {
			throw new RuntimeDelegateException(e);
		} catch (CreateException e) {
			throw new RuntimeDelegateException(e);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see InquiryBusinessDelegate#inquireTableAsIds(String)
	 */
	public InquiryObject[] inquireTableAsIds(String tableName) {
		try {
			return inquiry.inquireTableAsIds(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see InquiryBusinessDelegate#inquireTableAsNames(String)
	 */
	public InquiryObject[] inquireTableAsNames(String tableName) {
		try {
			return inquiry.inquireTableAsNames(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see InquiryBusinessDelegate#inquireTableAsHash(String)
	 */
	public Map inquireTableAsHash(String tableName) {
		try {
			return inquiry.inquireTableAsHash(tableName);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
}
