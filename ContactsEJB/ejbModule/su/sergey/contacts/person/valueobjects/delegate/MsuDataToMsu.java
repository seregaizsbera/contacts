package su.sergey.contacts.person.valueobjects.delegate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.dto.MsuData;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.util.ContactsDateTimeFormat;

public class MsuDataToMsu implements Serializable, Msu {
	private MsuData msuData;
	
	/**
	 * Constructor for MsuDataToMsu
	 */
	public MsuDataToMsu(MsuData msuData) {
		this.msuData = msuData;
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
	 * @see Msu#getDepartmentId()
	 */
	public Integer getDepartmentId() {
		return msuData.getDepartment();
	}

	/**
	 * @see Msu#isHospice()
	 */
	public boolean isHospice() {
		return msuData.getHospice().booleanValue();
	}

	/**
	 * @see Msu#getSubfaculty()
	 */
	public String getSubfaculty() {
		return msuData.getSubfaculty();
	}
	
	/**
	 * @see Msu#getDescription()
	 */
	public String getDescription() {
		return msuData.getNote();
	}
}
