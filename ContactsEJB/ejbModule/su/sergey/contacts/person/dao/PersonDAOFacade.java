package su.sergey.contacts.person.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import su.sergey.contacts.dao.AddressDAO;
import su.sergey.contacts.dao.BirthdayDAO;
import su.sergey.contacts.dao.CoworkerDAO;
import su.sergey.contacts.dao.EmailDAO;
import su.sergey.contacts.dao.FriendDAO;
import su.sergey.contacts.dao.IcqDAO;
import su.sergey.contacts.dao.MsuDAO;
import su.sergey.contacts.dao.PersonDAO;
import su.sergey.contacts.dao.PersonEmailsDAO;
import su.sergey.contacts.dao.PersonPhonesDAO;
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
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.dto.FriendData;
import su.sergey.contacts.dto.FriendHandle;
import su.sergey.contacts.dto.IcqData;
import su.sergey.contacts.dto.IcqHandle;
import su.sergey.contacts.dto.MsuData;
import su.sergey.contacts.dto.MsuHandle;
import su.sergey.contacts.dto.PersonCreateInfo;
import su.sergey.contacts.dto.PersonData;
import su.sergey.contacts.dto.PersonEmailsData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.PersonPhonesData;
import su.sergey.contacts.dto.PersonUpdateInfo;
import su.sergey.contacts.dto.PhoneData;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.dto.RelatedData;
import su.sergey.contacts.dto.RelatedHandle;
import su.sergey.contacts.dto.ShnipData;
import su.sergey.contacts.dto.ShnipHandle;
import su.sergey.contacts.email.delegate.PersonEmailDataToEmail;
import su.sergey.contacts.email.valueobjects.Email2;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.email.valueobjects.impl.DefaultEmail2;
import su.sergey.contacts.person.valueobjects.Coworker;
import su.sergey.contacts.person.valueobjects.Friend;
import su.sergey.contacts.person.valueobjects.Icq;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.Related;
import su.sergey.contacts.person.valueobjects.Shnip;
import su.sergey.contacts.person.valueobjects.delegate.CoworkerToCoworkerData;
import su.sergey.contacts.person.valueobjects.delegate.FriendToFriendData;
import su.sergey.contacts.person.valueobjects.delegate.IcqToIcqData;
import su.sergey.contacts.person.valueobjects.delegate.MsuToMsuData;
import su.sergey.contacts.person.valueobjects.delegate.PersonDataToPerson;
import su.sergey.contacts.person.valueobjects.delegate.PersonToPersonData;
import su.sergey.contacts.person.valueobjects.delegate.RelatedToRelatedData;
import su.sergey.contacts.person.valueobjects.delegate.ShnipToShnipData;
import su.sergey.contacts.phone.delegate.PersonPhoneDataToPhone;
import su.sergey.contacts.phone.valueobjects.Phone2;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.phone.valueobjects.impl.DefaultPhone2;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.AgregateSQLGenerator;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;
import su.sergey.contacts.util.dao.SqlOutAccessor;
import su.sergey.contacts.util.dao.TableOutAccessor;

public class PersonDAOFacade extends AbstractDAO {
	private static PersonDAOFacade instance;
	private String phonesQuery;
	private String emailsQuery;
	private String numberOfPhonesQuery;
	private String numberOfEmailsQuery;
	private final AddressDAO addressDao;
	private final BirthdayDAO birthdayDao;
	private final CoworkerDAO coworkerDao;
	private final EmailDAO emailDao;
	private final FriendDAO friendDao;
	private final IcqDAO icqDao;
	private final MsuDAO msuDao;
	private final PersonDAO personDao;
	private final PersonEmailsDAO personEmailsDao;
	private final PersonPhonesDAO personPhonesDao;
	private final PhoneDAO phoneDao;
	private final RelatedDAO relatedDao;
	private final ShnipDAO shnipDao;

