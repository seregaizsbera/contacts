package su.sergey.contacts.util;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateToString implements Serializable {
    private static final long MAX_TIME = 253402203600000L;
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private SimpleDateFormat sdf;

    private String TIME_FORMAT = "HH:mm";
    private SimpleDateFormat stf;    
    
    public DateToString() {
        sdf = new SimpleDateFormat(DATE_FORMAT);
        stf = new SimpleDateFormat(TIME_FORMAT, Locale.ENGLISH);
    }
    
    public Date toDate(String stringDate) {
        ParsePosition pos = new ParsePosition(0);
        Date date = sdf.parse(stringDate, pos);
        if ((pos.getIndex() == 0)
                || (pos.getIndex() < stringDate.trim().length())
                || (date.getTime() > MAX_TIME)) {
            return null;
        }
        return date;
    }

    public String dateToString(Date date) {
    	if (date != null) {
            return sdf.format(date);
    	} else {
    		return "&nbsp;";
    	}
    }


    public String dateToHTMLString(Date date) {
    	if (date != null) {
            return sdf.format(date);
    	} else {
    		return "&nbsp;";
    	}
    }
    
    public String timeToHTMLString(Date date) {
    	if (date != null) {
            return stf.format(date);
    	} else {
    		return "&nbsp;";
    	}
    }
}

