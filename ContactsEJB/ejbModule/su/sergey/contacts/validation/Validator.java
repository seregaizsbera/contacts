package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * Validator
 * 
 * @author ������ ��������
 */
public interface Validator {
    String getFieldName();
    FieldValidationError validate(Object o);
}
