package su.sergey.contacts;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.EJBObject;

/**
 * Remote interface for Enterprise Bean: Test
 */
public interface Test extends EJBObject {

	int getSeventeen() throws RemoteException;
	
	Properties getProperties() throws RemoteException;
}
