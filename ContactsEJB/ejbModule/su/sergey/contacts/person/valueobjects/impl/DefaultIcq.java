package su.sergey.contacts.person.valueobjects.impl;

import java.io.Serializable;

import su.sergey.contacts.person.valueobjects.Icq;

public class DefaultIcq implements Serializable, Icq {
	private Long icq;
	private String nickname;

	/**
	 * Gets the icq
	 * @return Returns a Long
	 */
	public Long getIcq() {
		return icq;
	}
	
	/**
	 * Sets the icq
	 * @param icq The icq to set
	 */
	public void setIcq(Long icq) {
		this.icq = icq;
	}

	/**
	 * Gets the nickname
	 * @return Returns a String
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Sets the nickname
	 * @param nickname The nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
