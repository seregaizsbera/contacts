package su.sergey.contacts.util;

import org.omg.Security.InvalidCredentialType;
import org.omg.SecurityLevel2.Credentials;
import org.omg.SecurityLevel2.InvalidCredential;
import org.omg.SecurityLevel2.LoginFailed;

public final class ServiceUtil {
	static public void login(String userName, String password) throws LoginFailedException {
		if (userName == null) {
			return;
		}
		try {
			LoginHelper loginHelper = new LoginHelper();
			Credentials credentials = loginHelper.login(userName, password);
			loginHelper.setInvocationCredentials(credentials);
		} catch (LoginFailed e) {
			throw new LoginFailedException(e);
		} catch (InvalidCredential e) {
			throw new LoginFailedException(e);
		} catch (InvalidCredentialType e) {
			throw new LoginFailedException(e);
		}
	}
}