package su.sergey.test;

import javax.naming.Context;
import junit.framework.TestCase;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.report.ReportException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.sessionfacade.businessdelegate.impl.DefaultDAOBusinessDelegate;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.util.ServiceUtil;

public class ReportTest extends TestCase {
	private DAOBusinessDelegate facade;

	/**
	 * Constructor for ReportTest
	 */
	public ReportTest(String name) {
		super(name);
	}
	
	public void testPersons() throws ReportException {
		PersonSearchParameters searchParameters = new PersonSearchParameters();
		searchParameters.setFullData(true);
		facade.buildPersonReport(searchParameters, "Все люди");
	}
	
	public void testPersonsB() throws ReportException {
		PersonSearchParameters searchParameters = new PersonSearchParameters();
		searchParameters.setLastName("Б*");
		searchParameters.setFullData(true);
		facade.buildPersonReport(searchParameters, "У кого фамилии на букву Б");
	}
	
	public void testSupplies() throws ReportException {
		SupplySearchParameters searchParameters = new SupplySearchParameters();
		searchParameters.setFullData(true);
		facade.buildSupplyReport(searchParameters, "Все организации");
	}
	
	/**
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		ServiceUtil.login("sergey", "changeitxxx");
		System.setProperty(Context.PROVIDER_URL, "iiop://localhost:2809");
		facade = new DefaultDAOBusinessDelegate(JNDINames.DAO_SESSION_FACADE_BEAN);
	}
}
