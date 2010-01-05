package su.sergey.contacts.valueobjects.validation;

/**
 * <p>Title: IS Clients</p>
 * <p>Description: </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author Сергей Богданов
 * @version 1.0
 */

public class DateOutOfRange extends FieldValidationError {

    public DateOutOfRange(String fieldName, int errorCode) {
        super(fieldName, errorCode);
    }

    public DateOutOfRange(String fieldName, int errorCode, Object invalidValue) {
        super(fieldName, errorCode, invalidValue);
	}
}
