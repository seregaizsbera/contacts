package su.sergey.contacts.person;

import java.util.Collection;

import su.sergey.contacts.person.dao.PersonSearchDAO;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.report.AbstractCollectionReportBuilder;
import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

public class PersonReportBuilder extends AbstractCollectionReportBuilder {
	private final PersonSearchParameters searchParameters;
	private final PersonSearchDAO searchDao;

	/**
	 * Constructor for PersonReportBuilder
	 */
	public PersonReportBuilder(ReportConfig config) {
		super(config);
		ConnectionSource connectionSource = new ContainerConnectionSource();
		searchDao = new PersonSearchDAO(connectionSource);
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
	protected Collection getReportBody(int firstElement, int numberOfElements) {
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
