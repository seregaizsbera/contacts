package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.*;
import su.sergey.contacts.valueobjects.*;

/**
 *
 *
 * Author: 
 * Date: 07.10.2002
 * */
public class CurrencyValidator extends BaseValidator {

    private int integerSize = 28;
    private int fractionalSize = 2;

    public CurrencyValidator(String fieldName) {
        super(fieldName);
    }
    
    public CurrencyValidator(String fieldName, int integerSize, int fractionalSize) {
        super(fieldName);

        this.integerSize = integerSize;
        this.fractionalSize = fractionalSize;
    }

    public FieldValidationError validate(Object o) {
        FieldValidationError error = null;
        Currency             currency;

        System.err.println("Validating getFieldName() = " + getFieldName() +
                " value = " + o);
        if (o != null) {
            currency = (Currency) o;
            if (currency.getIntegerValue().toString().length() > integerSize) {
                error = new FieldValidationError(getFieldName(),
                        ErrorCodes.CURRENCY_FIELD_SIZE_EXCEEDED, o);
            }
        }

        return error;
    }
}
