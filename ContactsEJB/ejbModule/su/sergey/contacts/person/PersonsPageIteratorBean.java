package su.sergey.contacts.person;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.pageiterator.AbstractPageIterator;
import su.sergey.contacts.person.dao.PersonSearchDAO;
import su.sergey.contacts.person.searchparamters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.util.dao.DAOException;

/**
 * Bean implementation class for Enterprise Bean: PersonsPageIterator
 */
public class PersonsPageIteratorBean extends AbstractPageIterator implements SessionBean {
	private SessionContext mySessionCtx;
	private PersonSearchParameters searchParameters;
	
	/**
	 * @see AbstractPageIterator#evaluatePage()
	 */
	protected List evaluatePage() throws DAOException {
		PersonSearchDAO searchDao = PersonSearchDAO.getInstance();
		int position = getCurrentPozition() + 1;
		int pageSize = getPageSize();
		List result = searchDao.find(searchParameters, position, pageSize);
		return result;
	}

	/**
	 * @see AbstractPageIterator#evaluateTotal()
	 */
	protected long evaluateTotal() throws DAOException {
		PersonSearchDAO searchDao = PersonSearchDAO.getInstance();
		long result = searchDao.count(searchParameters);
		return 0;
	}
	
	public Person2[] prev() throws DAOException {
		List res = prevPage();
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	public Person2[] current() throws DAOException {
		List res = currentPage();
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	public Person2[] next() throws DAOException {
		List res = nextPage();
		Person2 result[] = (Person2[]) res.toArray(new Person2[0]);
		return result;
	}
	
	public Person2[] goTo(int page) throws DAOException {
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
	public void ejbActivate() {}
	
	/**
	 * ejbCreate
	 */
	public void ejbCreate(PersonSearchParameters searchParameters) throws CreateException {
		this.searchParameters = searchParameters;
	}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {}
}
