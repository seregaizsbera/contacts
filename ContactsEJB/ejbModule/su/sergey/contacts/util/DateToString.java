package su.sergey.contacts.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString implements Serializable {
    private static final long MAX_TIME = 253402203600000L;
    private DateFormat sdf;
    private DateFormat stf;    
    
    public DateToString() {
        sdf = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_DATE_FORMAT);
        stf = new SimpleDateFormat(ContactsDateTimeFormat.DEFAULT_TIME_FORMAT);
    }
    
    public DateToString(DateFormat sdf, DateFormat stf) {
        this.sdf = sdf;
        this.stf = stf;
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
