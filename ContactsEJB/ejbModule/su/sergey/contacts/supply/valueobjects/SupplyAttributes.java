package su.sergey.contacts.supply.valueobjects;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public interface SupplyAttributes extends Serializable {
	String getName();
	String getParentName();
	Integer getKind();
	String getAddress();
	String getMetro();
	String getUrl();
	String getInn();
	String getKpp();
	String getOgrn();
	String getShortName();
	boolean isImportant();
	String getPropertyForm();
	boolean isHolding();
	String getNote();
	Collection getPhones();
	Collection getEmails();
	Date getUpdateTime();
}
