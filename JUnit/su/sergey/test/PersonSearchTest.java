package su.sergey.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.person.dao.PersonSearchDAO;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.PGConnectionSource;
import su.sergey.contacts.util.dao.SQLGenerator;

public class PersonSearchTest extends TestCase {
	/**
	 * Constructor for PersonSearchTest
	 */
	public PersonSearchTest(String name) {
		super(name);
	}
	
	public void test() throws ParseException {
		Properties properties = new Properties();
		properties.setProperty(PGConnectionSource.PN_DB_NAME, "contacts");
		properties.setProperty(PGConnectionSource.PN_USER_LOGIN, "j2eeagent");
		properties.setProperty(PGConnectionSource.PN_USER_PASSWORD, "j2ee");
		ConnectionSource connectionSource = new PGConnectionSource(properties);
		PersonSearchDAO dao = new PersonSearchDAO(connectionSource);
		PersonSearchParameters searchParameters = new PersonSearchParameters();
		searchParameters.setAddress("*");
		searchParameters.setAfterBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1900-12-31"));
		searchParameters.setBeforeBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-31"));
		searchParameters.setPhone("*902*");
		searchParameters.setFirstName("á*");
		searchParameters.setLastName("*");
		searchParameters.setMsu(PersonSearchParameters.PERSON_IN_GROUP);
		long count = dao.count(searchParameters);
        List persons = dao.find(searchParameters, 1, 20);
		for (Iterator i = persons.iterator(); i.hasNext();) {
			Person2 person = (Person2) i.next();
			PersonHandle handle = person.getHandle();
			PersonAttributes attributes = person.getAttributes();
			System.err.println(handle.getId() + ": " + attributes.getFirstName() + " " + attributes.getLastName());
		}
		persons = dao.find(searchParameters, 1, SQLGenerator.ALL_RECORDS);
		assertEquals(count, persons.size());
	}
}
