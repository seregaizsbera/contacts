package su.sergey.contacts.query;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import su.sergey.contacts.dao.QueryDAO;
import su.sergey.contacts.dto.QueryData;
import su.sergey.contacts.dto.QueryHandle;
import su.sergey.contacts.query.dao.QueryDAOFacade;
import su.sergey.contacts.query.valueobjects.QueryResult;

/**
 * Bean implementation class for Enterprise Bean: Query
 */
public class QueryBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public String[] getLastQueries(int maxNumberOfQueries, String userName) {
		QueryDAOFacade daoFacade = QueryDAOFacade.getInstance();
		String result[] = daoFacade.getLastQueries(userName, maxNumberOfQueries);
		return result;
	}
	
	public QueryResult performQuery(String sql, String userName) {
		QueryDAOFacade daoFacade = QueryDAOFacade.getInstance();
		RE selectRegexp = null;
		try {
            selectRegexp = new RE("^\\s*select", RE.MATCH_CASEINDEPENDENT);
		} catch (RESyntaxException e) {
			e.printStackTrace();
		}
        QueryResult result;
        if (selectRegexp.match(sql)) {
        	result = daoFacade.performSelect(sql);
        } else {
        	result = daoFacade.performUpdate(sql);
        }
        Query self = (Query) mySessionCtx.getEJBObject();
        try {
    		self.addQuery(sql, userName);
        } catch (RemoteException e) {
        	e.printStackTrace();
        }
		return result;
	}

	public void addQuery(String sql, String userName) {
		QueryDAO queryDao = QueryDAO.getInstance();
		QueryHandle handle = new QueryHandle(userName, sql);
		queryDao.remove(handle);
		QueryData queryData = new QueryData();
		queryData.setUserName(userName);
		queryData.setSql(sql);
		queryDao.create(queryData);
	}
	
	//-----------------------------------------------------------------
	
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
