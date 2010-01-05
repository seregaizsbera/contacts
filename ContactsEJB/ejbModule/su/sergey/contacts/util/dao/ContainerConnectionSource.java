package su.sergey.contacts.util.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Имплементация ConnectionSource предназначенная для использования внутри
 * Servlet или EJB контейнера. Для получения соединений с базой использует
 * DataSource который получает из JNDI по адресу, который содержится в свойстве
 * dsJNDIName по умолчанию равному DEFAULT_DATA_SOURCE.
 * 
 * @author Сергей Богданов
 */
public class ContainerConnectionSource implements ConnectionSource {
    public static final String DEFAULT_JNDI_NAME = "java:comp/env/jdbc/DefaultDataSource";
    private final DataSource dataSource;

    public ContainerConnectionSource() {
        this(DEFAULT_JNDI_NAME);
    }

    /**
     * Создает экземпляр класса.
     * @param jndiName адресс в JNDI по которому будет производиться
     * поиск DataSource.
     */
    public ContainerConnectionSource(String jndiName) {
        try {
            Context context = new InitialContext();
            this.dataSource = (DataSource) context.lookup(jndiName);
        } catch (NamingException e) {
            throw new DAOException(e);
        } catch (RuntimeException e) {
            throw new DAOException(e);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (RuntimeException e) {
            throw new DAOException(e);
        }
    }

    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    /**
     * @see ConnectionSource#getConnection(String, String)
     */
    public Connection getConnection(String userName, String password) {
        try {
            return dataSource.getConnection(userName, password);
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (RuntimeException e) {
            throw new DAOException(e);
        }
    }
}
