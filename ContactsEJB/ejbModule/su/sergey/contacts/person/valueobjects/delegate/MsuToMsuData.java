package su.sergey.contacts.person.valueobjects.delegate;

import java.util.Date;

import su.sergey.contacts.dto.MsuCreateInfo;
import su.sergey.contacts.dto.MsuHandle;
import su.sergey.contacts.dto.MsuUpdateInfo;
import su.sergey.contacts.person.valueobjects.Msu;

public final class MsuToMsuData implements MsuCreateInfo, MsuUpdateInfo {
	private MsuHandle handle;
	private Msu msuInfo;

	/**
	 * Constructor for MsuToMsuData
	 */
	public MsuToMsuData(MsuHandle handle, Msu msuInfo) {
		this.handle = handle;
		this.msuInfo = msuInfo;
	}

	/**
	 * @see MsuCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return handle.getPerson();
	}

	/**
	 * @see MsuCreateInfo#getGraduate()
	 */
	public Date getGraduate() {
		return msuInfo.getGraduateDate();
	}

	/**
	 * @see MsuCreateInfo#getDepartment()
	 */
	public Integer getDepartment() {
		return msuInfo.getDepartmentId();
	}

	/**
	 * @see MsuCreateInfo#getHospice()
	 */
	public Boolean getHospice() {
		return new Boolean(msuInfo.isHospice());
	}

	/**
	 * @see MsuCreateInfo#getSubfaculty()
	 */
	public String getSubfaculty() {
		return msuInfo.getSubfaculty();
	}

	/**
	 * @see MsuCreateInfo#getNote()
	 */
	public String getNote() {
		return msuInfo.getDescription();
	}

	/**
	 * @see MsuCreateInfo#getTutor()
	 */
	public Boolean getTutor() {
		return new Boolean(msuInfo.isTutor());
	}
}
