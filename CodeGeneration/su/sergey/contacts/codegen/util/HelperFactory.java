package su.sergey.contacts.codegen.util;

import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.PGHelper;

public class HelperFactory {
	private static Helper helper;
	
	/**
	 * Gets the helper
	 * @return Returns a Helper
	 */
	public static Helper getHelper() {
		if (helper == null) {
			helper = new PGHelper();
		}
		return helper;
	}
}
