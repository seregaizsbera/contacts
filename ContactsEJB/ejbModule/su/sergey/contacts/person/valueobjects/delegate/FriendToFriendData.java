package su.sergey.contacts.person.valueobjects.delegate;

import su.sergey.contacts.dto.FriendCreateInfo;
import su.sergey.contacts.dto.FriendHandle;
import su.sergey.contacts.dto.FriendUpdateInfo;
import su.sergey.contacts.person.valueobjects.Friend;

public final class FriendToFriendData implements FriendCreateInfo, FriendUpdateInfo {
	private FriendHandle handle;
	private Friend friendInfo;

	/**
	 * Constructor for FriendToFriendData
	 */
	public FriendToFriendData(FriendHandle handle, Friend friendInfo) {
		this.handle = handle;
		this.friendInfo = friendInfo;
	}

	/**
	 * @see FriendCreateInfo#getPerson()
	 */
	public Integer getPerson() {
		return handle.getPerson();
	}

	/**
	 * @see FriendCreateInfo#getNote()
	 */
	public String getNote() {
		return friendInfo.getDescription();
	}
}
