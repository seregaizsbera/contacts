package su.sergey.contacts.exceptions;

import java.rmi.RemoteException;

public final class ExceptionUtil {
	public static String extractShorMessage(RemoteException e) {
    	String message = e.detail.getMessage();
		int slashNPos = message.indexOf("\n");
		message = message.substring(0, slashNPos);
		return message;
	}
}