	/**
	 * Constructor for PersonDAOFacade
	 */
	private PersonDAOFacade() {
		personDao = PersonDAO.getInstance();
		icqDao = IcqDAO.getInstance();
		friendDao = FriendDAO.getInstance();
		msuDao = MsuDAO.getInstance();
		relatedDao = RelatedDAO.getInstance();
		shnipDao = ShnipDAO.getInstance();
		coworkerDao = CoworkerDAO.getInstance();
		addressDao = AddressDAO.getInstance();
		birthdayDao = BirthdayDAO.getInstance();
		phoneDao = PhoneDAO.getInstance();
		personPhonesDao = PersonPhonesDAO.getInstance();
		emailDao = EmailDAO.getInstance();
		personEmailsDao = PersonEmailsDAO.getInstance();		
		init();
	}

	/**
	 * Constructor for PersonDAOFacade
	 */
	public PersonDAOFacade(ConnectionSource connectionSource) {
		super(connectionSource);
		personDao = new PersonDAO(connectionSource);
		icqDao = new IcqDAO(connectionSource);
		friendDao = new FriendDAO(connectionSource);
		msuDao = new MsuDAO(connectionSource);
		relatedDao = new RelatedDAO(connectionSource);
		shnipDao = new ShnipDAO(connectionSource);
		coworkerDao = new CoworkerDAO(connectionSource);
		addressDao = new AddressDAO(connectionSource);
		birthdayDao = new BirthdayDAO(connectionSource);
		phoneDao = new PhoneDAO(connectionSource);
		personPhonesDao = new PersonPhonesDAO(connectionSource);
		emailDao = new EmailDAO(connectionSource);
		personEmailsDao = new PersonEmailsDAO(connectionSource);		
		init();
	}
	
	private void init() {
		SQLGenerator sql = new SQLGenerator();
		sql.init("person_phones");
		sql.joinTable("person_phones", "phones", "phone", "id");
		SqlOutAccessor out = new TableOutAccessor("person_phones", sql);
		personPhonesDao.addOuts(out);
		out = new TableOutAccessor("phones", sql);
		phoneDao.addOuts(out);
		sql.addCondition("person_phones", "person", "=?");
		sql.addOrder("person_phones.basic desc");
		phonesQuery = sql.getSQL();
		
		sql.init("person_emails");
		sql.joinTable("person_emails", "emails", "email", "id");
		out = new TableOutAccessor("person_emails", sql);
		personEmailsDao.addOuts(out);
		out = new TableOutAccessor("emails", sql);
		emailDao.addOuts(out);
		sql.addCondition("person_emails", "person", "=?");
		sql.addOrder("person_emails.basic desc");
		emailsQuery = sql.getSQL();		
		
		AgregateSQLGenerator countSql = new AgregateSQLGenerator();
		
		countSql.init("person_phones");
		countSql.count("person_phones", "phone", false);
		countSql.addCondition("person_phones", "person", "=?");
		numberOfPhonesQuery = countSql.getSQL();
		
		countSql.init("person_emails");
		countSql.count("person_emails", "email", false);
		countSql.addCondition("person_emails", "person", "=?");
		numberOfEmailsQuery = countSql.getSQL();
	}

	public Phone2[] getPersonPhones(PersonHandle handle) {
		Collection phones = findPersonPhones(handle, true);
		Phone2 result[] = (Phone2[]) phones.toArray(new Phone2[0]);
		return result;
	}
	
