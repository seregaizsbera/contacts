package su.sergey.contacts.query;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.query.valueobjects.QueryMetaData;
import su.sergey.contacts.query.valueobjects.QueryRecord;
import su.sergey.contacts.query.valueobjects.QueryResult;
import su.sergey.contacts.query.valueobjects.impl.DefaultQueryMetaData;
import su.sergey.contacts.query.valueobjects.impl.DefaultQueryRecord;
import su.sergey.contacts.query.valueobjects.impl.DefaultQueryResult;

/**
 * Bean implementation class for Enterprise Bean: Query
 */
public class QueryBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public QueryResult performQuery(String sql) {
		String values[] = {"Тест1", "Тест2"};
		QueryRecord records[] = {new DefaultQueryRecord(values)};
		String columns[] = {"Столбец1", "Столбец2"};
		QueryMetaData metaData = new DefaultQueryMetaData(columns);
		QueryResult result = new DefaultQueryResult(metaData, records);
		return result;
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
