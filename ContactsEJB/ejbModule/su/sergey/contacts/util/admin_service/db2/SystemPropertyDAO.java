package su.sergey.contacts.util.admin_service.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import su.sergey.contacts.exceptions.InvalidValueException;
import su.sergey.contacts.util.admin_service.SystemProperty;
import su.sergey.contacts.util.admin_service.SystemPropertyValidator;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.DAOException;

/**
 * Класс для доступа к данным таблицы SYSPROP (системные опции)
 * @author: 
 * @date 12/08/2002
 */

public class SystemPropertyDAO extends AbstractDAO {

    private static SystemPropertyDAO instance;

    protected SystemPropertyDAO() { }

    public static SystemPropertyDAO getInstance() {
        return instance != null ? instance : (instance = new SystemPropertyDAO());
    }

    /*
     * Gets System Property value by its name
     */
    /**
     * Возвращает значение системной опции по ее имени
     */
    public String getSystemPropertyValue(String name) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String value = null;
        try {
            conn = getConnection();
            String sql = "select value from ADM.SYSPROP where name=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                value = rs.getString(1);
            }
        } catch (SQLException e) {
            log(e);
            throw new DAOException(e);
        } finally {
         close(pstmt);
         close(conn);
        }
        return value;
    }


    /*
     * Gets SystemProperty by its name
     */
    /**
     * Возвращает объект SystemProperty по ее имени
     */
    public SystemProperty getSystemProperty(String name) throws DAOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        SystemProperty result = null;
        try {
            conn = getConnection();
            String sql = "select value,type,validity from ADM.SYSPROP where name=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new SystemProperty();
                result.setName(name);
                result.setValue(rs.getString("value"));
                result.setType(rs.getString("type"));
                result.setValidity(rs.getString("validity"));
            }
        } catch (SQLException e) {
            log(e);
            throw new DAOException(e);
        } finally {
         close(pstmt);
         close(conn);
        }
        return result;
    }


    /*
     * Gets all properties available as Collection of SystemProperty objects
     */
    /**
     * Возвращает коллекцию, содержащую все проперти из SYSPROP таблицы
     */
    public Collection getSystemProperties() throws DAOException {
        Connection conn = null;
        Statement stmt = null;
        Collection results = new ArrayList();
        try {
            conn = getConnection();
            String sql = "select name,value,desc from ADM.SYSPROP";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            populate(rs, results);
        } catch (SQLException e) {
            log(e);
            throw new DAOException(e);
        } finally {
         close(stmt);
         close(conn);
        }
        return results;
    }

    private final void populate(ResultSet rs, Collection res)
            throws SQLException {
        while(rs.next()) {
            SystemProperty prop = new SystemProperty();
            prop.setName(rs.getString(1));
            prop.setValue(rs.getString(2));
            prop.setDescription(rs.getString(3));
            res.add(prop);
        }
    }

    /*
    *  Updates System Property value checing its validity
    */
    /**
     * Изменяет значение системной опции. Проверяет корректность (валидность)
     * опции и кидает InvalidParameterException если значение некорректно
     */
    public void setSystemProperty(String name, String value)
            throws DAOException, InvalidValueException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            log("Before check validity");
            checkValidity(conn, name, value); //throws exception in cas of invalidity
            String sql = "update ADM.SYSPROP set value=? where name=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, value);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            /*
            log("Debug inserting");
            String sql = "insert into buf.lgz(cdate, zid,op_code,msg,znum,zdate,ztype,zosb,zfil) values(current timestamp,11123323,25676,'This information',6876587,current timestamp,null,'qwertu','123456')";
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < 10000; i++) {
                pstmt.executeUpdate();
            }
            log("Finished Debug inserting");
            */
        } catch (SQLException e) {
            log(e);
            throw new DAOException(e);
        } finally {
         close(pstmt);
         close(conn);
        }
    }

    private boolean checkValidity(Connection conn, String name, String value)
                    throws SQLException, InvalidValueException {
        PreparedStatement pstmt = null;
        boolean valid = true;
        try {
            String sql = "select type,validity from ADM.SYSPROP where name=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String type = rs.getString(1);
                if (type != null) {
                    //Parameter needs validation
                    String pattern = rs.getString(2);
                    valid = SystemPropertyValidator.getInstance().checkValidity(value,
                            type, pattern);
                    if (!valid) {
                        log("Making validation exception");
                        throw new InvalidValueException("Неправильно задан параметр: \""
                                + name + "\". Параметр должен соответствовать шаблону: " + pattern, name);

                    }
                }
            }
        } catch (SQLException e) {
            log(e);
            throw e;
        } finally {
         close(pstmt);
        }
        return valid;
    }

}
