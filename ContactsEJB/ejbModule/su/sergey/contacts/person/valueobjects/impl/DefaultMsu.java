package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.dto.MsuDepartmentHandle;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class DefaultMsu implements Serializable, Msu {
	private MsuDepartmentHandle departmentHandle;
	private Date graduateDate;
	private String departmentShortName;
	private String departmentName;
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
	 * Gets the departmentHandle
	 * @return Returns a MsuDepartmentHandle
	 */
	public MsuDepartmentHandle getDepartmentHandle() {
		return departmentHandle;
	}
	
	/**
	 * Sets the departmentHandle
	 * @param departmentHandle The departmentHandle to set
	 */
	public void setDepartmentHandle(MsuDepartmentHandle departmentHandle) {
		this.departmentHandle = departmentHandle;
	}

	/**
	 * Gets the departmentName
	 * @return Returns a String
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	
	/**
	 * Sets the departmentName
	 * @param departmentName The departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * Gets the departmentShortName
	 * @return Returns a String
	 */
	public String getDepartmentShortName() {
		return departmentShortName;
	}
	
	/**
	 * Sets the departmentShortName
	 * @param departmentShortName The departmentShortName to set
	 */
	public void setDepartmentShortName(String departmentShortName) {
		this.departmentShortName = departmentShortName;
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
}
