package su.sergey.contacts.inquiry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import su.sergey.contacts.dao.MsuDepartmentDAO;
import su.sergey.contacts.dto.MsuDepartmentData;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
import su.sergey.contacts.inquiry.valueobjects.impl.DefaultInquiryObject;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;
import su.sergey.contacts.util.dao.TableOutAccessor;

public class InquiryDAOFacade extends AbstractDAO {
	private static InquiryDAOFacade instance;

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
		String query = sql.getSQL();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Collection objects = new ArrayList();
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int index = 1;
				Integer id = getInt(resultSet, index++);
				String name = getString(resultSet, index++);
				InquiryObject object = new DefaultInquiryObject(id, name);
				objects.add(object);
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
	
	public MsuDepartmentData[] getMsuDepartments() {
		SQLGenerator sql = new SQLGenerator();
		sql.init("msu_departments");
		MsuDepartmentDAO msuDepartmentDao = MsuDepartmentDAO.getInstance();
		msuDepartmentDao.addOuts(new TableOutAccessor("msu_departments", sql));
		Collection departments = new ArrayList();
		String query = sql.getSQL();
		Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				MsuDepartmentData data = new MsuDepartmentData();
				msuDepartmentDao.populate(data, resultSet, 1);
				departments.add(data);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		MsuDepartmentData result[] = (MsuDepartmentData[]) departments.toArray(new MsuDepartmentData[0]);
		return result;
	}
	
	public static InquiryDAOFacade getInstance() {
		if (instance == null) {
			instance = new InquiryDAOFacade();
		}
		return instance;
	}
}
