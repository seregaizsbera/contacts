package su.sergey.contacts.report;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.w3c.dom.Document;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.properties.PropertyNames;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.properties.businessdelegate.PropertyBusinessDelegate;
import su.sergey.contacts.properties.businessdelegate.impl.DefaultPropertyBusinessDelegate;
import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;
import su.sergey.contacts.util.xml.XMLItem;

public abstract class AbstractReportBuilder implements ReportBuilder {
	private ReportConfig config;

	/**
	 * Constructor for AbstractReportBuilder
	 */
	protected AbstractReportBuilder(ReportConfig config) {
		this.config = config;
	}
	
	public File buildReport() throws ReportException {
		XMLItem rootElement = buildReportAsXML();
		Document reportDocument = rootElement.makeDocument();
		rootElement = null;
		File result = saveReport(reportDocument);
		return result;
	}

	public XMLItem buildReportAsXML() throws ReportException {
		XMLItem rootElement = new XMLItem("report");
		XMLItem contents = getContents();
		XMLItem configElement = new ObjectToXmlConverter().makeXMLRecord("config", config);
		rootElement.addChild(configElement);
		rootElement.addChild(contents);
		return rootElement;
	}
	
	protected abstract XMLItem getContents();
	
	protected abstract String getElementName();
	
	protected String getEncoding() {
		return "KOI8-R";
	}
	
	private File saveReport(Document reportDocument) throws ReportException {
		try {
			PropertyBusinessDelegate property = new DefaultPropertyBusinessDelegate(JNDINames.PROPERTY_BEAN);
			File reportFolder = (File) property.getValue(PropertyNames.REPORT_FOLDER);
			File reportFile = File.createTempFile("report", ".xml", reportFolder);
			OutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile));
			String encoding = getEncoding();
			XMLItem.render(output, reportDocument, encoding, true);
			output.close();
			return reportFile;
		} catch (PropertyNotFoundException e) {
			throw new ReportException(e);
		} catch (FileNotFoundException e) {
			throw new ReportException(e);
		} catch (IOException e) {
			throw new ReportException(e);
		}
	}
}
