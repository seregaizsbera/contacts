package su.sergey.contacts.exceptions;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

public final class ExceptionUtil {
	public static Throwable followExceptionChain(Throwable e) {
		Throwable i = e;
		while (true) {
			if (i instanceof EJBException) {
				EJBException t = (EJBException) i;
				Throwable t1 = t.getCausedByException();
				if (t1 == null) {
					break;
				}
				i = t1;
			} else if (i instanceof RemoteException) {
				RemoteException t = (RemoteException) i;
				Throwable t1 = t.detail;
				if (t1 == null) {
					break;
				}
				i = t1;
			} else {
				break;
			}
		}
		return i;
	}
	
	public static String getMessage(Throwable e) {
		if (e == null) {
			return null;
		}
		Throwable i = followExceptionChain(e);
		if (i == null) {
			return null;
		}
		String message = i.getMessage();
		if (message == null) {
			return null;
		}
		int boundary = message.indexOf("\n\n");
		String result;
		if (boundary == -1) {
			result = message;
		} else {
			result = message.substring(0, boundary);
		}
		return result;
	}
}
