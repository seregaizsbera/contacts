package su.sergey.contacts.query.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import su.sergey.contacts.query.valueobjects.QueryRecord;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.query.valueobjects.impl.DefaultQueryMetaData;
import su.sergey.contacts.query.valueobjects.impl.DefaultQueryRecord;
import su.sergey.contacts.query.valueobjects.impl.DefaultQueryResult;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;

public class QueryDAOFacade extends AbstractDAO {
	private static QueryDAOFacade instance;

	/**
	 * Constructor for QueryDAOFacade
	 */
	private QueryDAOFacade() {
	}
	
	private QueryResult makeError(Exception e) {
		DefaultQueryResult result = new DefaultQueryResult();
		DefaultQueryMetaData metaData = new DefaultQueryMetaData();
		String columnNames[] = {"Ошибка " + e.getClass()};
		metaData.setColumnNames(columnNames);
		String messages[] = {e.getMessage()};
		QueryRecord records[] = {new DefaultQueryRecord(messages)};
		result.setMetaData(metaData);
		result.setRecords(records);
		return result;
	}
	
	private QueryResult makeSuccess(int count) {
		DefaultQueryResult result = new DefaultQueryResult();
		DefaultQueryMetaData metaData = new DefaultQueryMetaData();
		String columnNames[] = {"Результат"};
		metaData.setColumnNames(columnNames);
		String messages[] = {"Запрос выполнен успешно. Обновлено " + count + " записей."};
		QueryRecord records[] = {new DefaultQueryRecord(messages)};
		result.setMetaData(metaData);
		result.setRecords(records);
		return result;
	}
	
	private void populateMetaData(ResultSet resultSet, DefaultQueryMetaData metaData) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int numberOfColumns = resultSetMetaData.getColumnCount();
		String columnNames[] = new String[numberOfColumns];
		for (int i = 0; i < numberOfColumns; i++) {
			columnNames[i] = resultSetMetaData.getColumnName(i + 1);
		}
		metaData.setColumnNames(columnNames);
	}
	
	private void populate(ResultSet resultSet, DefaultQueryRecord record) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		int numberOfColumns = resultSetMetaData.getColumnCount();
		String values[] = new String[numberOfColumns];
		for (int i = 0; i < numberOfColumns; i++) {
			values[i] = getString(resultSet, i + 1);			
		}
		record.setValues(values);
	}
	
	public QueryResult performSelect(String query) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		DefaultQueryResult result = new DefaultQueryResult();
		QueryResult finalResult = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			DefaultQueryMetaData metaData = new DefaultQueryMetaData();
			populateMetaData(resultSet, metaData);
			Collection records = new ArrayList();
			while (resultSet.next()) {
				DefaultQueryRecord record = new DefaultQueryRecord();
				int index = 1;
				populate(resultSet, record);
				records.add(record);
			}
			result.setMetaData(metaData);
			result.setRecords((QueryRecord[]) records.toArray(new QueryRecord[0]));
			finalResult = result;
		} catch (SQLException e) {
			finalResult = makeError(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		return finalResult;
	}
	
	public QueryResult performUpdate(String query) {
		Connection connection = null;
		Statement statement = null;
		DefaultQueryResult result = new DefaultQueryResult();
		QueryResult finalResult = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			int count = statement.executeUpdate(query);
			finalResult = makeSuccess(count);
		} catch (SQLException e) {
			finalResult = makeError(e);
		} finally {
			close(statement);
			close(connection);
		}
		return finalResult;
	}
	
	public String[] getLastQueries(String userName, int numberOfQueries) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		String query = "select sql from queries where user_name=? order by id desc limit ?, 0";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			setString(statement, index++, userName);
			setInt(statement, index++, new Integer(numberOfQueries));
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				index = 1;
				String tmp = getString(resultSet, index++);
				result.add(tmp);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		String finalResult[] = (String[]) result.toArray(new String[0]);
		return finalResult;
	}

	public static QueryDAOFacade getInstance() {
		if (instance == null) {
			instance = new QueryDAOFacade();
		}
		return instance;
	}
}
