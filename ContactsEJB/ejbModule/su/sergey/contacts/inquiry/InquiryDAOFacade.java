package su.sergey.contacts.inquiry;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.inquiry.valueobjects.impl.DefaultInquiryObject;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;

public class InquiryDAOFacade extends AbstractDAO {
	private static InquiryDAOFacade instance;
	private static final int MAX_FETCH_RECORDS = 100;
	private static final String ID_COLUMN = "id";
	private static final String NAME_COLUMN = "name";

	/**
	 * Constructor for InquiryDAOFacade
	 */
	private InquiryDAOFacade() {
	}
	
	private Collection inquireTable(String tableName, String columnToSort) {
		SQLGenerator sql = new SQLGenerator();
		sql.init(tableName);
		sql.addOut(tableName, ID_COLUMN);
		sql.addOut(tableName, NAME_COLUMN);
		if (columnToSort != null) {
    		sql.addOrder(tableName + "." + columnToSort + " asc");
		}
		sql.setNumberOfRecords(MAX_FETCH_RECORDS + 1);
		String query = sql.getSQL();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			int i = 0;
			while (resultSet.next()) {
				int index = 1;
				Integer id = getInt(resultSet, index++);
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
	
	public InquiryObject[] inquireTableAsIds(String tableName) {
		Collection objects = inquireTable(tableName, ID_COLUMN);
		InquiryObject result[] = (InquiryObject[]) objects.toArray(new InquiryObject[0]);
		return result;
	}
	
	public InquiryObject[] inquireTableAsNames(String tableName) {
		Collection objects = inquireTable(tableName, NAME_COLUMN);
		InquiryObject result[] = (InquiryObject[]) objects.toArray(new InquiryObject[0]);
		return result;
	}
	
	public HashMap inquireTableAsHash(String tableName) {
		Collection objects = inquireTable(tableName, null);
		HashMap result = new HashMap();
		for (Iterator i = objects.iterator(); i.hasNext();) {
			InquiryObject object = (InquiryObject) i.next();
			result.put(object.getId(), object.getName());
		}
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
