package su.sergey.contacts.report.dao;

import java.io.File;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import su.sergey.contacts.report.ReportBuilder;
import su.sergey.contacts.report.ReportBuilderFactory;
import su.sergey.contacts.report.ReportException;
import su.sergey.contacts.report.valueobjects.impl.DefaultReportConfig;

public class ReportDAOFacade {
    private static final String REPORT_FILE_PATTERN = "^\\w+$";
	private static ReportDAOFacade instance;
	private RE fileNameRegexp;

	/**
	 * Constructor for ReportDAOFacade
	 */
	private ReportDAOFacade() {
		try {
    		fileNameRegexp = new RE(REPORT_FILE_PATTERN);
		} catch (RESyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public File buildReport(Integer reportType, String description, Object parameters) throws ReportException {
		DefaultReportConfig reportConfig = new DefaultReportConfig();
		reportConfig.setDescription(description);
		reportConfig.setParameters(parameters);
		reportConfig.setType(reportType);
		ReportBuilder reportBuilder = ReportBuilderFactory.getReportBuilder(reportConfig);
		File result = reportBuilder.buildReport();
		return result;
	}
	
	public static ReportDAOFacade getInstance() {
		if (instance == null) {
			instance = new ReportDAOFacade();
		}
    	return instance;
	}
}
