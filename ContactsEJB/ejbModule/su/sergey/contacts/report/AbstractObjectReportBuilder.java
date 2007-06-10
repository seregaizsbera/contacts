package su.sergey.contacts.report;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;

public abstract class AbstractObjectReportBuilder extends AbstractReportBuilder {

	/**
	 * Constructor for AbstractObjectReportBuilder
	 */
	protected AbstractObjectReportBuilder(ReportConfig config) {
		super(config);
	}

	protected abstract Object getReportBody() throws ReportException;
	
    /**
     * @see AbstractReportBuilder#makeReportBody(ContentHandler, ObjectToXmlConverter)
     */
    protected void makeReportBody(ContentHandler output, ObjectToXmlConverter converter) throws ReportException {
		String elementName = getElementName();			
		Object contents = getReportBody();
		try {
		    converter.makeXMLRecord(output, elementName, contents);
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
}
