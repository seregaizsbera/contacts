package su.sergey.contacts.valueobjects.validation;

/**
 * LargeInteger
 * @author 
 * @date 19.08.2002
 * @time 12:28:06
 */

public class LargeInteger extends FieldValidationError {
    private long maxValue;

    public LargeInteger(String fieldName, int errorCode, long maxValue) {
        super(fieldName, errorCode);
        this.maxValue = maxValue;
    }

    public LargeInteger(String fieldName, int errorCode, Object invalidValue, long maxValue) {
        super(fieldName, errorCode, invalidValue);
        this.maxValue = maxValue;
    }

    public long getMaxValue() {
        return maxValue;
    }
}
