package su.sergey.contacts.person.valueobjects.delegate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.dto.MsuData;
import su.sergey.contacts.dto.MsuDepartmentData;
import su.sergey.contacts.dto.MsuDepartmentHandle;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class MsuDataToMsu implements Serializable, Msu {
	private MsuData msuData;
	private MsuDepartmentData msuDepartmentData;
	
	/**
	 * Constructor for MsuDataToMsu
	 */
	public MsuDataToMsu(MsuData msuData) {
		this.msuData = msuData;
	}

	/**
	 * Constructor for MsuDataToMsu
	 */
	public MsuDataToMsu(MsuData msuData, MsuDepartmentData msuDepartmentData) {
		this.msuData = msuData;
		this.msuDepartmentData = msuDepartmentData;		
	}

	/**
	 * @see Msu#getGraduateDateStr()
	 */
	public String getGraduateDateStr() {
		String result = null;
		Date theDate = msuData.getGraduate();
		if (theDate != null) {
			result =  new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_YEAR_FORMAT).format(theDate);
		}
		return result;
	}

	/**
	 * @see Msu#getGraduateDate()
	 */
	public Date getGraduateDate() {
		return msuData.getGraduate();
	}

	/**
	 * @see Msu#getDepartmentHandle()
	 */
	public MsuDepartmentHandle getDepartmentHandle() {
		Integer department = msuData.getDepartment();
		MsuDepartmentHandle result = null;
		if (department != null) {
			result = new MsuDepartmentHandle(department);
		}
		return result;
	}

	/**
	 * @see Msu#getDepartmentName()
	 */
	public String getDepartmentName() {
		String result = null;
		if (msuDepartmentData != null) {
			result = msuDepartmentData.getDepartment();
		}
		return result;
	}

	/**
	 * @see Msu#getDepartmentShortName()
	 */
	public String getDepartmentShortName() {
		String result = null;
		if (msuDepartmentData != null) {
			result = msuDepartmentData.getShortName();
		}
		return result;
	}
	
	/**
	 * @see Msu#isHospice()
	 */
	public boolean isHospice() {
		boolean result = false;
		if (msuData.getHospice() != null) {
			result = msuData.getHospice().booleanValue();
		}
		return result;
	}

	/**
	 * @see Msu#getSubfaculty()
	 */
	public String getSubfaculty() {
		return msuData.getSubfaculty();
	}
}
