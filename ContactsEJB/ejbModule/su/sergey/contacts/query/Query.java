package su.sergey.contacts.query;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;
import su.sergey.contacts.query.valueobjects.QueryResult;

/**
 * Remote interface for Enterprise Bean: Query
 */
public interface Query extends EJBObject {
	QueryResult performQuery(String sql) throws RemoteException;
}

