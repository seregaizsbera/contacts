package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;


/**
 * BaseValidator
 * 
 * @author Сергей Богданов
 */
public abstract class BaseValidator implements Validator {
    private String fieldName;

    protected BaseValidator() {}

    protected BaseValidator(String fieldName) {
        this.fieldName = fieldName;
    }

    protected void _setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
