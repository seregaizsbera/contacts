package su.sergey.contacts.person.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
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
	private PersonDAOFacade daoFacade;
	private RE andRegexp;
	
	private void init() {
		try {
    		andRegexp = new RE(" AND ", RE.MATCH_CASEINDEPENDENT);
		} catch (RESyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor for PersonSearchDAO
	 */
	private PersonSearchDAO() {
		daoFacade = PersonDAOFacade.getInstance();
		init();
	}
	
	public PersonSearchDAO(ConnectionSource connectionSource) {
		super(connectionSource);
		daoFacade = new PersonDAOFacade(connectionSource);
	    init();
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
	
	private void makeNoteCondition(AbstractSQLGenerator sql, PersonSearchParameters searchParameters) {
		String note = searchParameters.getNote();
		if (note == null) {
			return;
		}
		SQLGenerator tmp = new SQLGenerator();
		tmp.init("persons");
		makeCondition(tmp, "persons.note", note);
		makeGroupNoteCondition(tmp, searchParameters.getCoworker(), "coworkers", note);
		makeGroupNoteCondition(tmp, searchParameters.getFriend(), "friends", note);
		makeGroupNoteCondition(tmp, searchParameters.getMsu(), "msu", note);
		makeGroupNoteCondition(tmp, searchParameters.getRelated(), "relatives", note);
		makeGroupNoteCondition(tmp, searchParameters.getShnip(), "shnippers", note);
		String where = tmp.getWhere();
		where = andRegexp.subst(where, " OR ");
		sql.addCondition("(" + where + ")");
	}
	
	private void makeGroupNoteCondition(AbstractSQLGenerator sql, int groupFlag, String table, String text) {
		if (groupFlag == PersonSearchParameters.PERSON_IN_GROUP) {
			makeCondition(sql, table + ".note", text);
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
		if (searchParameters.needBirthdays()) {
		    sql.joinTable("persons", "birthdays", "id", "person");
		}
		if (searchParameters.needPhones()) {
			sql.joinTable("persons", "person_phones", "id", "person");
			sql.joinTable("person_phones", "phones", "phone", "id");
		}
		if (searchParameters.needIcq()) {
			sql.joinTable("persons", "icqs", "id", "person");
		}
		if (searchParameters.needEmails()) {
			sql.joinTable("persons", "person_emails", "id", "person");
			sql.joinTable("person_emails", "emails", "email", "id");
		}
		if (searchParameters.needAddress()) {
			sql.joinTable("persons", "addresses", "id", "person");
		}
		if (searchParameters.getMonthOfBirthday() >= 0) {
      		makeCondition(sql, "date_part('month', timestamp(birthdays.birthday))", "" + searchParameters.getMonthOfBirthday());
		}
		if (searchParameters.getGender() != null) {
			sql.addCondition("persons", "gender", "=" + searchParameters.getGender());
		}
		makeCondition(sql, "persons.first", searchParameters.getFirstName());
		makeCondition(sql, "persons.last", searchParameters.getLastName());
		makeCondition(sql, "persons.middle", searchParameters.getMiddleName());
		makeNoteCondition(sql, searchParameters);
        makeDateCondition(sql, "birthdays.birthyear", "=", searchParameters.getYearOfBirthday());
        makeBirthdayDayCondition(sql, searchParameters);
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

	private void makeBirthdayDayCondition(AbstractSQLGenerator sql, PersonSearchParameters searchParameters) {
		Date before = searchParameters.getBeforeBirthdayDay();
		Date after = searchParameters.getAfterBirthdayDay();
		if (after == null) {
			if (before == null) {
        		return;
			} else {
        		makeDateCondition(sql, "to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy')", "<=", before);
				return;
			}
		} else if (before == null) {
      		makeDateCondition(sql, "to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy')", ">=", after);
       		return;
		}
		if (after.before(before)) {
    		makeDateCondition(sql, "to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy')", "<=", before);
    		makeDateCondition(sql, "to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy')", ">=", after);
		} else {
			String condition = "("
			                   + "to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy') <= " + makeDate(before)
			                   + " or "
			                   + "to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy') >= " + makeDate(after)
			                   + ")";
			sql.addCondition(condition);
		}
	}
	
	private List loadByIds(Collection ids, boolean fullData) {
		List result = new ArrayList();
		for (Iterator i = ids.iterator(); i.hasNext();) {
			Integer id = (Integer) i.next();
			PersonHandle handle = new PersonHandle(id);
			PersonAttributes attributes = daoFacade.findPerson(handle, fullData);
			Person2 person = new Person2(handle, attributes);
			result.add(person);
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
	
	public List find(PersonSearchParameters searchParameters, long start, long length) {
		SQLGenerator sql = new SQLGenerator();
		sql.init("persons");
		sql.addDistinct(true);
		sql.addOut("persons", "id");
		addCondition(sql, searchParameters);
		sql.setFirstRecord(start);
		sql.setNumberOfRecords(length);
		sql.setForReadOnly(true);
		if (searchParameters.isSorted()) {
			sql.addOut("persons", "first");
			sql.addOut("persons", "middle");
			sql.addOut("persons", "last");
			sql.addOrder("persons.last asc");
			sql.addOrder("persons.first asc");
			sql.addOrder("persons.middle asc");
		}
		if (searchParameters.needBirthdaySort()) {
			sql.addOut("to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy')");
			sql.addOrder("to_date(to_char(birthdays.birthday, 'dd.MM.1970'), 'dd.MM.yyyy') asc");
		}
		String query = sql.getSQL();
		List result = find(query, searchParameters.isFullData());
		return result;
	}

	public int count(PersonSearchParameters searchParameters) {
		AgregateSQLGenerator sql = new AgregateSQLGenerator();
		sql.init("persons");
		sql.count("persons", "id", true);
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

	public static PersonSearchDAO getInstance() {
		if (instance == null) {
			instance = new PersonSearchDAO();
		}
		return instance;
	}
}
