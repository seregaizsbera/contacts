package su.sergey.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;

public class GenericPooledConnection implements PooledConnection {
	private Connection connection;
	private Set listeners;
	private String databaseName;
	private String description;
	private String userName;
	private String password;

	/**
	 * Constructor for PooledPGConnection
	 */
	public GenericPooledConnection(Connection connection) {
		this.connection = connection;
		listeners = new HashSet();
	}

	/**
	 * @see PooledConnection#addConnectionEventListener(ConnectionEventListener)
	 */
	public void addConnectionEventListener(ConnectionEventListener listener) {
		listeners.add(listener);
	}

	/**
	 * @see PooledConnection#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		return connection;
	}

	/**
	 * @see PooledConnection#removeConnectionEventListener(ConnectionEventListener)
	 */
	public void removeConnectionEventListener(ConnectionEventListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * @see PooledConnection#close()
	 */
	public void close() throws SQLException {
		connection.close();
		for (Iterator i = listeners.iterator(); i.hasNext();) {
			ConnectionEventListener listener = (ConnectionEventListener) i.next();
			listener.connectionClosed(new ConnectionEvent(this));
		}
	}
}
