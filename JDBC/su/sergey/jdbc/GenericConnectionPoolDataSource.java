package su.sergey.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

public class GenericConnectionPoolDataSource implements ConnectionPoolDataSource {
	private String databaseName;
	private String description;
	private String user;
	private String password;
	private String driverClass;
	private String urlPrefix;
	
	/**
	 * @see ConnectionPoolDataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() throws SQLException {
		return DriverManager.getLogWriter();
	}

	/**
	 * @see ConnectionPoolDataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() throws SQLException {
		return DriverManager.getLoginTimeout();
	}
	
	private String makeUrl() {
		String url = (urlPrefix == null) ? databaseName : urlPrefix + ":" + databaseName;
		return url;
	}

	/**
	 * @see ConnectionPoolDataSource#getPooledConnection()
	 */
	public PooledConnection getPooledConnection() throws SQLException {
		return new GenericPooledConnection(DriverManager.getConnection(makeUrl(), user, password));
	}

	/**
	 * @see ConnectionPoolDataSource#getPooledConnection(String, String)
	 */
	public PooledConnection getPooledConnection(String theUserName, String thePassword) throws SQLException {
		return new GenericPooledConnection(DriverManager.getConnection(makeUrl(), theUserName, thePassword));
	}

	/**
	 * @see ConnectionPoolDataSource#setLogWriter(PrintWriter)
	 */
	public void setLogWriter(PrintWriter logWriter) throws SQLException {
		DriverManager.setLogWriter(logWriter);
	}

	/**
	 * @see ConnectionPoolDataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int loginTimeout) throws SQLException {
		DriverManager.setLoginTimeout(loginTimeout);
	}
	
	/**
	 * Gets the user
	 * @return Returns a String
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Sets the user
	 * @param user The user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the password
	 * @return Returns a String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the databaseName
	 * @return Returns a String
	 */
	public String getDatabaseName() {
		return databaseName;
	}
	
	/**
	 * Sets the databaseName
	 * @param databaseName The databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	/**
	 * Gets the description
	 * @return Returns a String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the driverClass
	 * @return Returns a String
	 */
	public String getDriverClass() {
		return driverClass;
	}
	
	/**
	 * Sets the driverClass
	 * @param driverClass The driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
		try {
    		Class.forName(this.driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the urlPrefix
	 * @return Returns a String
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}
	
	/**
	 * Sets the urlPrefix
	 * @param urlPrefix The urlPrefix to set
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
}
