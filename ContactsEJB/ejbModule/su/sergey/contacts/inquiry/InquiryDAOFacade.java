package su.sergey.contacts.inquiry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.inquiry.valueobjects.impl.DefaultInquiryObject;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;

public class InquiryDAOFacade extends AbstractDAO {
	private static InquiryDAOFacade instance;
	private static final int MAX_FETCH_RECORDS = 100;

	/**
	 * Constructor for InquiryDAOFacade
	 */
	private InquiryDAOFacade() {
	}
	
	public InquiryObject[] inquireTable(String tableName) {
		SQLGenerator sql = new SQLGenerator();
		sql.init(tableName);
		sql.addOut(tableName, "id");
		sql.addOut(tableName, "name");
		sql.addOrder(tableName + ".name asc");
		sql.setNumberOfRecords(MAX_FETCH_RECORDS + 1);
		String query = sql.getSQL();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Collection objects = new ArrayList();
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
				objects.add(object);
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
		InquiryObject result[] = (InquiryObject[]) objects.toArray(new InquiryObject[0]);
		return result;
	}
	
	public static InquiryDAOFacade getInstance() {
		if (instance == null) {
			instance = new InquiryDAOFacade();
		}
		return instance;
	}
}
