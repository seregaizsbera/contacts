package su.sergey.postgresql;

import java.io.StreamTokenizer;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;

public class Driver implements java.sql.Driver {
	public static final String DRIVER_NAME = "pgsqlbsa";
	private static final String WRAPPED_DRIVER_NAME = "postgresql";
	private java.sql.Driver nativeDriver;
	
	static {
		try {
    		DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor for Driver
	 */
	public Driver() throws SQLException {
		nativeDriver = new org.postgresql.Driver();
	}
	
	private String[] parseUrl(String url) {
		String result[] = {"", "", ""};
		if (url == null) {
			return result;
		}
		StringTokenizer tokenizer = new StringTokenizer(url, ":");
		int index = 0;
		for (int i = 0; i < 2; i++) {
    		if (tokenizer.hasMoreTokens()) {
                result[index++] = tokenizer.nextToken();
    		}
		}
		if (tokenizer.hasMoreTokens()) {
			String lastStr = tokenizer.nextToken("\0");
            lastStr = lastStr.substring(1, lastStr.length());
            result[index++] = lastStr;
		}
		return result;
	}
	
	private String[] accept(String url) {
		String tokens[] = parseUrl(url);
		if (tokens[0].equals("jdbc") && tokens[1].equals(DRIVER_NAME)) {
			return tokens;
		} else {
			return null;
		}
	}
	
	private String makeNewUrl(String oldUrl) {
		String[] tokens = accept(oldUrl);
		if (tokens == null) {
			return null;
		}
		return "jdbc:" + WRAPPED_DRIVER_NAME + ":" + tokens[2];
	}

	/**
	 * @see Driver#connect(String, Properties)
	 */
	public java.sql.Connection connect(String url, Properties properties) throws SQLException {
		String newUrl = makeNewUrl(url);
		if (newUrl == null) {
			return null;
		}
		DriverManager.println("Postgresql connection info: Database url was changed from " + url + " to " + newUrl);
		java.sql.Connection connection = nativeDriver.connect(newUrl, properties);
		if (connection == null) {
			return null;
		}
		java.sql.Connection result = new Connection(connection);
		return result;
	}

	/**
	 * @see Driver#acceptsURL(String)
	 */
	public boolean acceptsURL(String arg0) throws SQLException {
		String newUrl = makeNewUrl(arg0);
		boolean result = newUrl != null && nativeDriver.acceptsURL(newUrl);
		return result;
	}

	/**
	 * @see Driver#getPropertyInfo(String, Properties)
	 */
	public DriverPropertyInfo[] getPropertyInfo(String arg0, Properties arg1) throws SQLException {
		return nativeDriver.getPropertyInfo(arg0, arg1);
	}

	/**
	 * @see Driver#getMajorVersion()
	 */
	public int getMajorVersion() {
		return nativeDriver.getMajorVersion();
	}

	/**
	 * @see Driver#getMinorVersion()
	 */
	public int getMinorVersion() {
		return nativeDriver.getMinorVersion();
	}

	/**
	 * @see Driver#jdbcCompliant()
	 */
	public boolean jdbcCompliant() {
		return nativeDriver.jdbcCompliant();
	}
}
