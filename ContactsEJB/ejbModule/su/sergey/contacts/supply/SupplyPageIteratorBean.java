package su.sergey.contacts.supply;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.pageiterator.AbstractPageIterator;
import su.sergey.contacts.supply.dao.SupplySearchDAO;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.util.dao.DAOException;

/**
 * Bean implementation class for Enterprise Bean: SupplyPageIterator
 */
public class SupplyPageIteratorBean extends AbstractPageIterator implements SessionBean {
	private SessionContext mySessionCtx;
	private SupplySearchParameters searchParameters;
	
	/**
	 * @see AbstractPageIterator#evaluatePage()
	 */
	protected List evaluatePage() throws DAOException {
		SupplySearchDAO searchDao = SupplySearchDAO.getInstance();
		int pageSize = getPageSize();
		int position = getCurrentPage() * pageSize + 1;
		List result = searchDao.find(searchParameters, position, pageSize);
		return result;
	}

	/**
	 * @see AbstractPageIterator#evaluateTotal()
	 */
	protected int evaluateTotal() throws DAOException {
		SupplySearchDAO searchDao = SupplySearchDAO.getInstance();
		int result = searchDao.count(searchParameters);
		return result;
	}
	
	public Supply2[] prev() throws DAOException {
		List res = prevPage();
		Supply2 result[] = (Supply2[]) res.toArray(new Supply2[0]);
		return result;
	}
	
	public Supply2[] current() throws DAOException {
		List res = currentPage();
		Supply2 result[] = (Supply2[]) res.toArray(new Supply2[0]);
		return result;
	}
	
	public Supply2[] next() throws DAOException {
		List res = nextPage();
		Supply2 result[] = (Supply2[]) res.toArray(new Supply2[0]);
		return result;
	}
	
	public Supply2[] goTo(int page) throws DAOException {
		List res = goToPage(page);
		Supply2 result[] = (Supply2[]) res.toArray(new Supply2[0]);
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
	public void ejbCreate(SupplySearchParameters searchParameters, int pageSize) throws CreateException {
		this.searchParameters = searchParameters;
		create(pageSize);
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
