package su.sergey.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	/**
	 * Constructor for AllTests
	 */
	public AllTests() {}
	
	public static Test suite() {
		TestSuite result = new TestSuite("Мои тесты");
		result.addTestSuite(DAOComplexTest.class);
		result.addTestSuite(FacadeTest.class);
		result.addTestSuite(PersonSearchTest.class);
		return result;
	}
}
