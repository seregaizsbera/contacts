package su.sergey.test;

import java.io.InputStream;
import java.text.ParseException;
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
	private Properties properties;
	
	/**
	 * Constructor for PersonSearchTest
	 */
	public PersonSearchTest(String name) {
		super(name);
	}
	
	public void test() throws ParseException {
		ConnectionSource connectionSource = new PGConnectionSource(properties);
		PersonSearchDAO dao = new PersonSearchDAO(connectionSource);
		PersonSearchParameters searchParameters = new PersonSearchParameters();
		searchParameters.setAddress("*");
		searchParameters.setPhone("*916*");
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
	
	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
		properties.load(input);
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		properties = null;
	}
}
