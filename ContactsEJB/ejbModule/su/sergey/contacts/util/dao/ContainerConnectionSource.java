package su.sergey.contacts.util.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import su.sergey.contacts.JNDINames;

/**
 * Имплементация ConnectionSource предназначенная для использования внутри
 * Servlet или EJB контейнера. Для получения соединений с базой использует
 * DataSource который получает из JNDI по адресу, который содержится в свойстве
 * dsJNDIName по умолчанию равному DEFAULT_DATA_SOURCE.
 * 
 * @author 
 */
public class ContainerConnectionSource implements ConnectionSource {
    private static ContainerConnectionSource instance = null;

    private String dsJNDIName = JNDINames.DEFAULT_DATA_SOURCE_REFERENCE;

    private ContainerConnectionSource() {}

    /**
     * Создает экземпляр класса.
     * @param dsJNDIName адресс в JNDI по которому будет производиться
     * поиск DataSource.
     */
    public ContainerConnectionSource(String dsJNDIName) {
        this.dsJNDIName = dsJNDIName;
    }

    public Connection getConnection() throws DAOException {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {}
    }

    protected DataSource getDataSource() throws DAOException {
        try {
            Context ctx = new InitialContext();
            return (DataSource) ctx.lookup(getDsJNDIName());
        } catch (NamingException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Возвращает значение свойства dsJNDIName, которое определяет по какому
     * адресу в JNDI будет производиьтся поиск DataSource.
     * @return значение свойства dsJNDIName, которое определяет по какому
     * адресу в JNDI будет производиьтся поиск DataSource.
     */
    public String getDsJNDIName() {
        return dsJNDIName;
    }

    protected void setDsJNDIName(String dsJNDIName) {
        this.dsJNDIName = dsJNDIName;
    }

    /**
     * Возвращает экземпляр этого класса со значением совйства
     * dsJNDIName равным значению по умолчанию.
     * @return экземпляр этого класса со значением совйства
     * dsJNDIName равным значению по умолчанию.
     */
    public static ContainerConnectionSource getInstance() {
    	if (instance == null) {
			instance = new ContainerConnectionSource();
    	}
        return instance;
    }
	/**
	 * @see ConnectionSource#getConnection(String, String)
	 */
	public Connection getConnection(String userName, String password) throws DAOException {
        try {
            return getDataSource().getConnection(userName, password);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
	}
}
