package su.sergey.contacts.util.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Имплементация ConnectionSource которая получает конеции к базе данных
 * напрямую через драйвер. Паремеры соединения он берет и когфигурационных
 * свойств, которые передаются ему в конструкторе или из системных свойст
 * если конфигурационные свойства null. Если для получения экземпляра
 * этого класса используется статический метод getInstance() то конфигурационные
 * свойства берутся из системных свойств.
 * 
 * @author
 */
public class PGConnectionSource implements ConnectionSource {
    /**
     * Имя конфигурационного свойства которое содержит
     * логин к базе данных.
     */
    public static final String PN_USER_LOGIN = "userLogin";
    
    /**
     * Имя конфигурационного свойства которое содержит
     * пароль для соединения с базой данных.
     */
    public static final String PN_USER_PASSWORD = "userPwd";
    
    /**
     * Имя конфигурационного свойства которое содержит
     * имя базы данных включая возможно имя машины на которой
     * она находится и порт.
     */
    public static final String PN_DB_NAME = "dbName";

    private static PGConnectionSource instance;

    private String dbName;
    private String userLogin;
    private String userPwd;
    private Properties prop;

    private PGConnectionSource() throws DAOException {
    	init();
    }

    /**
     * Создает экземпляр этого класса.
     * @param prop конфигурационные свойства которые будут использоваться
     * для соединения с базой данных.
     */
    public PGConnectionSource(Properties prop) throws DAOException {
        this.prop = prop;
        init();
    }

    /**
     * Инициализирует соединение с базой данных.
     */
    private void init() throws DAOException {
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

    public Connection getConnection() throws DAOException {
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:postgresql:" + getDbName(), getUserLogin(), getUserPwd());
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void close(Connection conn) throws DAOException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            throw new DAOException(sqle);
        }
    }

    /**
     * Возвращает имя базы данных к которой производит соединения
     * данный класс.
     * @return имя базы данных к которой производит соединения
     * данный класс.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * Вовзращает логин под которым данный класс подсоединяется
     * к базе данных.
     * @return логин под которым данный класс подсоединяется
     * к базе данных.
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Возвращает пароль котторый используется для подсоединения
     * к базе данных.
     * @return пароль котторый используется для подсоединения
     * к базе данных.
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * Возвращает экзмеляр данног класса который берет свой конфигурационные
     * свойства из системных свойст.
     * @return экзмеляр данног класса который берет свой конфигурационные
     * свойства из системных свойст.
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
	public Connection getConnection(String userName, String password) throws DAOException {
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
