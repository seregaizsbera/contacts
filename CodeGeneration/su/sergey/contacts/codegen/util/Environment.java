package su.sergey.contacts.codegen.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Enviroment
 * 
 * @author Сергей Богданов
 */
public class Environment {
    private static Properties properties;
    
    static {
        properties = new Properties();
        ClassLoader loader = Environment.class.getClassLoader();
        InputStream input = loader.getResourceAsStream("environment.properties");
        if (input != null) {
            try {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getCurrencyClass() {
        return properties.getProperty("CURRENCY_CLASS", "su.sergey.contacts.valueobjects.Currency");
    }
    
    public static String getRStatusColumn() {
        return properties.getProperty("RSTATUS_COLUMN", "RSTATUS");
    }
    
    public static String getActidColumn() {
        return properties.getProperty("ACTID_COLUMN", "ACTID");
    }
    
    public static String getSrcPath() {
        return properties.getProperty("SRC_PATH", "/usr/src");
    }
    
    public static String getDocumentOutputPath() {
        return properties.getProperty("DOCUMENT_OUTPUT_PATH", "/usr/src/table_desc.html");
    }
    
    public static String getDocumentEncoding() {
        return properties.getProperty("DOCUMENT_ENCODING", "KOI8-R");
    }
    
    public static String getDtoPackage() {
        return properties.getProperty("DTO_PACKAGE", "su.sergey.contacts.dao.dto");
    }

    public static String getDaoPackage() {
        return properties.getProperty("DAO_PACKAGE", "su.sergey.contacts.dao.db2");
    }

    public static String getAbstractDaoClass() {
        return properties.getProperty("ABSTRACT_DAO_CLASS", "su.sergey.contacts.util.dao.db2.AbstractDAO");
    }

    public static String getConnectionSourceClass() {
        return properties.getProperty("CONNECTION_SOURCE_CLASS", "su.sergey.contacts.util.dao.ConnectionSource");
    }

    public static String getSqlOutAccessorClass() {
        return properties.getProperty("SQL_OUT_ACCESSOR_CLASS", "su.sergey.contacts.util.dao.SqlOutAccessor");
    }

    public static String getDaoExceptionClass() {
        return properties.getProperty("DAO_EXCEPTION_CLASS", "su.sergey.contacts.util.dao.DAOException");
    }
}
