package su.sergey.contacts.supply.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.util.dao.AbstractSQLGenerator;
import su.sergey.contacts.util.dao.AbstractSearchDAO;
import su.sergey.contacts.util.dao.AgregateSQLGenerator;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;

public class SupplySearchDAO extends AbstractSearchDAO {
	private static SupplySearchDAO instance;
	
	/**
	 * Constructor for SupplySearchDAO
	 */
	private SupplySearchDAO() {}
	
	public SupplySearchDAO(ConnectionSource connectionSource) {
		super(connectionSource);
	}
	
	private void makeCondition(AbstractSQLGenerator sql, String column, String value) {
        if (value != null) {
            if (needsLikeSearch(value)) {
                sql.addCondition("upper(trim(" + column + ")) like upper(" + makeLike(value) + ")");
            } else {
                sql.addCondition(column + "=" + makeEqual(value));
            }
        }
	}
	
	private void makeCondition(AbstractSQLGenerator sql, String column, Integer value) {
        if (value != null) {
            sql.addCondition(column + "=" + value);
        }
	}
	
	private void addCondition(AbstractSQLGenerator sql, SupplySearchParameters searchParameters) {
		if (searchParameters.needPhones()) {
			sql.joinTable("supplies", "supply_phones", "id", "supply");
			sql.joinTable("supply_phones", "phones", "phone", "id");
		}
		if (searchParameters.needEmails()) {
			sql.joinTable("supplies", "supply_emails", "id", "supply");
			sql.joinTable("supply_emails", "emails", "email", "id");
		}
		if (searchParameters.getImportantOnly()) {
			sql.addCondition("supplies.important");
		}
		makeCondition(sql, "supplies.address", searchParameters.getAddress());
		makeCondition(sql, "emails.email", searchParameters.getEmail());
		makeCondition(sql, "supplies.inn", searchParameters.getInn());
		makeCondition(sql, "supplies.kind", searchParameters.getKind());
		makeCondition(sql, "supplies.name", searchParameters.getName());
		makeCondition(sql, "supplies.note", searchParameters.getNote());
		makeCondition(sql, "supplies.parent_name", searchParameters.getParentName());
		makeCondition(sql, "supplies.short_name", searchParameters.getShortName());
		makeCondition(sql, "phones.phone", searchParameters.getPhone());
		makeCondition(sql, "supplies.url", searchParameters.getUrl());
		makeCondition(sql, "supplies.metro", searchParameters.getMetro());
		makeCondition(sql, "supplies.property_form", searchParameters.getPropertyForm());
	}
	
	private List loadByIds(Collection ids, boolean fullData) {
		SupplyDAOFacade daoFacade = SupplyDAOFacade.getInstance();
		List result = new ArrayList();
		for (Iterator i = ids.iterator(); i.hasNext();) {
			Integer id = (Integer) i.next();
			SupplyHandle handle = new SupplyHandle(id);
			SupplyAttributes attributes = daoFacade.findSupply(handle, fullData);
			Supply2 supply = new Supply2(handle, attributes);
			result.add(supply);
		}
		return result;
	}
	
	private List find(String query, boolean fullData) {
		Collection ids = new ArrayList();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int index = 1;
				Integer id = getInt(resultSet, index++);
				ids.add(id);
			}
			List result = loadByIds(ids, fullData);
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
	}
	
	public List find(SupplySearchParameters searchParameters, long start, long length) {
		SQLGenerator sql = new SQLGenerator();
		sql.init("supplies");
		sql.addDistinct(true);
		sql.addOut("supplies", "id");
		addCondition(sql, searchParameters);
		sql.setFirstRecord(start);
		sql.setNumberOfRecords(length);
		sql.setForReadOnly(true);
		if (searchParameters.isSorted()) {
			sql.addOut("supplies", "name");
			sql.addOut("supplies", "parent_name");
			sql.addOut("supplies", "important");
			sql.addOrder("supplies.important desc");
			sql.addOrder("supplies.parent_name asc");
			sql.addOrder("supplies.name asc");
		}
		String query = sql.getSQL();
		List result = find(query, searchParameters.isFullData());
		return result;
	}

	public int count(SupplySearchParameters searchParameters) {
		AgregateSQLGenerator sql = new AgregateSQLGenerator();
		sql.init("supplies");
		sql.count("supplies", "id", true);
		addCondition(sql, searchParameters);
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		int result = 0;
		String query = sql.getSQL();
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				int index = 1;
				result = getInt(resultSet, index++).intValue();
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

	public static SupplySearchDAO getInstance() {
		if (instance == null) {
			instance = new SupplySearchDAO();
		}
		return instance;
	}
}
