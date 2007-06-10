package su.sergey.contacts.util.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractSearchDAO extends AbstractDAO {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Constructor for AbstractSearchDAO
     */
    protected AbstractSearchDAO(ConnectionSource connectionSource) {
        super(connectionSource);
    }
	
    protected static final String makeLike(String s) {
        return "\'" + DAOUtil.convertSearchString(s, true) + "\'";
    }

    protected static final String makeEqual(String s) {
        return "\'" + DAOUtil.convertSearchString(s, false) + "\'";
    }

    protected static final String makeDate(Date d) {
        return "\'" + DATE_FORMAT.format(d) + "\'";
    }
}
