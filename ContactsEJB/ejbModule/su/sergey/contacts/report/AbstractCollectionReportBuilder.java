package su.sergey.contacts.report;

import java.util.Collection;

import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;
import su.sergey.contacts.util.xml.XMLItem;

public abstract class AbstractCollectionReportBuilder extends AbstractReportBuilder {

	/**
	 * Constructor for AbstractCollectionReportBuilder
	 */
	protected AbstractCollectionReportBuilder(ReportConfig config) {
		super(config);
	}
	
	/**
	 * ���������� ��������� ������ �������� ��� ��������� � �����.
	 * 
	 * @param firstElement ����� ������� ��������. ��������� ���������� ������� � 1.
	 * @param numberOfElements ���������� ���������.
	 * @return Collection ����� �������� � �������, ������� ����� �������� � �����.
	 *     ���� ��� �� ������� ��������, �� ���������� ������ ���������.
	 */
	protected abstract Collection getBody(int firstElement, int numberOfElements);
	
	protected abstract int getTotalCount();
	
	protected int getFetchSize() {
		return 256;
	}

	/**
	 * @see AbstractReportBuilder#getContents()
	 */
	protected XMLItem getContents() {
		int fetchSize = getFetchSize();
        String elementName = getElementName();
        XMLItem result = new XMLItem(elementName);
        XMLItem countElement = new XMLItem(elementName + "_count", Integer.toString(getTotalCount()));
        result.addChild(countElement);
        int pos = 1;
        Collection data = getBody(pos, fetchSize);
        ObjectToXmlConverter converter = new ObjectToXmlConverter();
        while (data != null && !data.isEmpty()) {
            converter.addXMLRecordsFromCollection(result, elementName, data);
            pos += data.size();
            data.clear();
            data = getBody(pos, fetchSize);
        }
        return result;
	}
}
