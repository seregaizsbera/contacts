package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;
import su.sergey.contacts.valueobjects.validation.LargeString;

/**
 * StringSizeValidator
 * @author 
 * @date 19.08.2002
 * @time 13:42:11
 */

public class StringSizeValidator extends BaseValidator {
	private int minStringSize = -1;
    private int maxStringSize = 0;

    public StringSizeValidator(String fieldName, int maxStringSize) {
        super(fieldName);
        this.maxStringSize = maxStringSize;
    }

    public StringSizeValidator(String fieldName, int minStringSize, int maxStringSize) {
        super(fieldName);
        this.maxStringSize = maxStringSize;
        this.minStringSize = minStringSize;
    }
     
    public FieldValidationError validate(Object o) {
        if (o != null && ((String)o).length() > maxStringSize) {
            return new LargeString(getFieldName(), ErrorCodes.CHAR_FIELD_SIZE_EXCEEDED, o, maxStringSize);
        } else if(o != null && ((String)o).length() < minStringSize) {
            return new LargeString(getFieldName(), ErrorCodes.CHAR_FIELD_SIZE_TOO_SMALL, o, minStringSize);
        } else {
            return null;
        }
    }
}
