package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class DefaultMsu implements Serializable, Msu {
	private Integer departmentId;
	private Date graduateDate;
	private boolean hospice;
	private String subfaculty;

	/**
	 * Gets the graduateDate
	 * @return Returns a Date
	 */
	public Date getGraduateDate() {
		return graduateDate;
	}
	
	/**
	 * Sets the graduateDate
	 * @param graduateDate The graduateDate to set
	 */
	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	/**
	 * @see Msu#getGraduateDateStr()
	 */
	public String getGraduateDateStr() {
		String result = null;
		if (graduateDate != null) {
    		result = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_YEAR_FORMAT).format(graduateDate);
		}
		return result;
	}
	
	/**
	 * Gets the hospice
	 * @return Returns a boolean
	 */
	public boolean isHospice() {
		return hospice;
	}
	
	/**
	 * Sets the hospice
	 * @param hospice The hospice to set
	 */
	public void setHospice(boolean hospice) {
		this.hospice = hospice;
	}
	
	/**
	 * Gets the subfaculty
	 * @return Returns a String
	 */
	public String getSubfaculty() {
		return subfaculty;
	}
	
	/**
	 * Sets the subfaculty
	 * @param subfaculty The subfaculty to set
	 */
	public void setSubfaculty(String subfaculty) {
		this.subfaculty = subfaculty;
	}
	
	/**
	 * Gets the departmentId
	 * @return Returns a Integer
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
	
	/**
	 * Sets the departmentId
	 * @param departmentId The departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
}
