package su.sergey.contacts.person.businessdelegate;

import su.sergey.contacts.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.valueobjects.Person2;

public interface PersonPageIteratorBusinessDelegate extends PageIteratorBusinessDelegate {
	Person2[] prev() throws ContactsException;
	Person2[] current() throws ContactsException;
	Person2[] next() throws ContactsException;
	Person2[] goTo(int page) throws ContactsException;	
}
