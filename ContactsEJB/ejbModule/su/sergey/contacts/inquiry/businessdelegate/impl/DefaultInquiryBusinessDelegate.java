package su.sergey.contacts.inquiry.businessdelegate.impl;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.inquiry.Inquiry;
import su.sergey.contacts.inquiry.InquiryHome;
import su.sergey.contacts.inquiry.businessdelegate.InquiryBusinessDelegate;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjects;

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
	 * @see InquiryBusinessDelegate#getCurrentDatabase()
	 */
	public String getCurrentDatabase() {
		try {
			return inquiry.getCurrentDatabase();
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
	
	/**
	 * @see InquiryBusinessDelegate#inquireTable(String)
	 */
	public InquiryObjects inquireTable(String alias) {
		try {
			return inquiry.inquireTable(alias);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}

	/**
	 * @see InquiryBusinessDelegate#inquireTableAliases(int, int)
	 */
	public String[] inquireTableAliases(int scope) {
		try {
			return inquiry.inquireTableAliases(scope);
		} catch (RemoteException e) {
			throw new RuntimeDelegateException(e);
		}
	}
}
