package su.sergey.contacts.report.valueobjects.impl;

import java.util.Calendar;
import java.util.Date;

import su.sergey.contacts.report.valueobjects.ReportConfig;

public class DefaultReportConfig implements ReportConfig {
	private Integer type;
	private String description;
	private Object parameters;	
	private Date creationDateTime;
	
	public DefaultReportConfig() {
		Calendar calendar = Calendar.getInstance();
		creationDateTime = calendar.getTime();
	}
	
	/**
	 * Gets the type
	 * @return Returns a Integer
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * Sets the type
	 * @param type The type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * Gets the paramters
	 * @return Returns a Object
	 */
	public Object getParameters() {
		return parameters;
	}
	
	/**
	 * Sets the paramters
	 * @param paramters The paramters to set
	 */
	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}

	/**
	 * Gets the description
	 * @return Returns a String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see ReportConfig#getCreationDate()
	 */
	public Date getCreationDate() {
		return creationDateTime;
	}

	/**
	 * @see ReportConfig#getCreationTime()
	 */
	public Date getCreationTime() {
		return creationDateTime;
	}
}
