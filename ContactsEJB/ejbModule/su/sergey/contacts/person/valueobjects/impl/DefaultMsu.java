package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import java.util.Date;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.person.valueobjects.handles.MsuDepartmentHandle;

public class DefaultMsu implements Serializable, Msu {
	private MsuDepartmentHandle department;
	private Date graduateDate;
	private String departmentShortName;

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
	 * Gets the department
	 * @return Returns a MsuDepartmentHandle
	 */
	public MsuDepartmentHandle getDepartment() {
		return department;
	}
	
	/**
	 * Sets the department
	 * @param department The department to set
	 */
	public void setDepartment(MsuDepartmentHandle department) {
		this.department = department;
	}
}
