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
    private static final String SAS_CLIENT_FILE = "sas.client.properties";
    private static final String J2EE_FILE = "j2ee.properties";
    private static final String PARAMETERS_FILE = "parameters.properties";
    private static final String DAYS_BEFORE_MONTH = "days.before.month";
    private static final String DAYS_BEGINING_MONTH = "days.begining.month";
    private final DAOBusinessDelegate businessDelegate;
    private int daysBeforeMonth;
    private int daysBeginingMonth;
    private final Calendar calendar;
    private final Date toDay;
    private boolean forceBirthdays;

    private DateBounds getBounds() throws ParseException {
        Date currentDate = calendar.getTime();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, daysBeforeMonth);
        int newMonth = calendar.get(Calendar.MONTH);
        if (newMonth != currentMonth) {
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 1);
            int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
        } else if (currentDay <= daysBeginingMonth) {
            calendar.setTime(currentDate);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date beginDate = calendar.getTime();
            calendar.setTime(currentDate);
            currentDate = beginDate;
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

    private LookupBirthdays(String args[]) throws ParseException, IOException {
        init(args);
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
        if (content.hasNext()) {
            BirthdaysForm form = new BirthdaysForm(content);
            form.setVisible(true);
        }
        businessDelegate.setSystemPropertyValue(PropertyNames.LAST_BIRTHDAYS_CHECK, toDay);
    }

    public static void main(String[] args) {
        int retval = 1;
        try {
            LookupBirthdays performer = new LookupBirthdays(args);
            performer.perform();
            retval = 0;
        } catch (Exception e) {
            Util.showException(e);
        } catch (Throwable e) {
                e.printStackTrace();
        } finally {
            System.exit(retval);
        }
    }

    private boolean needCheck() throws PropertyNotFoundException, ParseException {
        if (forceBirthdays) {
	    return true;
	}
        Date lastCheck = (Date) businessDelegate.getSystemPropertyValue(PropertyNames.LAST_BIRTHDAYS_CHECK);
        boolean needCheck = toDay.after(lastCheck);
        return needCheck;
    }

    private void init(String[] args) throws IOException, ParseException {
        ClassLoader classLoader = LookupBirthdays.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream(SAS_CLIENT_FILE);
        Properties properties = new Properties(System.getProperties());
        loadProperties(properties, input);
        input = classLoader.getResourceAsStream(J2EE_FILE);
        loadProperties(properties, input);
        if (args.length != 0) {
            input = new FileInputStream(args[0]);
            loadProperties(properties, input);
        }
        forceBirthdays = false;
	for (int i = 1; i < args.length; i++) {
	    if (args[i].equals("-force")) {
	        forceBirthdays = true;
		break;
	    }
	}
        System.setProperties(properties);
        properties = new Properties();
        input = classLoader.getResourceAsStream(PARAMETERS_FILE);
        loadProperties(properties, input);
        String daysBeforeMonthStr = properties.getProperty(DAYS_BEFORE_MONTH, "3");
        String daysBeginingMonthStr = properties.getProperty(DAYS_BEGINING_MONTH, "3");
        daysBeforeMonth = Integer.parseInt(daysBeforeMonthStr);
        daysBeginingMonth = Integer.parseInt(daysBeginingMonthStr);
    }

    private static void loadProperties(Properties properties, InputStream input) throws java.io.IOException {
        if (input != null) {
            properties.load(input);
            input.close();
        }
    }
}
