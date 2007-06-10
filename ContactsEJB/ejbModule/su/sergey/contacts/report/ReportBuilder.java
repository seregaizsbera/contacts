package su.sergey.contacts.report;

import java.io.File;

public interface ReportBuilder {
	
	File buildReport() throws ReportException;
	
}
