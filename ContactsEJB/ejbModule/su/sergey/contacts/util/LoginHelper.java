package su.sergey.contacts.util;

import java.io.UnsupportedEncodingException;

import org.omg.Security.Attribute;
import org.omg.Security.AttributeType;
import org.omg.Security.CredentialType;
import org.omg.Security.DuplicateAttributeType;
import org.omg.Security.ExtensibleFamily;
import org.omg.Security.InvalidAttributeType;
import org.omg.Security.InvalidCredentialType;
import org.omg.Security.OpaqueHolder;
import org.omg.Security.Public;
import org.omg.SecurityLevel2.Credentials;
import org.omg.SecurityLevel2.CredentialsHolder;
import org.omg.SecurityLevel2.InvalidCredential;
import org.omg.SecurityLevel2.LoginFailed;

import com.ibm.CORBA.iiop.ORB;
import com.ibm.IExtendedSecurity.Current;
import com.ibm.IExtendedSecurity._LoginHelper;
import com.ibm.ejs.oa.EJSORB;

/**
 * LoginHelper is a utility class that uses CORBA security APIs to perform
 * a programmatic login. It uses the CORBA "SecurityCurrent" to obtain a
 * CORBA login_helper and invokes request_login on that object. This method
 * sets up the userid and password on the thread so that any further
 * method invocation on a secure server will be invoked under that userid/password.
 * The server will extract the userid and password and will perform authentication
 * before invoking the method.
 */
final class LoginHelper {
    static private final int PUBLIC = 0;
    static private final AttributeType secAttrType[];

    // use the CORBA attribute type to get public attribute (user name),
    // access id (user registry specific unique ID)
    static {
        secAttrType = new AttributeType[1];
        ExtensibleFamily familyOMG = new ExtensibleFamily((short) 0, (short) 1);
        secAttrType[PUBLIC] = new AttributeType(familyOMG, Public.value);
    }

    // IBM's extension of SecurityCurrent
    private Current current = null;

    private Current getSecurityCurrent() throws IllegalStateException {
        Current current = null;
        try {
            // obtain a reference to the underlying ORB
            ORB orb = EJSORB.getORBInstance();
            // obtain the SecurityCurrent from the ORB
            if (orb != null) {
                current = (Current) orb.resolve_initial_references("SecurityCurrent");
            }
            else {
                throw new IllegalStateException("SecurityCurrent: null");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error getting SecurityCurrent from the ORB");
        }
        return current;
    }
    
    /**
      * The method login() sets the authentication data on the thread of execution
      * by calling the login_helper.request_login
      * 
      * @param userid userid
      * @param password user's password
      * @return Credentials set on the security context based on the userid and password
      * @throws LoginFailed exception
      */
    public Credentials login(String userid, String password) throws LoginFailed {
        if (!(userid != null && (userid.length() != 0))) {
            // Invalid userID (empty string)
            throw new LoginFailed();
        }

        if (!(password != null && (password.length() != 0))) {
            // Invalid password (empty string)
            throw new LoginFailed();
        }

        _LoginHelper loginHelper = current.login_helper();
        return loginHelper.request_login(userid, "", password, new CredentialsHolder(), new OpaqueHolder());
    }

    /**
     * This method sets the specified credentials as the invocation
     * credentials. Any method invoked after this will be executed under
     * the authority of the credentials.
     * @param invokedCreds credentials under which future methods should be invoked
     * @throws InvalidCredentialType, InvalidCredential
     */
    public void setInvocationCredentials(Credentials invokedCreds) throws InvalidCredentialType, InvalidCredential {
        current.set_credentials(CredentialType.SecInvocationCredentials, invokedCreds);
    }

    /**
     * This method gets the credentials under which the current
     * method is getting executed.
     * 
     * @return credentials under which the current method is invoked
     * @throws InvalidCredentialType
     */
    public Credentials getInvocationCredentials() throws InvalidCredentialType {
        return current.get_credentials(CredentialType.SecInvocationCredentials);
    }

    /**
     * Returns a human-readable user name attribute of the specified credentials.
     * 
     * @return String the user name
     */
    public String getUserName(Credentials creds) throws DuplicateAttributeType, InvalidAttributeType {
        Attribute[] publicAttrs = creds.get_attributes(secAttrType);
        String uname = "";
        try {
            uname = new String(publicAttrs[PUBLIC].value, "UTF8");
        }
        catch (UnsupportedEncodingException e) {}
        return uname;

    }
    
    public LoginHelper() throws IllegalStateException {
        // obtain the security current from the ORB instance
        current = getSecurityCurrent();
    }
}
