package su.sergey.contacts.directory.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import su.sergey.contacts.util.dao.AbstractDAO;

public class NSIDAOFacade extends AbstractDAO {
	private static NSIDAOFacade instance;

	/**
	 * Constructor for NSIDAOFacade
	 */
	protected NSIDAOFacade() {
	}
	
	public Properties getPhoneTypes() {
		Connection connection = null;
		Statement statement = null;
		return null;
	}
	
	public static NSIDAOFacade getInstance() {
		if (instance == null) {
			instance = new NSIDAOFacade();
		}
		return instance;
	}
}
