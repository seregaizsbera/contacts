package su.sergey.contacts.person.businessdelegate;

import su.sergey.contacts.pageiterator.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.person.valueobjects.Person2;

public interface PersonPageIteratorBusinessDelegate extends PageIteratorBusinessDelegate {
	Person2[] prev();
	Person2[] current();
	Person2[] next();
	Person2[] goTo(int page);	
}
