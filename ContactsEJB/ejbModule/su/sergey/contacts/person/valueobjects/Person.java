package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public interface Person extends Serializable {
	String getFirstName();
	String getMiddleName();
	String getLastName();
	Collection getPhones();
	String getAddress();
	Collection getEmails();
	String getIcq();
	boolean isFriend();
	boolean isRelated();
	boolean isShnip();
	boolean isMsu();
	Date getBirthday();
	Friend getFriendInfo();
	Shnip getShnipInfo();
	Msu getMsuInfo();
	Related getRelatedInfo();
}
