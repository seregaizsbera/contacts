package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.dto.PersonHandle;

public interface Shnip extends Serializable {
	Date getGraduateDate();
	String getGraduateDateStr();
	String getFormLetter();
	PersonHandle getFormLeader();
	String getDescription();
}
