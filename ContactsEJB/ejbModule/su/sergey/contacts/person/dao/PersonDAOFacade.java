package su.sergey.contacts.person.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import su.sergey.contacts.dao.AddressDAO;
import su.sergey.contacts.dao.BirthdayDAO;
import su.sergey.contacts.dao.CoworkerDAO;
import su.sergey.contacts.dao.EmailDAO;
import su.sergey.contacts.dao.FriendDAO;
import su.sergey.contacts.dao.IcqDAO;
import su.sergey.contacts.dao.MsuDAO;
import su.sergey.contacts.dao.PersonDAO;
import su.sergey.contacts.dao.PhoneDAO;
import su.sergey.contacts.dao.RelatedDAO;
import su.sergey.contacts.dao.ShnipDAO;
import su.sergey.contacts.dto.AddressData;
import su.sergey.contacts.dto.AddressHandle;
import su.sergey.contacts.dto.BirthdayData;
import su.sergey.contacts.dto.BirthdayHandle;
import su.sergey.contacts.dto.CoworkerData;
import su.sergey.contacts.dto.CoworkerHandle;
import su.sergey.contacts.dto.EmailData;
import su.sergey.contacts.dto.FriendData;
import su.sergey.contacts.dto.FriendHandle;
import su.sergey.contacts.dto.IcqData;
import su.sergey.contacts.dto.IcqHandle;
import su.sergey.contacts.dto.MsuData;
import su.sergey.contacts.dto.MsuHandle;
import su.sergey.contacts.dto.PersonData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.dto.RelatedData;
import su.sergey.contacts.dto.RelatedHandle;
import su.sergey.contacts.dto.ShnipData;
import su.sergey.contacts.dto.ShnipHandle;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.delegate.PersonDataToPerson;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;

public class PersonDAOFacade extends AbstractDAO {
	private static PersonDAOFacade instance;
	
	private Collection findPersonPhones(PersonHandle handle) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		String query = "select b.id, b.phone, b.type, b.basic from person_phones as a join phones as b on a.phone=b.id where a.person=?";
		PhoneDAO phoneDao = PhoneDAO.getInstance();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			setInt(statement, index++, handle.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PhoneData phone = new PhoneData();
				phoneDao.populate(phone, resultSet, 1);
				result.add(phone);				
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

	private Collection findPersonEmails(PersonHandle handle) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		String query = "select id, person, email, basic from emails where person=?";
		EmailDAO emailDao = EmailDAO.getInstance();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			setInt(statement, index++, handle.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				EmailData email = new EmailData();
				emailDao.populate(email, resultSet, 1);
				result.add(email);				
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

	/**
	 * Constructor for PersonDAOFacade
	 */
	private PersonDAOFacade() {
	}
	
	public PersonAttributes findPerson(PersonHandle handle) {
		PersonDAO personDao = PersonDAO.getInstance();
		IcqDAO icqDao = IcqDAO.getInstance();
		FriendDAO friendDao = FriendDAO.getInstance();
		MsuDAO msuDao = MsuDAO.getInstance();
		RelatedDAO relatedDao = RelatedDAO.getInstance();
		ShnipDAO shnipDao = ShnipDAO.getInstance();
		CoworkerDAO coworkerDao = CoworkerDAO.getInstance();
		AddressDAO addressDao = AddressDAO.getInstance();
		BirthdayDAO birthdayDao = BirthdayDAO.getInstance();
		PersonData personData = personDao.find(handle);
		if (personData == null) {
			return null;
		}
		Collection phones = findPersonPhones(handle);
		Collection emails = findPersonEmails(handle);
		IcqHandle icqHandle = new IcqHandle(handle.getId());
		IcqData icqData = icqDao.find(icqHandle);
		FriendHandle friendHandle = new FriendHandle(handle.getId());
		FriendData friendData = friendDao.find(friendHandle);
		MsuHandle msuHandle = new MsuHandle(handle.getId());
		MsuData msuData = msuDao.find(msuHandle);
		RelatedHandle relatedHandle = new RelatedHandle(handle.getId());
		RelatedData relatedData = relatedDao.find(relatedHandle);
		ShnipHandle shnipHandle = new ShnipHandle(handle.getId());
		ShnipData shnipData = shnipDao.find(shnipHandle);
		CoworkerHandle coworkerHandle = new CoworkerHandle(handle.getId());
		CoworkerData coworkerData = coworkerDao.find(coworkerHandle);
		AddressHandle addressHandle  = new AddressHandle(handle.getId());
		AddressData addressData = addressDao.find(addressHandle);
		BirthdayHandle birthdayHandle = new BirthdayHandle(handle.getId());
		BirthdayData birthdayData = birthdayDao.find(birthdayHandle);
		PersonAttributes result = new PersonDataToPerson(personData,
		                                                           phones,
		                                                           emails,
		                                                           birthdayData,
		                                                           icqData,
		                                                           addressData,
		                                                           friendData,
		                                                           relatedData,
		                                                           shnipData,
		                                                           msuData,
		                                                           coworkerData);
		return result;
	}

	public static PersonDAOFacade getInstance() {
		if (instance == null) {
			instance = new PersonDAOFacade();
		}
		return instance;
	}
}

