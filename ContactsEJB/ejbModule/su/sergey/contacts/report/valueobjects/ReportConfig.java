package su.sergey.contacts.report.valueobjects;

import java.util.Date;

public interface ReportConfig {
	Integer getType();
	String getDescription();
	Object getParameters();
	Date getCreationDate();
	Date getCreationTime();
}
