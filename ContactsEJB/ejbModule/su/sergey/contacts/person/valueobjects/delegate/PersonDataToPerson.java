package su.sergey.contacts.person.valueobjects.delegate;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import su.sergey.contacts.dto.AddressData;
import su.sergey.contacts.dto.BirthdayData;
import su.sergey.contacts.dto.CoworkerData;
import su.sergey.contacts.dto.FriendData;
import su.sergey.contacts.dto.IcqData;
import su.sergey.contacts.dto.MsuData;
import su.sergey.contacts.dto.PersonData;
import su.sergey.contacts.dto.PersonHandle;
import su.sergey.contacts.dto.RelatedData;
import su.sergey.contacts.dto.ShnipData;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.person.valueobjects.Coworker;
import su.sergey.contacts.person.valueobjects.Friend;
import su.sergey.contacts.person.valueobjects.Icq;
import su.sergey.contacts.person.valueobjects.Msu;
import su.sergey.contacts.person.valueobjects.PersonAttributes;
import su.sergey.contacts.person.valueobjects.Related;
import su.sergey.contacts.person.valueobjects.Shnip;
import su.sergey.contacts.person.valueobjects.impl.DefaultCoworker;
import su.sergey.contacts.person.valueobjects.impl.DefaultFriend;
import su.sergey.contacts.person.valueobjects.impl.DefaultIcq;
import su.sergey.contacts.person.valueobjects.impl.DefaultRelated;
import su.sergey.contacts.person.valueobjects.impl.DefaultShnip;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.util.ContactsDateTimeFormat;


public class PersonDataToPerson implements Serializable, PersonAttributes {
	private PersonData personData;
	private Collection phones;
	private Collection emails;
	private DefaultIcq icq;
	private DefaultFriend friend;
	private DefaultRelated related;
	private DefaultShnip shnip;
	private Msu msu;
	private DefaultCoworker coworker;
	private String address;
	private Date birthday;
	private Date birthYear;
	private String birthdayStr;
	
	/**
	 * Constructor for PersonDataToPersonAttributes
	 */
	public PersonDataToPerson(PersonData personData,
	                                    Collection phones,
	                                    Collection emails,
	                                    BirthdayData birthdayData,
	                                    IcqData icqData,
	                                    AddressData addressData,
	                                    FriendData friendData,
	                                    RelatedData relatedData,
	                                    ShnipData shnipData,
	                                    MsuData msuData,
	                                    CoworkerData coworkerData) {
	    this.personData = personData;
	    this.phones = phones;
	    this.emails = emails;
	    if (birthdayData != null) {
	    	this.birthday = birthdayData.getBirthday();
	    	this.birthYear = birthdayData.getBirthyear();
	    }
	    if (icqData != null) {
	    	this.icq = new DefaultIcq();
	    	this.icq.setIcq(icqData.getIcq());
	    	this.icq.setNickname(icqData.getNickname());	    	
	    }
	    if (addressData != null) {
	        this.address = addressData.getAddress();
	    }
	    if (friendData != null) {
	    	this.friend = new DefaultFriend();
	    	this.friend.setDescription(friendData.getNote());
	    }
	    if (relatedData != null) {
	    	this.related = new DefaultRelated();
	    	this.related.setDescription(relatedData.getNote());
	    	this.related.setRelationship(relatedData.getRelationship());
	    }
	    if (shnipData != null) {
	    	this.shnip = new DefaultShnip();
	    	this.shnip.setDescription(shnipData.getNote());
	    	Integer formLeader = shnipData.getFormLeader();
	    	if (formLeader != null) {
    	    	this.shnip.setFormLeader(new PersonHandle(formLeader));
	    	}
	    	this.shnip.setFormLetter(shnipData.getFormLetter());
	    	this.shnip.setGraduateDate(shnipData.getGraduate());
	    }
	    if (msuData != null) {
	    	this.msu = new MsuDataToMsu(msuData);
	    }
	    if (coworkerData != null) {
	    	this.coworker = new DefaultCoworker();
	    	this.coworker.setDescription(coworkerData.getNote());
	    	this.coworker.setJob(coworkerData.getJob());
	    	this.coworker.setAdministration(coworkerData.getAdministration());
	    	this.coworker.setDepartment(coworkerData.getDepartment());
	    	this.coworker.setPost(coworkerData.getPost());
	    }
	}

