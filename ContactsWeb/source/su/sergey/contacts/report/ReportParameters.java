package su.sergey.contacts.report;

public interface ReportParameters {
	String AN_REPORT_TYPE = "reportType";
	String AN_XML_FILE = "xmlFile";
	String AN_XSL_FILE = "xslFile";
	
	String PN_DESCRIPTION = "description";
	
	String PERSONS_XSL = "xsl/persons.xsl";
	String SUPPLIES_XSL = "xsl/supplies.xsl";
	
	String SHOW_REPORT_SERVLET = "/servlets/showReport";
}
