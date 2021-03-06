package su.sergey.contacts.birthdays;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.model.DateBounds;
import su.sergey.contacts.birthdays.model.NamingContextDAOBusinessDelegate;
import su.sergey.contacts.birthdays.model.NamingUtil;
import su.sergey.contacts.birthdays.view.BirthdaysForm;
import su.sergey.contacts.properties.PropertyNames;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.Util;

/**
 * @author ๓ลาวลส
 */
public class LookupBirthdays {
    private static final String SAS_CLIENT_FILE = "sas.client.properties";
    private static final String PARAMETERS_FILE = "parameters.properties";
    private static final String DAYS_BEFORE_MONTH = "days.before.month";
    private static final String DAYS_BEGINNING_MONTH = "days.beginning.month";
    private static final String NAME_SERVICE = "su.sergey.contacts.birthdays.nameService";
    private static final String ROOT_PREFIX = "su.sergey.contacts.birthdays.rootPrefix";
    private final DAOBusinessDelegate businessDelegate;
    private final Calendar calendar;
    private final Date toDay;
    private final NamingContext context;
    private int daysBeforeMonth;
    private int daysBeginningMonth;
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
        } else if (currentDay <= daysBeginningMonth) {
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

    private LookupBirthdays(String args[]) throws ParseException, IOException, org.omg.CORBA.ORBPackage.InvalidName, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName {
        Properties properties = init(args);
	ORB orb = ORB.init(args, properties);
        org.omg.CORBA.Object object = orb.resolve_initial_references(properties.getProperty(NAME_SERVICE, "NameService"));
        NamingContext ctx = NamingContextHelper.narrow(object);
	String rootPrefix = properties.getProperty(ROOT_PREFIX);
	if (rootPrefix != null && !rootPrefix.equals("")) {
	    object = ctx.resolve(NamingUtil.toCorbaName(rootPrefix));
	    ctx = NamingContextHelper.narrow(object);
	}
	context = ctx;
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        toDay = calendar.getTime();
        businessDelegate = new NamingContextDAOBusinessDelegate(context, JNDINames.DAO_SESSION_FACADE_BEAN);
    }

    private void perform() throws PropertyNotFoundException, ParseException {
        if(!needCheck()) {
            return;
        }
        DateBounds bounds = getBounds();
        BirthdaysContent content = new BirthdaysContent(bounds, context);
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

    private Properties init(String[] args) throws IOException, ParseException {
        ClassLoader classLoader = LookupBirthdays.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream(SAS_CLIENT_FILE);
        Properties properties = new Properties();
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
        input = classLoader.getResourceAsStream(PARAMETERS_FILE);
        loadProperties(properties, input);
        String daysBeforeMonthStr = properties.getProperty(DAYS_BEFORE_MONTH, "3");
        String daysBeginningMonthStr = properties.getProperty(DAYS_BEGINNING_MONTH, "3");
        daysBeforeMonth = Integer.parseInt(daysBeforeMonthStr);
        daysBeginningMonth = Integer.parseInt(daysBeginningMonthStr);
	System.setProperty("traceSettingsFile", properties.getProperty("traceSettingsFile", "none"));
	return properties;
    }

    private static void loadProperties(Properties properties, InputStream input) throws IOException {
        if (input != null) {
            properties.load(input);
            input.close();
        }
    }
}
