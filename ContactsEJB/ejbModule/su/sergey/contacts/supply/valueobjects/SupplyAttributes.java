package su.sergey.contacts.supply.valueobjects;

import java.io.Serializable;
import java.util.Collection;

public interface SupplyAttributes extends Serializable {
	String getName();
	String getParentName();
	Integer getKind();
	String getAddress();
	String getUrl();
	String getInn();
	String getShortName();
	boolean isImportant();
	String getNote();
	Collection getPhones();
	Collection getEmails();
}
