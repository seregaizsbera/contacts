package su.sergey.contacts.person.valueobjects.delegate;

import su.sergey.contacts.dto.IcqCreateInfo;
import su.sergey.contacts.dto.IcqHandle;
import su.sergey.contacts.dto.IcqUpdateInfo;
import su.sergey.contacts.person.valueobjects.Icq;

public final class IcqToIcqData implements IcqCreateInfo, IcqUpdateInfo {
	private IcqHandle handle;
	private Icq icq;

	/**
	 * Constructor for IcqToIcqData
	 */
	public IcqToIcqData(IcqHandle handle, Icq icq) {
		this.handle = handle;
		this.icq = icq;
	}

	/**
	 * @see IcqCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return handle.getPerson();
	}

	/**
	 * @see IcqCreateInfo#getIcq()
	 */
	public Long getIcq() {
		return icq.getIcq();
	}

	/**
	 * @see IcqCreateInfo#getNickname()
	 */
	public String getNickname() {
		return icq.getNickname();
	}
}
