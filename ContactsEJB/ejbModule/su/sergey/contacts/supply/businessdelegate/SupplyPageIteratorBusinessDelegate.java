package su.sergey.contacts.supply.businessdelegate;

import su.sergey.contacts.pageiterator.businessdelegate.PageIteratorBusinessDelegate;
import su.sergey.contacts.supply.valueobjects.Supply2;

public interface SupplyPageIteratorBusinessDelegate extends PageIteratorBusinessDelegate {
	Supply2[] prev();
	Supply2[] current();
	Supply2[] next();
	Supply2[] goTo(int page);	
}
