package su.sergey.test;

import java.util.Properties;

import junit.framework.TestCase;
import su.sergey.contacts.dao.PersonDAO;
import su.sergey.contacts.dto.PersonData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.PGConnectionSource;

public final class DAOComplexTest extends TestCase {
	private ConnectionSource connectionSource;

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
		data.setSecond("testPersonFamily");
		Integer newPersonId = dao.create(data);
		PersonHandle handle = new PersonHandle(newPersonId);
		PersonData receivedData = dao.find(handle);
		assertNotNull("Not null", receivedData);
		assertEquals("Same name", receivedData.getFirst(), "testPerson");
		assertEquals("Same second name", receivedData.getSecond(), "testPersonFamily");
		receivedData.setSecond("2nd");
		receivedData.setNote("The note");
		dao.update(handle, receivedData);
		data = dao.find(handle);
		assertNotNull("Not null 1", data);
		assertEquals("Same second name 1", data.getSecond(), "2nd");
		assertEquals("Same note", data.getNote(), "The note");
		dao.remove(handle);
		assertNull(dao.find(handle));
	}	

	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		Properties properties = new Properties();
		properties.put(PGConnectionSource.PN_DB_NAME, "contacts");
		properties.put(PGConnectionSource.PN_USER_LOGIN, "j2eeagent");
		properties.put(PGConnectionSource.PN_USER_PASSWORD, "j2ee");
		connectionSource = new PGConnectionSource(properties);
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		connectionSource = null;
	}
}
