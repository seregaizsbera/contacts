package su.sergey.contacts.report;

import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;
import su.sergey.contacts.util.xml.XMLItem;

public abstract class AbstractObjectReportBuilder extends AbstractReportBuilder {

	/**
	 * Constructor for AbstractObjectReportBuilder
	 */
	protected AbstractObjectReportBuilder(ReportConfig config) {
		super(config);
	}

	protected abstract Object getReportBody();
	
	/**
	 * @see AbstractReportBuilder#getContents()
	 */
	protected XMLItem getContents() {
		String elementName = getElementName();
		Object body = getReportBody();
		XMLItem result = ObjectToXmlConverter.makeXMLRecord(elementName, body);
		return result;
	}
}
