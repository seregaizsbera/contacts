package su.sergey.contacts.person.valueobjects.delegate;

import su.sergey.contacts.dto.RelatedCreateInfo;
import su.sergey.contacts.dto.RelatedHandle;
import su.sergey.contacts.dto.RelatedUpdateInfo;
import su.sergey.contacts.person.valueobjects.Related;

public final class RelatedToRelatedData implements RelatedCreateInfo, RelatedUpdateInfo {
	private RelatedHandle handle;
	private Related relatedInfo;

	/**
	 * Constructor for RelatedToRelatedData
	 */
	public RelatedToRelatedData(RelatedHandle handle, Related relatedInfo) {
		this.handle = handle;
		this.relatedInfo = relatedInfo;
	}

	/**
	 * @see RelatedCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return handle.getPerson();
	}

	/**
	 * @see RelatedCreateInfo#getRelationship()
	 */
	public String getRelationship() {
		return relatedInfo.getRelationship();
	}

	/**
	 * @see RelatedCreateInfo#getNote()
	 */
	public String getNote() {
		return relatedInfo.getDescription();
	}
}
