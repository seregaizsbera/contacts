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
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

/**
 * Bean implementation class for Enterprise Bean: Query
 */
public class QueryBean implements SessionBean {
    private SessionContext mySessionCtx;
    private QueryDAO queryDao;
    private QueryDAOFacade queryDaoFacade;
	
	public String[] getLastQueries(int maxNumberOfQueries, String userName) {
		String result[] = queryDaoFacade.getLastQueries(userName, maxNumberOfQueries);
		return result;
	}
	
	public QueryResult performQuery(String sql, String userName) {
		RE selectRegexp = null;
		try {
            selectRegexp = new RE("^\\s*select", RE.MATCH_CASEINDEPENDENT);
		} catch (RESyntaxException e) {
			e.printStackTrace();
		}
        QueryResult result;
        if (selectRegexp.match(sql)) {
        	result = queryDaoFacade.performSelect(sql);
        } else {
        	result = queryDaoFacade.performUpdate(sql);
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
	    ConnectionSource connectionSource = new ContainerConnectionSource();
	    queryDao = new QueryDAO(connectionSource);
	    queryDaoFacade = new QueryDAOFacade(connectionSource);
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
