package su.sergey.contacts.report;

import java.io.File;

import su.sergey.contacts.util.xml.XMLItem;

public interface ReportBuilder {
	XMLItem buildReportAsXML() throws ReportException;
	File buildReport() throws ReportException;
}
