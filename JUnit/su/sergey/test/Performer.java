package su.sergey.test;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

public final class Performer {
	public static void main(String[] args) {
		TestSuite suite = new TestSuite("Мои тесты");
		suite.addTestSuite(FileHelperTest.class);
		TestRunner.run(suite);
	}
}

