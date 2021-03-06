package su.sergey.contacts.util.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ������������� ConnectionSource ������� �������� ������� � ���� ������
 * �������� ����� �������. �������� ���������� �� ����� � ����������������
 * �������, ������� ���������� ��� � ������������ ��� �� ��������� ������
 * ���� ���������������� �������� null. ���� ��� ��������� ����������
 * ����� ������ ������������ ����������� ����� getInstance() �� ����������������
 * �������� ������� �� ��������� �������.
 * 
 * @author ������ ��������
 */
public class PGConnectionSource implements ConnectionSource {
    /**
     * ��� ����������������� �������� ������� ��������
     * ����� � ���� ������.
     */
    public static final String PN_USER_LOGIN = "userName";
    
    /**
     * ��� ����������������� �������� ������� ��������
     * ������ ��� ���������� � ����� ������.
     */
    public static final String PN_USER_PASSWORD = "userPassword";
    
    /**
     * ��� ����������������� �������� ������� ��������
     * ��� ���� ������ ������� �������� ��� ������ �� �������
     * ��� ��������� � ����.
     */
    public static final String PN_DB_NAME = "databaseName";

    private static PGConnectionSource instance;

    private String dbName;
    private String userLogin;
    private String userPwd;
    private Properties prop;

    private PGConnectionSource() {
    	init();
    }

    /**
     * ������� ��������� ����� ������.
     * @param prop ���������������� �������� ������� ����� ��������������
     * ��� ���������� � ����� ������.
     */
    public PGConnectionSource(Properties prop) {
        this.prop = prop;
        init();
    }

    /**
     * �������������� ���������� � ����� ������.
     */
    private void init() {
        if (prop == null) {
            prop = System.getProperties();
        }
        userLogin = prop.getProperty(PN_USER_LOGIN);
        userPwd = prop.getProperty(PN_USER_PASSWORD);
        dbName = prop.getProperty(PN_DB_NAME);
        if (userLogin == null || userPwd == null || dbName == null) {
            throw new DAOException("One of the essential properties not specified!");
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        }
    }

    public Connection getConnection() {
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:postgresql:" + getDbName(), getUserLogin(), getUserPwd());
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            throw new DAOException(sqle);
        }
    }

    /**
     * ���������� ��� ���� ������ � ������� ���������� ����������
     * ������ �����.
     * @return ��� ���� ������ � ������� ���������� ����������
     * ������ �����.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * ���������� ����� ��� ������� ������ ����� ��������������
     * � ���� ������.
     * @return ����� ��� ������� ������ ����� ��������������
     * � ���� ������.
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * ���������� ������ �������� ������������ ��� �������������
     * � ���� ������.
     * @return ������ �������� ������������ ��� �������������
     * � ���� ������.
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * ���������� �������� ������ ������ ������� ����� ���� ����������������
     * �������� �� ��������� ������.
     * @return �������� ������ ������ ������� ����� ���� ����������������
     * �������� �� ��������� ������.
     */
    public static final PGConnectionSource getInstance() {
        if (instance == null) {
            instance = new PGConnectionSource();
        }
        return instance;
    }
	/**
	 * @see ConnectionSource#getConnection(String, String)
	 */
	public Connection getConnection(String userName, String password) {
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:postgresql:" + getDbName(), userName, password);
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
	}
}
