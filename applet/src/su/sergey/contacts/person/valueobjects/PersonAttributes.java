package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;


public interface PersonAttributes extends Serializable {
	String getFirstName();
	String getMiddleName();
	String getLastName();
	Integer getGender();
	PhoneAttributes getBasicPhone();
	EmailAttributes getBasicEmail();
	Collection getPhones();
	String getNote();
	String getAddress();
	Collection getEmails();
	Icq getIcq();
	boolean isFriend();
	boolean isRelated();
	boolean isShnip();
	boolean isMsu();
	boolean isCoworker();
	Date getBirthday();
	Date getBirthYear();
	Friend getFriendInfo();
	Shnip getShnipInfo();
	Msu getMsuInfo();
	Related getRelatedInfo();
	Coworker getCoworkerInfo();
	Date getUpdateTime();
}
