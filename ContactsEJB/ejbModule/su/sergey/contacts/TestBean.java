package su.sergey.contacts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

/**
 * Bean implementation class for Enterprise Bean: Test
 */
public class TestBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public int getSeventeen() {
		try {
			Context context = new InitialContext();
			Object object = context.lookup("java:comp/env/jdbc/ContactsDB");
			DataSource dataSource = (DataSource) PortableRemoteObject.narrow(object, DataSource.class);
			Connection connection = dataSource.getConnection();
			PreparedStatement st = connection.prepareStatement("select count(*) from persons");
			ResultSet rs = st.executeQuery();
			rs.next();
			int result = rs.getInt(1);
			rs.close();
			st.close();
			connection.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 17;
	}
	
	public Properties getProperties() {
		return System.getProperties();
	}
	
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
	public void ejbCreate() throws CreateException {}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {}
}
