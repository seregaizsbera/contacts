package su.sergey.contacts.person.valueobjects.delegate;

import su.sergey.contacts.dto.CoworkerCreateInfo;
import su.sergey.contacts.dto.CoworkerHandle;
import su.sergey.contacts.dto.CoworkerUpdateInfo;
import su.sergey.contacts.person.valueobjects.Coworker;

public final class CoworkerToCoworkerData implements CoworkerCreateInfo, CoworkerUpdateInfo {
	private CoworkerHandle handle;
	private Coworker attributes;

	/**
	 * Constructor for CoworkerToCoworkerData
	 */
	public CoworkerToCoworkerData(CoworkerHandle handle, Coworker attributes) {
		this.handle = handle;
		this.attributes = attributes;
	}

	/**
	 * @see CoworkerCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return handle.getPerson();
	}

	/**
	 * @see CoworkerCreateInfo#getJob()
	 */
	public String getJob() {
		return attributes.getJob();
	}

	/**
	 * @see CoworkerCreateInfo#getNote()
	 */
	public String getNote() {
		return attributes.getDescription();
	}
}

