package su.sergey;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

import javax.naming.*;
import javax.rmi.*;
import javax.swing.*;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.Security.*;

import su.sergey.contacts.sessionfacade.*;

public class NewApplet extends JApplet {
    private JButton button;

    public String[][] getParameterInfo() {
        return new String[0][0];
    }
    
    private void perform() {
        AccessController.doPrivileged(new PrivilegedAction() {
	    public Object run() {
	        try {
	            PrintWriter output = new PrintWriter(new FileOutputStream("/tmp/applet.txt"));
	            output.println("Сергей");
                    output.close();
	            Properties sysProperties = System.getProperties();
	            for (Iterator i = new TreeSet(sysProperties.keySet()).iterator(); i.hasNext();) {
	                String key = (String) i.next();
	        	System.err.println(key + " = " + sysProperties.getProperty(key));
	            }
	            System.err.println(System.getSecurityManager());
	            Properties properties = new Properties();
	            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	            InputStream input = classLoader.getResourceAsStream("connect.properties");
	            properties.load(input);
	            String host = getDocumentBase().getHost();
	            String port = "2810";
	            ORB orb = ORB.init(new String[0], properties);
	            String refs[] = orb.list_initial_services();
	            for (int i = 0; i < refs.length; i++) {
	                System.err.println(refs[i]);
	            }
		    
		    org.omg.CORBA.Object object = orb.resolve_initial_references("SecurityCurrent");
		    org.omg.SecurityLevel2.Current securityCurrent = org.omg.SecurityLevel2.CurrentHelper.narrow(object);
		    org.omg.SecurityLevel2.PrincipalAuthenticator authenticator = securityCurrent.principal_authenticator();
		    org.omg.SecurityLevel2.CredentialsHolder credentialsHolder = new org.omg.SecurityLevel2.CredentialsHolder();
		    AuthenticationStatus status = authenticator.authenticate(0, "sergey", "ser62951413".getBytes(), new Attribute[0], credentialsHolder, new OpaqueHolder(), new OpaqueHolder());
		    if (status != AuthenticationStatus.SecAuthSuccess) {
		        throw new SecurityException("Authentication failed");
		    }
		    securityCurrent.set_credentials(CredentialType.SecInvocationCredentials, credentialsHolder.value);
	            object = orb.string_to_object("corbaloc:iiop:" + host + ":" + port + "/NameServiceServerRoot");
	            NamingContextExt context = NamingContextExtHelper.narrow(object);
	            object = context.resolve_str("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome");
	            DAOSessionFacadeHome home = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
	            System.err.println(orb);
	            System.err.println(context);
	            System.err.println(home);
	            DAOSessionFacade facade = home.create();
		    System.err.println(facade);
	            System.err.println("OK");
	            System.err.println(getDocumentBase());
	            System.err.println(getCodeBase());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return null;
	    }
        });		
    }
  
    public void init() {
        super.init();
	System.setProperty("traceSettingsFile", "trace.properties");
        System.err.println("MyApplet: init");
	button = new JButton(new AbstractAction("Нажми") {
	    public void actionPerformed(ActionEvent event) {
	        perform();
	    }
	});
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
	contentPane.add(button);
    }
    
    public static void main(String args[]) {
        try {
	    JApplet applet = new NewApplet();
	    applet.setStub(new AppletStub() {
	        public boolean isActive() {
		    return true;
		}
                public URL getDocumentBase() {
		    try {
		        return new URL("http://localhost/~sergey/applet");
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    }
		    return null;
		}
                public URL getCodeBase() {
		    return getDocumentBase();
		}
	        public String getParameter(String a) {
		    return null;
		}
	        public AppletContext getAppletContext() {
		    return null;
		}
	        public void appletResize(int a, int b) {
		}
	    });
	    JDialog window = new JDialog();
	    window.getContentPane().add(applet);
	    window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    window.setModal(true);
	    window.setBounds(50, 50, 240, 180);
	    applet.init();
	    applet.start();
	    applet.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
