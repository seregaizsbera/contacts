package su.sergey.contacts.person.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.impl.DefaultPerson2;
import su.sergey.contacts.person.valueobjects.impl.DefaultPersonAttributes;
import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.util.dao.AbstractSQLGenerator;
import su.sergey.contacts.util.dao.AbstractSearchDAO;
import su.sergey.contacts.util.dao.AgregateSQLGenerator;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.util.dao.SQLGenerator;

public class PersonSearchDAO extends AbstractSearchDAO {
	private static PersonSearchDAO instance;
	private static DateFormat dateFormat = new SimpleDateFormat(ContactsDateTimeFormat.DATABASE_DATE_FORMAT);
	
	/**
	 * Constructor for PersonSearchDAO
	 */
	private PersonSearchDAO() {}
	
	public PersonSearchDAO(ConnectionSource connectionSource) {
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
	
	private void makeIcqCondition(AbstractSQLGenerator sql, String icq) {
        if (icq != null) {
            if (needsLikeSearch(icq)) {
            	String likeValue = "upper(" + makeLike(icq) + ")";
                sql.addCondition("((upper(text(icqs.icq)) like " + likeValue + ") or (upper(trim(icqs.nickname)) like " + likeValue + "))");
            } else {
            	String equalValue = makeEqual(icq);
                sql.addCondition("((text(icqs.icq)=" + equalValue + ") or (trim(icqs.nickname)=" + equalValue + "))");
            }
        }
	}
	
	private void makeDateCondition(AbstractSQLGenerator sql, String column, String operator, Date date) {
		if (date != null) {
			sql.addCondition(column + operator + makeDate(date));
		}
	}
	
	private void makeGroupCondition(AbstractSQLGenerator sql, String table, String column, int condition) {
		switch (condition) {
			case PersonSearchParameters.PERSON_IN_GROUP:
			    sql.joinTable("persons", table, "id", column);
			case PersonSearchParameters.GROUP_IGNORE:
			default:
			    break;
		}
	}
	
	private void addCondition(AbstractSQLGenerator sql, PersonSearchParameters searchParameters) {
		if (searchParameters.getBeforeBirthday() != null
		    || searchParameters.getAfterBirthday() != null
		    || searchParameters.getMonthOfBirthday() != 0) {
		    sql.joinTable("persons", "birthdays", "id", "person");
    		makeCondition(sql, "date_part('month', timestamp(birthdays.birthday))", "" + searchParameters.getMonthOfBirthday());
		}
		if (searchParameters.getPhone() != null) {
			sql.joinTable("persons", "person_phones", "id", "person");
			sql.joinTable("person_phones", "phones", "phone", "id");
		}
		if (searchParameters.getIcq() != null) {
			sql.joinTable("persons", "icqs", "id", "person");
		}
		if (searchParameters.getEmail() != null) {
			sql.joinTable("persons", "person_emails", "id", "person");
			sql.joinTable("person_emails", "emails", "email", "id");
		}
		if (searchParameters.getAddress() != null) {
			sql.joinTable("persons", "addresses", "id", "person");
		}
		if (searchParameters.getGender() != null) {
			sql.addCondition("persons", "gender", "=" + searchParameters.getGender());
		}
		makeCondition(sql, "persons.first", searchParameters.getFirstName());
		makeCondition(sql, "persons.last", searchParameters.getLastName());
		makeCondition(sql, "persons.middle", searchParameters.getMiddleName());
        makeDateCondition(sql, "birthdays.birthday", "<=", searchParameters.getBeforeBirthday());
		makeDateCondition(sql, "birthdays.birthday", ">=", searchParameters.getAfterBirthday());
		makeCondition(sql, "addresses.address", searchParameters.getAddress());
		makeCondition(sql, "phones.phone", searchParameters.getPhone());
		makeGroupCondition(sql, "friends", "person", searchParameters.getFriend());
		makeGroupCondition(sql, "shnip", "person", searchParameters.getShnip());
		makeGroupCondition(sql, "msu", "person", searchParameters.getMsu());
		makeGroupCondition(sql, "relatives", "person", searchParameters.getRelated());
		makeGroupCondition(sql, "coworkers", "person", searchParameters.getCoworker());
		makeCondition(sql, "emails.email", searchParameters.getEmail());
		makeIcqCondition(sql, searchParameters.getIcq());
	}
	
	private List find(String query) throws DAOException {
		List result = new ArrayList();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int index = 1;
				PersonHandle handle = new PersonHandle(getInt(resultSet, index++));
				DefaultPersonAttributes attributes = new DefaultPersonAttributes();
				attributes.setFirstName(getString(resultSet, index++));
				attributes.setLastName(getString(resultSet, index++));
				attributes.setMiddleName(getString(resultSet, index++));
				attributes.setNote(getString(resultSet, index++));
				Person2 person = new DefaultPerson2(handle, attributes);
				result.add(person);
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
	
	public List find(PersonSearchParameters searchParameters, long start, long length) throws DAOException {
		SQLGenerator sql = new SQLGenerator();
		sql.init("persons");
		sql.addOut("persons", "id");
		sql.addOut("persons", "first");
		sql.addOut("persons", "last");
		sql.addOut("persons", "middle");
		sql.addOut("persons", "note");
		addCondition(sql, searchParameters);
		sql.addDistinct(true);
		sql.setFirstRecord(start);
		sql.setNumberOfRecords(length);
		sql.setForReadOnly(true);
		String query = sql.getSQL();
		List result = find(query);
		return result;
	}

	public long count(PersonSearchParameters searchParameters) throws DAOException {
		AgregateSQLGenerator sql = new AgregateSQLGenerator();
		sql.init("persons");
		sql.count("persons", "id", true);
		addCondition(sql, searchParameters);
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		long result = 0;
		String query = sql.getSQL();
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				int index = 1;
				result = getLong(resultSet, index++).longValue();
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

	public static PersonSearchDAO getInstance() {
		if (instance == null) {
			instance = new PersonSearchDAO();
		}
		return instance;
	}
}