	/**
	 * @see PersonAttributes#getFirstName()
	 */
	public String getFirstName() {
		return personData.getFirst();
	}

	/**
	 * @see PersonAttributes#getMiddleName()
	 */
	public String getMiddleName() {
		return personData.getMiddle();
	}

	/**
	 * @see PersonAttributes#getLastName()
	 */
	public String getLastName() {
		return personData.getLast();
	}

	/**
	 * @see PersonAttributes#getBasicPhone()
	 */
	public PhoneAttributes getBasicPhone() {
		if (phones == null) {
			return null;
		}
		PhoneAttributes result = null;
		int index = 0;
		for (Iterator i = phones.iterator(); i.hasNext();) {
			PhoneAttributes phone = (PhoneAttributes) i.next();
			if (index++ == 0) {
				result = phone;
			}
			if (phone.isBasic()) {
				result = phone;
				break;
			}
		}
		return result;
	}

	/**
	 * @see PersonAttributes#getPhones()
	 */
	public Collection getPhones() {
		return phones;
	}

	/**
	 * @see PersonAttributes#getNote()
	 */
	public String getNote() {
		return personData.getNote();
	}

	/**
	 * @see PersonAttributes#getAddress()
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @see PersonAttributes#getEmails()
	 */
	public Collection getEmails() {
		return emails;
	}

	/**
	 * @see PersonAttributes#getIcq()
	 */
	public Icq getIcq() {
		return icq;
	}

	/**
	 * @see PersonAttributes#isFriend()
	 */
	public boolean isFriend() {
		return friend != null;
	}

	/**
	 * @see PersonAttributes#isRelated()
	 */
	public boolean isRelated() {
		return related != null;
	}

	/**
	 * @see PersonAttributes#isShnip()
	 */
	public boolean isShnip() {
		return shnip != null;
	}

	/**
	 * @see PersonAttributes#isMsu()
	 */
	public boolean isMsu() {
		return msu != null;
	}

	/**
	 * @see PersonAttributes#isCoworker()
	 */
	public boolean isCoworker() {
		return coworker != null;
	}

	/**
	 * @see PersonAttributes#getBirthday()
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @see PersonAttributes#getFriendInfo()
	 */
	public Friend getFriendInfo() {
		return friend;
	}

	/**
	 * @see PersonAttributes#getShnipInfo()
	 */
	public Shnip getShnipInfo() {
		return shnip;
	}

	/**
	 * @see PersonAttributes#getMsuInfo()
	 */
	public Msu getMsuInfo() {
		return msu;
	}

	/**
	 * @see PersonAttributes#getRelatedInfo()
	 */
	public Related getRelatedInfo() {
		return related;
	}

	/**
	 * @see PersonAttributes#getCoworkerInfo()
	 */
	public Coworker getCoworkerInfo() {
		return coworker;
	}
	
	/**
	 * @see PersonAttributes#getGender()
	 */
	public Integer getGender() {
		return personData.getGender();
	}

	/**
	 * @see PersonAttributes#getBasicEmail()
	 */
	public EmailAttributes getBasicEmail() {
		if (emails == null) {
			return null;
		}
		EmailAttributes result = null;
		int index = 0;
		for (Iterator i = emails.iterator(); i.hasNext();) {
			EmailAttributes email = (EmailAttributes) i.next();
			if (index++ == 0) {
				result = email;
			}
			if (email.isBasic()) {
				result = email;
				break;
			}
		}
		return result;
	}

	/**
	 * Gets the birthYear
	 * @return Returns a Date
	 */
	public Date getBirthYear() {
		return birthYear;
	}
	
	/**
	 * Sets the birthYear
	 * @param birthYear The birthYear to set
	 */
	public void setBirthYear(Date birthYear) {
		this.birthYear = birthYear;
	}
}
