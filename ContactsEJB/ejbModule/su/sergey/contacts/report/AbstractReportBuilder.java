package su.sergey.contacts.report;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import su.sergey.contacts.JNDINames;
import su.sergey.contacts.properties.PropertyNames;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.properties.businessdelegate.PropertyBusinessDelegate;
import su.sergey.contacts.properties.businessdelegate.impl.DefaultPropertyBusinessDelegate;
import su.sergey.contacts.report.valueobjects.ReportConfig;
import su.sergey.contacts.util.xml.ObjectToXmlConverter;

public abstract class AbstractReportBuilder implements ReportBuilder {
	private ReportConfig config;

	/**
	 * Constructor for AbstractReportBuilder
	 */
	protected AbstractReportBuilder(ReportConfig config) {
		this.config = config;
	}	

	protected abstract void makeReportBody(ContentHandler output, ObjectToXmlConverter converter) throws ReportException;

	protected abstract String getElementName() throws ReportException;
	
	protected String getEncoding() throws ReportException {
		return "KOI8-R";
	}
	
	public File buildReport() throws ReportException {
		OutputStream output = null;
		try {
			PropertyBusinessDelegate property = new DefaultPropertyBusinessDelegate(JNDINames.PROPERTY_REFERENCE);
			File reportFolder = (File) property.getValue(PropertyNames.REPORT_FOLDER);
			File reportFile = File.createTempFile("report", ".xml", reportFolder);
			reportFile.deleteOnExit();
			output = new BufferedOutputStream(new FileOutputStream(reportFile));
			String encoding = getEncoding();
			OutputFormat format = new OutputFormat(Method.XML, encoding, false);
			XMLSerializer serializer = new XMLSerializer(output, format);
			ObjectToXmlConverter converter = new ObjectToXmlConverter();
			
			serializer.startDocument();
			    serializer.startElement("", "", "report", new AttributesImpl());
			        converter.makeXMLRecord(serializer, "config", config);
					makeReportBody(serializer, converter);
			    serializer.endElement("", "", "report");
			serializer.endDocument();
			
			return reportFile;
		} catch (SAXException e) {
			throw new ReportException(e);
		} catch (PropertyNotFoundException e) {
			throw new ReportException(e);
		} catch (FileNotFoundException e) {
			throw new ReportException(e);
		} catch (IOException e) {
			throw new ReportException(e);
		} finally {
			if (output != null) {
				try {
                    output.close();				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
