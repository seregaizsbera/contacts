package su.sergey.contacts.valueobjects.validation;

/**
 * LargeString
 * @author 
 * @date 19.08.2002
 * @time 12:01:26
 */

public class LargeString extends FieldValidationError {
    private int maxLength;

    public LargeString(String fieldName, int errorCode, int maxLength) {
        super(fieldName, errorCode);
        this.maxLength = maxLength;
    }

    public LargeString(String fieldName, int errorCode, Object invalidValue, int maxLength) {
        super(fieldName, errorCode, invalidValue);
        this.maxLength = maxLength;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
