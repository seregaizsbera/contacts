package su.sergey.contacts.person;

import java.util.Collection;

import su.sergey.contacts.person.dao.PersonSearchDAO;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.report.AbstractCollectionReportBuilder;
import su.sergey.contacts.report.valueobjects.ReportConfig;

public class PersonReportBuilder extends AbstractCollectionReportBuilder {
	private PersonSearchParameters searchParameters;
	private PersonSearchDAO searchDao;

	/**
	 * Constructor for PersonReportBuilder
	 */
	public PersonReportBuilder(ReportConfig config) {
		super(config);
		searchDao = PersonSearchDAO.getInstance();
		searchParameters = (PersonSearchParameters) config.getParameters();
	}

	/**
	 * @see AbstractReportBuilder#getElementName()
	 */
	protected String getElementName() {
		return "persons";
	}

	/**
	 * @see AbstractCollectionReportBuilder#getBody(int, int)
	 */
	protected Collection getBody(int firstElement, int numberOfElements) {
		Collection result = searchDao.find(searchParameters, firstElement, numberOfElements);
		return result;
	}
	
	/**
	 * @see AbstractCollectionReportBuilder#getTotalCount()
	 */
	protected int getTotalCount() {
		int result = searchDao.count(searchParameters);
		return result;
	}
}
