package su.sergey.contacts.util.dao;

import java.sql.SQLException;

import javax.naming.NamingException;
import su.sergey.contacts.util.ex.RuntimeGenericException;

/**
 * Пробрасывается в с случае возникновения исключительных ситуаций при работе на
 * уровне доступа к базе данных.
 * 
 * @author Сергей Богданов
 */
public class DAOException extends RuntimeGenericException {
    public DAOException(String message, Exception cause) {
        super(message, cause);
    }

    public DAOException(Exception cause) {
        super(cause);
    }
    
    public DAOException(String message) {
        super(message, null);
    }
}
