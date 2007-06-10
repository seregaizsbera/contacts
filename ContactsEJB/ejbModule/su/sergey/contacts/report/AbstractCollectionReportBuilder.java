package su.sergey.contacts.report;

import java.util.Collection;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;

public abstract class AbstractCollectionReportBuilder extends AbstractReportBuilder {

	/**
	 * Constructor for AbstractCollectionReportBuilder
	 */
	protected AbstractCollectionReportBuilder(ReportConfig config) {
		super(config);
	}
	
	/**
	 * Возвращает очередную порцию объектов для помещения в отчет.
	 * 
	 * @param firstElement номер первого элемента. Нумерация эелементов ведется с 1.
	 * @param numberOfElements количество элементов.
	 * @return Collection Набор объектов с данными, которые будут помещены в отчет.
	 *     Если уже не найдено объектов, то возвращает пустую коллекцию.
	 */
	protected abstract Collection getReportBody(int firstElement, int numberOfElements) throws ReportException;
	
	protected abstract int getTotalCount() throws ReportException;
	
	protected int getFetchSize() throws ReportException {
		return 256;
	}

    /**
     * @see AbstractReportBuilder#makeReportBody(ContentHandler, ObjectToXmlConverter)
     */
    protected void makeReportBody(ContentHandler output, ObjectToXmlConverter converter) throws ReportException {
		int fetchSize = getFetchSize();
		int totalCount = getTotalCount();
        String elementName = getElementName();
        try {
            output.startElement("", "", elementName, new AttributesImpl());
            converter.makeXMLRecord(output, elementName + "_count", Integer.toString(totalCount));
            int pos = 1;
            Collection data = getReportBody(pos, fetchSize);
            while (data != null && !data.isEmpty()) {
                converter.addXMLRecordsFromCollection(output, elementName, data);
                pos += data.size();
                data = getReportBody(pos, fetchSize);
            }
            output.endElement("", "", elementName);
        } catch (SAXException e) {
        	throw new ReportException(e);
        }
    }
}
