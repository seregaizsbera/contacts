package su.sergey.contacts.util.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ������������� ConnectionSource ��������������� ��� ������������� ������
 * Servlet ��� EJB ����������. ��� ��������� ���������� � ����� ����������
 * DataSource ������� �������� �� JNDI �� ������, ������� ���������� � ��������
 * dsJNDIName �� ��������� ������� DEFAULT_DATA_SOURCE.
 * 
 * @author 
 */
public class ContainerConnectionSource implements ConnectionSource {
    /**
     * �������� �� ��������� �������� dsJNDIName.
     */
    public static final String DEFAULT_DATA_SOURCE = "java:comp/env/jdbc/DefaultDataSource";
    
    private static ContainerConnectionSource instance = null;

    private String dsJNDIName = DEFAULT_DATA_SOURCE;

    private ContainerConnectionSource() {}

    /**
     * ������� ��������� ������.
     * @param dsJNDIName ������ � JNDI �� �������� ����� �������������
     * ����� DataSource.
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
     * ���������� �������� �������� dsJNDIName, ������� ���������� �� ������
     * ������ � JNDI ����� ������������� ����� DataSource.
     * @return �������� �������� dsJNDIName, ������� ���������� �� ������
     * ������ � JNDI ����� ������������� ����� DataSource.
     */
    public String getDsJNDIName() {
        return dsJNDIName;
    }

    protected void setDsJNDIName(String dsJNDIName) {
        this.dsJNDIName = dsJNDIName;
    }

    /**
     * ���������� ��������� ����� ������ �� ��������� ��������
     * dsJNDIName ������ �������� �� ���������.
     * @return ��������� ����� ������ �� ��������� ��������
     * dsJNDIName ������ �������� �� ���������.
     */
    public static ContainerConnectionSource getInstance() {
    	if (instance == null) {
			instance = new ContainerConnectionSource();
    	}
        return instance;
    }
}