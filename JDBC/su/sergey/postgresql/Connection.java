package su.sergey.postgresql;

import java.sql.CallableStatement;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Map;


public class Connection implements java.sql.Connection {
	private java.sql.Connection nativeConnection;

	/**
	 * Constructor for Connection
	 */
	public Connection(java.sql.Connection nativeConnection) {
		this.nativeConnection = nativeConnection;
	}
	
	/**
	 * @see Connection#createStatement()
	 */
	public Statement createStatement() throws SQLException {
		return nativeConnection.createStatement();
	}

	/**
	 * @see Connection#prepareStatement(String)
	 */
	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		return nativeConnection.prepareStatement(arg0);
	}

	/**
	 * @see Connection#prepareCall(String)
	 */
	public CallableStatement prepareCall(String arg0) throws SQLException {
		return nativeConnection.prepareCall(arg0);
	}

	/**
	 * @see Connection#nativeSQL(String)
	 */
	public String nativeSQL(String arg0) throws SQLException {
		return nativeConnection.nativeSQL(arg0);
	}

	/**
	 * @see Connection#setAutoCommit(boolean)
	 */
	public void setAutoCommit(boolean arg0) throws SQLException {
		nativeConnection.setAutoCommit(arg0);
	}

	/**
	 * @see Connection#getAutoCommit()
	 */
	public boolean getAutoCommit() throws SQLException {
		return nativeConnection.getAutoCommit();
	}

	/**
	 * @see Connection#commit()
	 */
	public void commit() throws SQLException {
		nativeConnection.commit();
	}

	/**
	 * @see Connection#rollback()
	 */
	public void rollback() throws SQLException {
		nativeConnection.rollback();
	}

	/**
	 * @see Connection#close()
	 */
	public void close() throws SQLException {
		nativeConnection.close();
	}

	/**
	 * @see Connection#isClosed()
	 */
	public boolean isClosed() throws SQLException {
		return nativeConnection.isClosed();
	}

	/**
	 * @see Connection#getMetaData()
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		return nativeConnection.getMetaData();
	}

	/**
	 * @see Connection#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean arg0) throws SQLException {
		nativeConnection.setReadOnly(arg0);
	}

	/**
	 * @see Connection#isReadOnly()
	 */
	public boolean isReadOnly() throws SQLException {
		return nativeConnection.isReadOnly();
	}

	/**
	 * @see Connection#setCatalog(String)
	 */
	public void setCatalog(String arg0) throws SQLException {
		nativeConnection.setCatalog(arg0);
	}

	/**
	 * @see Connection#getCatalog()
	 */
	public String getCatalog() throws SQLException {
		return nativeConnection.getCatalog();
	}
	
	/**
	 * @see Connection#setTransactionIsolation(int)
	 */
	public void setTransactionIsolation(int transactionIsolation) throws SQLException {
		switch (transactionIsolation) {
			case TRANSACTION_READ_UNCOMMITTED:
			    nativeConnection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
			    DriverManager.println("Postgresql connection warning: Transaction isolation level changed from " + transactionIsolation + " to " + TRANSACTION_READ_COMMITTED);
			    break;
			case TRANSACTION_REPEATABLE_READ:
			    nativeConnection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
			    DriverManager.println("Postgresql connection warning: Transaction isolation level changed from " + transactionIsolation + " to " + TRANSACTION_SERIALIZABLE);
			    break;
			case TRANSACTION_NONE:
			case TRANSACTION_READ_COMMITTED:
			case TRANSACTION_SERIALIZABLE:
			default:
			    nativeConnection.setTransactionIsolation(transactionIsolation);
			    break;
		}
	}

	/**
	 * @see Connection#getTransactionIsolation()
	 */
	public int getTransactionIsolation() throws SQLException {
		return nativeConnection.getTransactionIsolation();
	}

	/**
	 * @see Connection#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {
		return nativeConnection.getWarnings();
	}

	/**
	 * @see Connection#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
		nativeConnection.clearWarnings();
	}

	/**
	 * @see Connection#createStatement(int, int)
	 */
	public Statement createStatement(int arg0, int arg1) throws SQLException {
		return nativeConnection.createStatement(arg0, arg1);
	}

	/**
	 * @see Connection#prepareStatement(String, int, int)
	 */
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
		return nativeConnection.prepareStatement(arg0, arg1, arg2);
	}

	/**
	 * @see Connection#prepareCall(String, int, int)
	 */
	public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
		return nativeConnection.prepareCall(arg0, arg1, arg2);
	}

	/**
	 * @see Connection#getTypeMap()
	 */
	public Map getTypeMap() throws SQLException {
		return nativeConnection.getTypeMap();
	}

	/**
	 * @see Connection#setTypeMap(Map)
	 */
	public void setTypeMap(Map arg0) throws SQLException {
		nativeConnection.setTypeMap(arg0);
	}
}
