package su.sergey.contacts.person.valueobjects.delegate;

import java.util.Date;

import su.sergey.contacts.dto.ShnipCreateInfo;
import su.sergey.contacts.dto.ShnipHandle;
import su.sergey.contacts.dto.ShnipUpdateInfo;
import su.sergey.contacts.person.valueobjects.Shnip;

public final class ShnipToShnipData implements ShnipCreateInfo, ShnipUpdateInfo {
	private ShnipHandle handle;
	private Shnip shnipInfo;

	/**
	 * Constructor for ShnipToShnipData
	 */
	public ShnipToShnipData(ShnipHandle handle, Shnip shnipInfo) {
		this.handle = handle;
		this.shnipInfo = shnipInfo;
	}

	/**
	 * @see ShnipCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return handle.getPerson();
	}

	/**
	 * @see ShnipCreateInfo#getGraduate()
	 */
	public Date getGraduate() {
		return shnipInfo.getGraduateDate();
	}

	/**
	 * @see ShnipCreateInfo#getFormLetter()
	 */
	public String getFormLetter() {
		return shnipInfo.getFormLetter();
	}

	/**
	 * @see ShnipCreateInfo#getFormLeader()
	 */
	public Integer getFormLeader() {
		return shnipInfo.getFormLeader().getId();
	}

	/**
	 * @see ShnipCreateInfo#getNote()
	 */
	public String getNote() {
		return shnipInfo.getDescription();
	}
}

