package su.sergey.contacts.inquiry;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.SessionContext;
import su.sergey.contacts.dao.InquiryDAO;
import su.sergey.contacts.dto.InquiryData;
import su.sergey.contacts.dto.InquiryHandle;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjects;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjectsAsCollection;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjectsAsMap;
import su.sergey.contacts.inquiry.valueobjects.impl.DefaultInquiryObject;
import su.sergey.contacts.inquiry.valueobjects.impl.InquiryObjectsAsArrayList;
import su.sergey.contacts.inquiry.valueobjects.impl.InquiryObjectsAsHashMap;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;

public class InquiryDAOFacade extends AbstractDAO {
	private static InquiryDAOFacade instance;
	private static final int MAX_FETCH_RECORDS = 100;
	private InquiryDAO inquiryDAO;

	/**
	 * Constructor for InquiryDAOFacade
	 */
	private InquiryDAOFacade() {
		inquiryDAO = InquiryDAO.getInstance();
	}
	
	public InquiryDAOFacade(ConnectionSource connectionSource) {
		super(connectionSource);
		inquiryDAO = new InquiryDAO(connectionSource);
	}
	
	private InquiryObjectsAsMap inquireTableAsMap(String query) {
		InquiryObjectsAsMap result = new InquiryObjectsAsHashMap();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			int i = 0;
			while (resultSet.next()) {
				int index = 1;
				String id = getString(resultSet, index++);
				String name = getString(resultSet, index++);
				DefaultInquiryObject object = new DefaultInquiryObject(id, name);
				result.put(id, name);
			    if (i++ == MAX_FETCH_RECORDS) {
			    	throw new DAOException("Все записи не могут быть загружены");
			    }
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		return result;
	}
	
	private InquiryObjectsAsCollection inquireTableAsCollection(String query) {
		InquiryObjectsAsCollection result = new InquiryObjectsAsArrayList();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			int i = 0;
			while (resultSet.next()) {
				int index = 1;
				String id = getString(resultSet, index++);
				String name = getString(resultSet, index++);
				DefaultInquiryObject object = new DefaultInquiryObject(id, name);
				result.add(object);
			    if (i++ == MAX_FETCH_RECORDS) {
			    	object.setId(null);
			    	object.setName("Все записи не могут быть отражены...");
			    }
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		return result;
	}
	
	public InquiryObjects inquireTable(String alias, SessionContext context) {		
		InquiryData data = inquiryDAO.find(new InquiryHandle(alias));
		if (data == null) {
			throw new DAOException("Не найдена таблица " + alias + ".");
		}
		String authorizedRole = data.getRole();
		if (authorizedRole != null) {
			if (!context.isCallerInRole(authorizedRole)) {
				throw new SecurityException("Не достаточно прав для доступа к таблице "
				                            + alias
				                            + ". Пользователь "
				                            + context.getCallerPrincipal().getName()
				                            + " должен находиться в роли "
				                            + authorizedRole
				                            + ".");
			}
		}
		String query = data.getQuery();
		InquiryObjects result;
		switch (data.getMode().intValue()) {
			case InquiryModes.COLLECTION:
			    result = inquireTableAsCollection(query);
			    break;
			case InquiryModes.MAP:
			    result = inquireTableAsMap(query);
			    break;
			default:
			    throw new RuntimeException("Неизвестный формат данных справочника");
		}
		return result;
	}
	
	public String[] inquireTableAliases(int scope) {
		Collection aliases = new ArrayList();
		String query = "SELECT alias FROM inquiry WHERE scope = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, scope);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String alias = getString(resultSet, 1);
				aliases.add(alias);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		String result[] = (String[]) aliases.toArray(new String[0]);
		return result;
	}
	
	public String getCurrentDatabase() {
		Connection connection = null;
		DatabaseMetaData metadata = null;
		try {
			connection = getConnection();
			metadata = connection.getMetaData();
			String result = metadata.getURL();
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(connection);
		}
	}
	
	public static InquiryDAOFacade getInstance() {
		if (instance == null) {
			instance = new InquiryDAOFacade();
		}
		return instance;
	}
}
