package su.sergey.contacts.validation;

import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.valueobjects.validation.DateOutOfRange;
import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * <p>Title: IS Clients</p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 1.0
 */

public class DateRangeValidator extends BaseValidator {
    static protected SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    static public Date DEFAULT_MIN_DATE;
    static public Date DEFAULT_MAX_DATE;

    private Date minDate;
    private Date maxDate;

    public DateRangeValidator(String fieldName) {
        this(fieldName, DEFAULT_MIN_DATE, DEFAULT_MAX_DATE);
    }

    public DateRangeValidator(String fieldName, Date maxDate) {
        this(fieldName, DEFAULT_MIN_DATE, maxDate);
    }

    public DateRangeValidator(String fieldName, Date minDate, Date maxDate) {
        super(fieldName);
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public FieldValidationError validate(Object obj) {
        if (obj == null || !(obj instanceof Date))
            return null;
        Date date = (Date) obj;
        if (date.after(maxDate) || date.before(minDate)) {
            return new DateOutOfRange(getFieldName(), ErrorCodes.DATE_OUT_OF_RANGE, obj);
        }
        else {
            return null;
        }
    }

    static {
        try {
            DEFAULT_MIN_DATE = sdf.parse("01.01.1900");
            DEFAULT_MAX_DATE = sdf.parse("31.12.3000");
        } catch (Exception ex) {
            //ignore
        }
    }
}
