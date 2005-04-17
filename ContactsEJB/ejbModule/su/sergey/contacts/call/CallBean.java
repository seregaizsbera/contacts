package su.sergey.contacts.call;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.call.valueobjects.CallExpenseAttributes;
import su.sergey.contacts.dao.CallExpenseDAO;
import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.DuplicateInstanceException;
import su.sergey.contacts.exceptions.MultipleFieldsValidationException;

/**
 * Bean implementation class for Enterprise Bean: Call
 */
public class CallBean implements SessionBean {
	private SessionContext mySessionCtx;
	private CallExpenseDAO callExpenseDao;
	
	public CallExpenseAttributes findCallExpense(CallExpenseHandle handle) {
		CallExpenseData data = callExpenseDao.find(handle);
		if (data == null) {
		    return null;
		}
		CallExpenseAttributes result = new CallExpenseAttributes(data);
		return result;
	}
	
	public CallExpenseHandle createCallExpense(CallExpenseAttributes callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
		Integer id = callExpenseDao.create(callExpense);
		CallExpenseHandle result = new CallExpenseHandle(id);
		return result;
	}

	public void updateCallExpense(CallExpenseHandle handle, CallExpenseAttributes callExpense) throws MultipleFieldsValidationException, DuplicateInstanceException {
		callExpenseDao.update(handle, callExpense);
	}
	
	public void removeCallExpense(CallExpenseHandle handle) {
		callExpenseDao.remove(handle);
	}
	
	//--------------------------------------------------------------------------------------
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
		callExpenseDao = CallExpenseDAO.getInstance();
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
