package su.sergey.contacts.query;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.query.valueobjects.QueryResult;

/**
 * Remote interface for Enterprise Bean: Query
 */
public interface Query extends EJBObject {
	String[] getLastQueries(int maxNumberOfQueries, String userName) throws RemoteException;
	
	QueryResult performQuery(String sql, String userName) throws RemoteException;
	
	void addQuery(String sql, String userName) throws RemoteException;
}

