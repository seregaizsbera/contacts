package su.sergey.contacts.call;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.call.valueobjects.CallExpense2;
import su.sergey.contacts.pageiterator.AbstractPageIterator;

/**
 * Bean implementation class for Enterprise Bean: CallExpensePageIterator
 */
public class CallExpensePageIteratorBean extends AbstractPageIterator implements SessionBean {
	private SessionContext mySessionCtx;
	
	/**
	 * @see AbstractPageIterator#evaluatePage()
	 */
	protected List evaluatePage() {
		return null;
	}

	/**
	 * @see AbstractPageIterator#evaluateTotal()
	 */
	protected int evaluateTotal() {
		return 0;
	}
	
    public CallExpense2[] prev() {
		List res = prevPage();
		CallExpense2 result[] = (CallExpense2[]) res.toArray(new CallExpense2[0]);
		return result;
    }
	
    public CallExpense2[] current() {
		List res = currentPage();
		CallExpense2 result[] = (CallExpense2[]) res.toArray(new CallExpense2[0]);
		return result;
    }
	
    public CallExpense2[] next() {
		List res = nextPage();
		CallExpense2 result[] = (CallExpense2[]) res.toArray(new CallExpense2[0]);
		return result;
    }
	
    public CallExpense2[] goTo(int page) {
		List res = goToPage(page);
		CallExpense2 result[] = (CallExpense2[]) res.toArray(new CallExpense2[0]);
		return result;
    }

	//----------------------------------------------------------------------------------------
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
	public void ejbCreate() throws CreateException {
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
