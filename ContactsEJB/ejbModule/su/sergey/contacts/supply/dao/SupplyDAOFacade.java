package su.sergey.contacts.supply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import su.sergey.contacts.dao.EmailDAO;
import su.sergey.contacts.dao.PhoneDAO;
import su.sergey.contacts.dao.SupplyDAO;
import su.sergey.contacts.dao.SupplyEmailsDAO;
import su.sergey.contacts.dao.SupplyPhonesDAO;
import su.sergey.contacts.dto.EmailData;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.SupplyCreateInfo;
import su.sergey.contacts.dto.SupplyData;
import su.sergey.contacts.dto.SupplyEmailsData;
import su.sergey.contacts.dto.SupplyHandle;
import su.sergey.contacts.dto.SupplyPhonesData;
import su.sergey.contacts.dto.SupplyUpdateInfo;
import su.sergey.contacts.email.delegate.SupplyEmailDataToEmail;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.phone.delegate.SupplyPhoneDataToPhone;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.supply.valueobjects.SupplyAttributes;
import su.sergey.contacts.supply.valueobjects.delegate.SupplyDataToSupply;
import su.sergey.contacts.supply.valueobjects.delegate.SupplyToSupplyData;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;
import su.sergey.contacts.util.dao.SqlOutAccessor;
import su.sergey.contacts.util.dao.TableOutAccessor;

public class SupplyDAOFacade extends AbstractDAO {
	private static SupplyDAOFacade instance;
	private String phonesQuery;
	private String emailsQuery;

	/**
	 * Constructor for SupplyDAOFacade
	 */
	private SupplyDAOFacade() {
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		SupplyPhonesDAO supplyPhonesDao = SupplyPhonesDAO.getInstance();
		SQLGenerator sql = new SQLGenerator();
		sql.init("supply_phones");
		sql.joinTable("supply_phones", "phones", "phone", "id");
		SqlOutAccessor out = new TableOutAccessor("supply_phones", sql);
		supplyPhonesDao.addOuts(out);
		out = new TableOutAccessor("phones", sql);
		phoneDao.addOuts(out);
		sql.addCondition("supply_phones", "supply", "=?");
		phonesQuery = sql.getSQL();
		
		EmailDAO emailDao = EmailDAO.getInstance();
		SupplyEmailsDAO supplyEmailsDao = SupplyEmailsDAO.getInstance();
		sql.init("supply_emails");
		sql.joinTable("supply_emails", "emails", "email", "id");
		out = new TableOutAccessor("supply_emails", sql);
		supplyEmailsDao.addOuts(out);
		out = new TableOutAccessor("emails", sql);
		emailDao.addOuts(out);
		sql.addCondition("supply_emails", "supply", "=?");
		emailsQuery = sql.getSQL();		
	}

	public Phone2[] getSupplyPhones(SupplyHandle handle) {
		Collection phones = findSupplyPhones(handle, true);
		Phone2 result[] = (Phone2[]) phones.toArray(new Phone2[0]);
		return result;
	}
	
	public Email2[] getSupplyEmails(SupplyHandle handle) {
		Collection emails = findSupplyEmails(handle, true);
		Email2 result[] = (Email2[]) emails.toArray(new Email2[0]);
		return result;
	}
	
	private Collection findSupplyPhones(SupplyHandle handle, boolean withHandle) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		SupplyPhonesDAO supplyPhonesDao = SupplyPhonesDAO.getInstance();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(phonesQuery);
			int index = 1;
			setInt(statement, index++, handle.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				SupplyPhonesData supplyPhonesData = new SupplyPhonesData();
				PhoneData phoneData = new PhoneData();
				index = supplyPhonesDao.populate(supplyPhonesData, resultSet, 1);
				phoneDao.populate(phoneData, resultSet, index);
				PhoneAttributes phone = new SupplyPhoneDataToPhone(phoneData, supplyPhonesData);
				if (!withHandle) {
					result.add(phone);
				} else {
					PhoneHandle phoneHandle = new PhoneHandle(phoneData.getId());
					Phone2 element = new Phone2(phoneHandle, phone);
    				result.add(element);
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
	private Collection findSupplyEmails(SupplyHandle handle, boolean withHandle) {
		SupplyEmailsDAO supplyEmailsDao = SupplyEmailsDAO.getInstance();
		EmailDAO emailDao = EmailDAO.getInstance();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(emailsQuery);
			int index = 1;
			setInt(statement, index++, handle.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				SupplyEmailsData supplyEmailsData = new SupplyEmailsData();
				EmailData emailData = new EmailData();
				index = supplyEmailsDao.populate(supplyEmailsData, resultSet, 1);
				emailDao.populate(emailData, resultSet, index);
				EmailAttributes email = new SupplyEmailDataToEmail(emailData, supplyEmailsData);
				if (!withHandle) {
					result.add(email);
				} else {
					Integer emailId = emailData.getId();
					EmailHandle emailHandle = new EmailHandle(emailId);
					Email2 element = new Email2(emailHandle, email);
    				result.add(element);
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

	public SupplyAttributes findSupply(SupplyHandle handle, boolean fullData) {
		SupplyDAO supplyDao = SupplyDAO.getInstance();
		SupplyData supplyData = supplyDao.find(handle);
		if (supplyData == null) {
			return null;
		}

		Collection phones = null;
		Collection emails = null;
		
		if (fullData) {
			phones = findSupplyPhones(handle, false);
			emails = findSupplyEmails(handle, false);
		}

		SupplyAttributes result = new SupplyDataToSupply(supplyData, phones, emails);
		return result;
	}

	public void updateSupply(SupplyHandle handle, SupplyAttributes attributes) {
		SupplyDAO supplyDao = SupplyDAO.getInstance();
		SupplyUpdateInfo updateInfo = new SupplyToSupplyData(attributes);
		supplyDao.update(handle, updateInfo);
	}

	public SupplyHandle createSupply(SupplyAttributes attributes) {
		SupplyDAO supplyDao = SupplyDAO.getInstance();
		SupplyCreateInfo createInfo = new SupplyToSupplyData(attributes);
		SupplyHandle handle = new SupplyHandle(supplyDao.create(createInfo));
		return handle;
	}

	private void removeSupplyObjects(SupplyHandle handle, String tableName) {
		removeSupplyObjects(handle, tableName, "supply");
	}

	private void removeSupplyObjects(SupplyHandle handle, String tableName, String supplyColumn) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("delete from " + tableName + " where " + supplyColumn + "=?");
			int index = 1;
			setInt(statement, index++, handle.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(statement);
			close(connection);
		}
	}

	public void removeSupply(SupplyHandle handle) {
		Collection phones = findSupplyPhones(handle, true);
		Collection emails = findSupplyEmails(handle, true);
		removeSupplyObjects(handle, "supply_phones");
		removeSupplyObjects(handle, "supply_emails");
		removeSupplyObjects(handle, "supplies", "id");
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		for (Iterator i = phones.iterator(); i.hasNext();) {
			Phone2 phone = (Phone2) i.next();
			phoneDao.remove(phone.getHandle());
		}
		EmailDAO emailDao = EmailDAO.getInstance();
		for (Iterator i = emails.iterator(); i.hasNext();) {
			Email2 email = (Email2) i.next();
			emailDao.remove(email.getHandle());
		}
	}

	public static SupplyDAOFacade getInstance() {
		if (instance == null) {
			instance = new SupplyDAOFacade();
		}
		return instance;
	}
}
