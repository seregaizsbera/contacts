package su.sergey.contacts.inquiry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class TableNames implements InquiryModes {
	public static String CALL_DIRECTIONS = "call_directions";
	public static String CALL_TYPES = "call_types";
	public static String GPRS_URLS = "gprs_urls";
	public static String MSU_DEPARTMENTS = "msu_departments";
	public static String PHONE_TYPES = "phone_types";
	public static String SHNIPPERS = "shnippers";
	public static String SUPPLY_KINDS = "supply_kinds";
	
	private static Map tableNames;
	private static Map nsiTableNames;
	
	static {
		tableNames = new HashMap();
		tableNames.put(CALL_DIRECTIONS, Boolean.TRUE);
		tableNames.put(CALL_TYPES, Boolean.TRUE);
		tableNames.put(GPRS_URLS, Boolean.TRUE);
		tableNames.put(MSU_DEPARTMENTS, Boolean.TRUE);
		tableNames.put(PHONE_TYPES, Boolean.TRUE);
		tableNames.put(SHNIPPERS, Boolean.TRUE);
		tableNames.put(SUPPLY_KINDS, Boolean.TRUE);
		
		nsiTableNames = new HashMap();
		nsiTableNames.put(CALL_DIRECTIONS, new Integer(ID_SORTED));
		nsiTableNames.put(CALL_TYPES, new Integer(ID_SORTED));
		nsiTableNames.put(GPRS_URLS, new Integer(ID_SORTED));
		nsiTableNames.put(MSU_DEPARTMENTS, new Integer(NAME_SORTED));
		nsiTableNames.put(PHONE_TYPES, new Integer(ID_SORTED | HASH));
		nsiTableNames.put(SUPPLY_KINDS, new Integer(ID_SORTED));
	}
	
	public static Map getTableNames() {
		return Collections.unmodifiableMap(tableNames);
	}
	
	public static Map getNsiTableNames() {
		return Collections.unmodifiableMap(nsiTableNames);
	}
}