	public boolean hasPhones(PersonHandle handle) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(numberOfPhonesQuery);
			int index = 1;
			setInt(statement, index, handle.getId());
			resultSet = statement.executeQuery();
			resultSet.next();
			index = 1;
			int numberOfPhones = resultSet.getInt(index++);
			boolean result = numberOfPhones != 0;
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
	}
	
	public boolean hasEmails(PersonHandle handle) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(numberOfEmailsQuery);
			int index = 1;
			setInt(statement, index, handle.getId());
			resultSet = statement.executeQuery();
			resultSet.next();
			index = 1;
			int numberOfEmails = resultSet.getInt(index++);
			boolean result = numberOfEmails != 0;
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
	}
	
	public Email2[] getPersonEmails(PersonHandle handle) {
		Collection emails = findPersonEmails(handle, true);
		Email2 result[] = (Email2[]) emails.toArray(new Email2[0]);
		return result;
	}
	
	private Collection findPersonPhones(PersonHandle handle, boolean withHandle) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Collection result = new ArrayList();
		try {
			connection = getConnection();
			statement = connection.prepareStatement(phonesQuery);
			int index = 1;
			setInt(statement, index++, handle.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PersonPhonesData personPhonesData = new PersonPhonesData();
				PhoneData phoneData = new PhoneData();
				index = personPhonesDao.populate(personPhonesData, resultSet, 1);
				phoneDao.populate(phoneData, resultSet, index);
				PhoneAttributes phone = new PersonPhoneDataToPhone(phoneData, personPhonesData);
				if (!withHandle) {
					result.add(phone);
				} else {
					PhoneHandle phoneHandle = new PhoneHandle(phoneData.getId());
					DefaultPhone2 element = new DefaultPhone2();
					element.setHandle(phoneHandle);
					element.setAttributes(phone);
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

	public void setBasicPhone(PersonHandle personHandle, PhoneHandle phoneHandle) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query1 = "update person_phones set basic=? where person=?";
		String query2 = "update person_phones set basic=? where person=? and phone=?";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query1);
			int index = 1;
			setBoolean(statement, index++, Boolean.FALSE);
			setInt(statement, index++, personHandle.getId());
			statement.executeUpdate();
			statement.close();
			statement = connection.prepareStatement(query2);
			index = 1;
			setBoolean(statement, index++, Boolean.TRUE);
			setInt(statement, index++, personHandle.getId());
			setInt(statement, index++, phoneHandle.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(statement);
			close(connection);
		}
	}
	
	public void setBasicEmail(PersonHandle personHandle, EmailHandle emailHandle) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query1 = "update person_emails set basic=? where person=?";
		String query2 = "update person_emails set basic=? where person=? and email=?";
		try {
			connection = getConnection();
			statement = connection.prepareStatement(query1);
			int index = 1;
			setBoolean(statement, index++, Boolean.FALSE);
			setInt(statement, index++, personHandle.getId());
			statement.executeUpdate();
			statement.close();
			statement = connection.prepareStatement(query2);
			index = 1;
			setBoolean(statement, index++, Boolean.TRUE);
			setInt(statement, index++, personHandle.getId());
			setInt(statement, index++, emailHandle.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(statement);
			close(connection);
		}
	}

	private Collection findPersonEmails(PersonHandle handle, boolean withHandle) {
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
				PersonEmailsData personEmailsData = new PersonEmailsData();
				EmailData emailData = new EmailData();
				index = personEmailsDao.populate(personEmailsData, resultSet, 1);
				emailDao.populate(emailData, resultSet, index);
				EmailAttributes email = new PersonEmailDataToEmail(emailData, personEmailsData);
				if (!withHandle) {
					result.add(email);
				} else {
					Integer emailId = emailData.getId();
					EmailHandle emailHandle = new EmailHandle(emailId);
					DefaultEmail2 element = new DefaultEmail2();
					element.setAttributes(email);
					element.setHandle(emailHandle);
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

	public PersonAttributes findPerson(PersonHandle handle, boolean fullData) {
		PersonData personData = personDao.find(handle);
		if (personData == null) {
			return null;
		}

		Collection phones = null;
		Collection emails = null;
		IcqData icqData = null;
		FriendData friendData = null;
		MsuData msuData = null;
		RelatedData relatedData = null;
		ShnipData shnipData = null;
		CoworkerData coworkerData = null;
		AddressData addressData = null;
		BirthdayData birthdayData = null;
		
		if (fullData) {
			phones = findPersonPhones(handle, false);
			emails = findPersonEmails(handle, false);
			
			IcqHandle icqHandle = new IcqHandle(handle.getId());
			icqData = icqDao.find(icqHandle);
			
			FriendHandle friendHandle = new FriendHandle(handle.getId());
			friendData = friendDao.find(friendHandle);
			
			MsuHandle msuHandle = new MsuHandle(handle.getId());
			msuData = msuDao.find(msuHandle);
			
			RelatedHandle relatedHandle = new RelatedHandle(handle.getId());
			relatedData = relatedDao.find(relatedHandle);
			
			ShnipHandle shnipHandle = new ShnipHandle(handle.getId());
			shnipData = shnipDao.find(shnipHandle);
			
			CoworkerHandle coworkerHandle = new CoworkerHandle(handle.getId());
			coworkerData = coworkerDao.find(coworkerHandle);
			
			AddressHandle addressHandle = new AddressHandle(handle.getId());
			addressData = addressDao.find(addressHandle);
			
			BirthdayHandle birthdayHandle = new BirthdayHandle(handle.getId());
			birthdayData = birthdayDao.find(birthdayHandle);
		}

		PersonAttributes result =
			new PersonDataToPerson(personData, phones, emails, birthdayData, icqData, addressData, friendData, relatedData, shnipData, msuData, coworkerData);
		return result;
	}

	public void updatePerson(PersonHandle handle, PersonAttributes attributes) {
		updateCoworkerInfo(handle, attributes.getCoworkerInfo());
		updateShnipInfo(handle, attributes.getShnipInfo());
		updateFriendInfo(handle, attributes.getFriendInfo());
		updateRelatedInfo(handle, attributes.getRelatedInfo());
		updateMsuInfo(handle, attributes.getMsuInfo());
		updateIcq(handle, attributes.getIcq());
		updateBirthday(handle, attributes.getBirthday(), attributes.getBirthYear());
		updateAddress(handle, attributes.getAddress());
		PersonUpdateInfo updateInfo = new PersonToPersonData(attributes);
		personDao.update(handle, updateInfo);
	}

	public PersonHandle createPerson(PersonAttributes attributes) {
		PersonCreateInfo createInfo = new PersonToPersonData(attributes);
		PersonHandle handle = new PersonHandle(personDao.create(createInfo));
		updateCoworkerInfo(handle, attributes.getCoworkerInfo());
		updateShnipInfo(handle, attributes.getShnipInfo());
		updateFriendInfo(handle, attributes.getFriendInfo());
		updateRelatedInfo(handle, attributes.getRelatedInfo());
		updateMsuInfo(handle, attributes.getMsuInfo());
		updateIcq(handle, attributes.getIcq());
		updateBirthday(handle, attributes.getBirthday(), attributes.getBirthYear());
		updateAddress(handle, attributes.getAddress());
		return handle;
	}

	private void updateAddress(PersonHandle handle, String address) {
		AddressHandle addressHandle = new AddressHandle(handle.getId());
		boolean wasAddress = addressDao.find(addressHandle) != null;
		boolean isAddress = address != null;
		if (isAddress) {
			AddressData addressData = new AddressData();
			addressData.setPerson(handle.getId());
			addressData.setAddress(address);
			if (wasAddress) {
				addressDao.update(addressHandle, addressData);
			} else {
				addressDao.create(addressData);
			}
		} else if (wasAddress) {
			addressDao.remove(addressHandle);
		}
	}

	private void updateIcq(PersonHandle handle, Icq icq) {
		IcqHandle icqHandle = new IcqHandle(handle.getId());
		boolean wasIcq = icqDao.find(icqHandle) != null;
		boolean isIcq = icq != null;
		if (isIcq) {
			IcqToIcqData icqData = new IcqToIcqData(icqHandle, icq);
			if (wasIcq) {
				icqDao.update(icqHandle, icqData);
			} else {
				icqDao.create(icqData);
			}
		} else if (wasIcq) {
			icqDao.remove(icqHandle);
		}
	}

	private void updateBirthday(PersonHandle handle, Date birthday, Date birthYear) {
		BirthdayHandle birthdayHandle = new BirthdayHandle(handle.getId());
		boolean wasBirthday = birthdayDao.find(birthdayHandle) != null;
		boolean isBirthday = birthday != null;
		if (isBirthday) {
			BirthdayData birthdayData = new BirthdayData();
			birthdayData.setPerson(handle.getId());
			birthdayData.setBirthday(birthday);
			birthdayData.setBirthyear(birthYear);
			if (wasBirthday) {
				birthdayDao.update(birthdayHandle, birthdayData);
			} else {
				birthdayDao.create(birthdayData);
			}
		} else if (wasBirthday) {
			birthdayDao.remove(birthdayHandle);
		}
	}

	private void updateMsuInfo(PersonHandle handle, Msu msuInfo) {
		MsuHandle msuHandle = new MsuHandle(handle.getId());
		boolean wasMsu = msuDao.find(msuHandle) != null;
		boolean isMsu = msuInfo != null;
		if (isMsu) {
			MsuToMsuData msuData = new MsuToMsuData(msuHandle, msuInfo);
			if (wasMsu) {
				msuDao.update(msuHandle, msuData);
			} else {
				msuDao.create(msuData);
			}
		} else if (wasMsu) {
			msuDao.remove(msuHandle);
		}
	}

	private void updateRelatedInfo(PersonHandle handle, Related relatedInfo) {
		RelatedHandle relatedHandle = new RelatedHandle(handle.getId());
		boolean wasRelated = relatedDao.find(relatedHandle) != null;
		boolean isRelated = relatedInfo != null;
		if (isRelated) {
			RelatedToRelatedData relatedData = new RelatedToRelatedData(relatedHandle, relatedInfo);
			if (wasRelated) {
				relatedDao.update(relatedHandle, relatedData);
			} else {
				relatedDao.create(relatedData);
			}
		} else if (wasRelated) {
			relatedDao.remove(relatedHandle);
		}
	}

	private void updateFriendInfo(PersonHandle handle, Friend friendInfo) {
		FriendHandle friendHandle = new FriendHandle(handle.getId());
		boolean wasFriend = friendDao.find(friendHandle) != null;
		boolean isFriend = friendInfo != null;
		if (isFriend) {
			FriendToFriendData friendData = new FriendToFriendData(friendHandle, friendInfo);
			if (wasFriend) {
				friendDao.update(friendHandle, friendData);
			} else {
				friendDao.create(friendData);
			}
		} else if (wasFriend) {
			friendDao.remove(friendHandle);
		}
	}

	private void updateShnipInfo(PersonHandle handle, Shnip shnipInfo) {
		ShnipHandle shnipHandle = new ShnipHandle(handle.getId());
		boolean wasShnip = shnipDao.find(shnipHandle) != null;
		boolean isShnip = shnipInfo != null;
		if (isShnip) {
			ShnipToShnipData shnipData = new ShnipToShnipData(shnipHandle, shnipInfo);
			if (wasShnip) {
				shnipDao.update(shnipHandle, shnipData);
			} else {
				shnipDao.create(shnipData);
			}
		} else if (wasShnip) {
			shnipDao.remove(shnipHandle);
		}
	}

	private void updateCoworkerInfo(PersonHandle handle, Coworker coworkerInfo) {
		CoworkerHandle coworkerHandle = new CoworkerHandle(handle.getId());
		boolean wasCoworker = coworkerDao.find(coworkerHandle) != null;
		boolean isCoworker = coworkerInfo != null;
		if (isCoworker) {
			CoworkerToCoworkerData coworkerData = new CoworkerToCoworkerData(coworkerHandle, coworkerInfo);
			if (wasCoworker) {
				coworkerDao.update(coworkerHandle, coworkerData);
			} else {
				coworkerDao.create(coworkerData);
			}
		} else if (wasCoworker) {
			coworkerDao.remove(coworkerHandle);
		}
	}

	private void removePersonObjects(PersonHandle handle, String tableName) {
		removePersonObjects(handle, tableName, "person");
	}

	private void removePersonObjects(PersonHandle handle, String tableName, String personColumn) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement("delete from " + tableName + " where " + personColumn + "=?");
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

	public void removePerson(PersonHandle handle) {
		Collection phones = findPersonPhones(handle, true);
		Collection emails = findPersonEmails(handle, true);

		removePersonObjects(handle, "person_phones");
		removePersonObjects(handle, "person_emails");
		removePersonObjects(handle, "addresses");
		removePersonObjects(handle, "icqs");
		removePersonObjects(handle, "shnip");
		removePersonObjects(handle, "msu");
		removePersonObjects(handle, "coworkers");
		removePersonObjects(handle, "relatives");
		removePersonObjects(handle, "friends");
		removePersonObjects(handle, "birthdays");
		removePersonObjects(handle, "persons", "id");

		for (Iterator i = phones.iterator(); i.hasNext();) {
			Phone2 phone = (Phone2) i.next();
			phoneDao.remove(phone.getHandle());
		}
		
		for (Iterator i = emails.iterator(); i.hasNext();) {
			Email2 email = (Email2) i.next();
			emailDao.remove(email.getHandle());
		}
	}

	public static PersonDAOFacade getInstance() {
		if (instance == null) {
			instance = new PersonDAOFacade();
		}
		return instance;
	}
}
