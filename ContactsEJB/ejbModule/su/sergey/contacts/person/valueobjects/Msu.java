package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.person.valueobjects.handles.MsuDepartmentHandle;

public interface Msu extends Serializable {
	Date getGraduateDate();
	MsuDepartmentHandle getDepartment();
	String getDepartmentShortName();
}
