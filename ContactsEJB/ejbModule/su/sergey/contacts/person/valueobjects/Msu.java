package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;
import java.util.Date;

public interface Msu extends Serializable {
	String getGraduateDateStr();
	Date getGraduateDate();
	Integer getDepartmentId();
	boolean isHospice();
	String getSubfaculty();
	String getDescription();
}
