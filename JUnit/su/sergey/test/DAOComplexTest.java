package su.sergey.test;

import java.io.InputStream;
import java.util.Properties;

import junit.framework.TestCase;
import su.sergey.contacts.dao.PersonDAO;
import su.sergey.contacts.dto.PersonData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.PGConnectionSource;

public final class DAOComplexTest extends TestCase {
	private ConnectionSource connectionSource;
	private	Properties properties;

	/**
	 * Constructor for DAOComplexTest
	 */
	public DAOComplexTest(String name) {
		super(name);
	}
	
	public void testPersonDAO() {
		PersonDAO dao = new PersonDAO(connectionSource);
		PersonData data = new PersonData();
		data.setFirst("testPerson");
		data.setLast("testPersonFamily");
		Integer newPersonId = dao.create(data);
		PersonHandle handle = new PersonHandle(newPersonId);
		PersonData receivedData = dao.find(handle);
		assertNotNull("Not null", receivedData);
		assertEquals("Same name", receivedData.getFirst(), "testPerson");
		assertEquals("Same last name", receivedData.getLast(), "testPersonFamily");
		receivedData.setLast("2nd");
		receivedData.setNote("The note");
		dao.update(handle, receivedData);
		data = dao.find(handle);
		assertNotNull("Not null 1", data);
		assertEquals("Same last name 1", data.getLast(), "last");
		assertEquals("Same note", data.getNote(), "The note");
		dao.remove(handle);
		assertNull(dao.find(handle));
	}	

	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
		properties.load(input);
		connectionSource = new PGConnectionSource(properties);
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		connectionSource = null;
		properties = null;
	}
}
