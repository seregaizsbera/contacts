package su.sergey.contacts.birthdays.model;

import java.util.Date;

/*
 * Created by IntelliJ IDEA.
 * User: sergey
 * Date: 17.11.2003
 * Time: 21:36:01
 * To change template for new class use
 * Code Style | Class Templates options (Tools | IDE Options).
 */

public class DateBounds {
    private Date date1;
    private Date date2;

    public DateBounds(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public Date getDate1() {
        return date1;
    }

    public Date getDate2() {
        return date2;
    }
}
