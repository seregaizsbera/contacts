package su.sergey.contacts.birthdays;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import su.sergey.contacts.JNDINames;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.model.DateBounds;
import su.sergey.contacts.birthdays.view.BirthdaysForm;
import su.sergey.contacts.properties.PropertyNames;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.sessionfacade.businessdelegate.impl.DefaultDAOBusinessDelegate;
import su.sergey.contacts.util.Util;

/**
 * @author Сергей
 */
public class LookupBirthdays {
    private static final String PROPERTIES_FILE = "sas.client.properties";
    private static final String PARAMETERS_FILE = "parameters.properties";
    private static final String DAYS_BEFORE_MONTH = "days.before.months";
    private final DAOBusinessDelegate businessDelegate;
    private static int daysBeforeMonth;
    private final Calendar calendar;
    private final Date toDay;

    private DateBounds getBounds() throws ParseException {
        Date currentDate = calendar.getTime();
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.add(Calendar.DATE, daysBeforeMonth);
        int newMonth = calendar.get(Calendar.MONTH);
        if (newMonth != currentMonth) {
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 1);
            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
        } else {
            calendar.setTime(currentDate);
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                calendar.add(Calendar.DATE, 1);
            }
        }
        Date endDate = calendar.getTime();
        DateBounds result = new DateBounds(currentDate, endDate);
        return result;
    }

    private LookupBirthdays() throws ParseException {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        toDay = calendar.getTime();
        businessDelegate = new DefaultDAOBusinessDelegate(JNDINames.DAO_SESSION_FACADE_BEAN);
    }

    private void perform() throws PropertyNotFoundException, ParseException {
        if(!needCheck()) {
            return;
        }
        DateBounds bounds = getBounds();
        BirthdaysContent content = new BirthdaysContent(bounds);
        BirthdaysForm form = new BirthdaysForm(content);
        form.setVisible(true);
        businessDelegate.setSystemPropertyValue(PropertyNames.LAST_BIRTHDAYS_CHECK, toDay);
    }

    public static void main(String[] args) {
        int retval = 0;
        try {
            init(args);
            LookupBirthdays performer = new LookupBirthdays();
            performer.perform();
        }
        catch(Exception e) {
            Util.showException(e);
            retval = 1;
        }
        System.exit(retval);
    }

    private boolean needCheck() throws PropertyNotFoundException, java.text.ParseException {
        Date lastCheck = (Date)businessDelegate.getSystemPropertyValue(PropertyNames.LAST_BIRTHDAYS_CHECK);
        boolean needCheck = toDay.after(lastCheck);
        return needCheck;
    }

    private static void init(String[] args) throws IOException, ParseException {
        ClassLoader classLoader = LookupBirthdays.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream(PROPERTIES_FILE);
        Properties properties = new Properties(System.getProperties());
        properties.load(input);
        input.close();
        if(args.length != 0) {
            input = new FileInputStream(args[0]);
            properties.load(input);
            input.close();
        }
        System.setProperties(properties);
        input = classLoader.getResourceAsStream(PARAMETERS_FILE);
        properties = new Properties();
        if (input != null) {
            properties.load(input);
            input.close();
        }
        String daysBeforeMonthStr = properties.getProperty(DAYS_BEFORE_MONTH, "3");
        daysBeforeMonth = Integer.parseInt(daysBeforeMonthStr);
    }
}
