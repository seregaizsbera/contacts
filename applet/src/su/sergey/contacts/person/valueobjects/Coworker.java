package su.sergey.contacts.person.valueobjects;

import java.io.Serializable;

public interface Coworker extends Serializable {
	String getJob();
	String getAdministration();
	String getDepartment();
	String getPost();
	String getDescription();
}

