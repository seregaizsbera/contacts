package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;
import su.sergey.contacts.valueobjects.validation.LargeString;
import su.sergey.contacts.valueobjects.validation.LargeInteger;

/**
 * StringSizeValidator
 * @author 
 * @date 19.08.2002
 * @time 13:42:11
 */

public class NotNullValidator extends BaseValidator {
    public NotNullValidator(String fieldName) {
        super(fieldName);
    }
    
    public FieldValidationError validate(Object o) {
        if (o == null) {
            return new FieldValidationError(getFieldName(), ErrorCodes.NULL_VALUE, o);
        } else {
            return null;
        }
    }
}
