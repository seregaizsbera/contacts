package su.sergey.contacts.person;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.admin.RoleNames;
import su.sergey.contacts.pageiterator.AbstractPageIterator;
import su.sergey.contacts.person.dao.PersonSearchDAO;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.valueobjects.PersonSearchGroupModes;

/**
 * Bean implementation class for Enterprise Bean: PersonPageIterator
 */
public class PersonPageIteratorBean extends AbstractPageIterator implements SessionBean {
	private SessionContext mySessionCtx;
	private PersonSearchParameters searchParameters;
	private PersonSearchDAO searchDao;
	
	/**
	 * @see AbstractPageIterator#evaluatePage()
	 */
	protected List evaluatePage() {
		int pageSize = getPageSize();
		int position = getCurrentPage() * pageSize + 1;
		List result = searchDao.find(searchParameters, position, pageSize);
		return result;
	}

	/**
	 * @see AbstractPageIterator#evaluateTotal()
	 */
	protected int evaluateTotal() {
		int result = searchDao.count(searchParameters);
		return result;
	}
	
	public Person2[] prev() {
		List res = prevPage();
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	public Person2[] current() {
		List res = currentPage();
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	public Person2[] next() {
		List res = nextPage();
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	public Person2[] goTo(int page) {
		List res = goToPage(page);
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	//------------------------------------------------------------------------
	
	/**
	 * getSessionContext
	 */
	public SessionContext getSessionContext() {
		return mySessionCtx;
	}
	
	/**
	 * setSessionContext
	 */
	public void setSessionContext(SessionContext ctx) {
		mySessionCtx = ctx;
	}
	
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	
	/**
	 * ejbCreate
	 */
	public void ejbCreate(PersonSearchParameters searchParameters, int pageSize) throws CreateException {
		if (!mySessionCtx.isCallerInRole(RoleNames.SERGEY)
		    && searchParameters.getGroupMode() != null
		    && !searchParameters.getGroupMode().equals(PersonSearchGroupModes.ANY)) {
		    	throw new CreateException("У вас нет прав для такого поиска");
		}
		this.searchDao = PersonSearchDAO.getInstance();
		this.searchParameters = searchParameters;
		create(pageSize);
	}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
}
