package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.dto.MsuDepartmentHandle;

public interface Msu extends Serializable {
	String getGraduateDateStr();
	Date getGraduateDate();
	MsuDepartmentHandle getDepartmentHandle();
	String getDepartmentName();
	String getDepartmentShortName();
	boolean isHospice();
	String getSubfaculty();
}
