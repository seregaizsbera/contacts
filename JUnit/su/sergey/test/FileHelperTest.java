package su.sergey.test;

import java.io.IOException;

import junit.framework.TestCase;
import su.sergey.contacts.codegen.util.FileHelper;

public class FileHelperTest extends TestCase {
	public FileHelper fileHelper;
	
	public FileHelperTest(String name) {
		super(name);
	}
	
	public void test1() throws IOException {
		String file = fileHelper.prepareFile("", "DAO.java");
		assertEquals(file, "/home/sergey/trash/src/DAO.java");
	}

	public void test2() throws IOException {
		String file = fileHelper.prepareFile("su.sergey.contacts.dao", "DAO.java");
		assertEquals(file, "/home/sergey/trash/src/su/sergey/contacts/dao/DAO.java");
	}
	
	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		fileHelper = new FileHelper("/home/sergey/trash/src");
	}

	/**
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		fileHelper = null;
	}
}
