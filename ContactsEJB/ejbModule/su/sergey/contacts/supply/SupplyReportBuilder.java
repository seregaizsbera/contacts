package su.sergey.contacts.supply;

import java.util.Collection;

import su.sergey.contacts.report.AbstractCollectionReportBuilder;
import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.supply.dao.SupplySearchDAO;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

public class SupplyReportBuilder extends AbstractCollectionReportBuilder {
	private SupplySearchDAO searchDao;
	private SupplySearchParameters searchParameters;

	/**
	 * Constructor for SupplyReportBuilder
	 */
	public SupplyReportBuilder(ReportConfig config) {
		super(config);
		searchParameters = (SupplySearchParameters) config.getParameters();
		searchDao = new SupplySearchDAO(new ContainerConnectionSource());
	}

	/**
	 * @see AbstractReportBuilder#getElementName()
	 */
	protected String getElementName() {
		return "supplies";
	}

	/**
	 * @see AbstractCollectionReportBuilder#getTotalCount()
	 */
	protected int getTotalCount() {
		int result = searchDao.count(searchParameters);
		return result;
	}

	/**
	 * @see AbstractCollectionReportBuilder#getReportBody(int, int)
	 */
	protected Collection getReportBody(int firstElement, int numberOfElements) {
		Collection result = searchDao.find(searchParameters, firstElement, numberOfElements);
		return result;
	}
}
