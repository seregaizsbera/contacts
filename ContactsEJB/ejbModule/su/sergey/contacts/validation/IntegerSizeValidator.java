package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;
import su.sergey.contacts.valueobjects.validation.LargeString;
import su.sergey.contacts.valueobjects.validation.LargeInteger;
import java.math.BigDecimal;
/**
 * StringSizeValidator
 * @author 
 * @date 19.08.2002
 * @time 13:42:11
 */

public class IntegerSizeValidator extends BaseValidator {
    private long maxValue = Long.MAX_VALUE;

    public IntegerSizeValidator(String fieldName, long maxValue) {
        super(fieldName);
        this.maxValue = maxValue;
    }
    

    public FieldValidationError validate(Object o) {
      if (o == null) return null;
      if (o instanceof BigDecimal) {
          BigDecimal bd = (BigDecimal) o;
         if (bd.compareTo(new BigDecimal(String.valueOf(maxValue))) > 0) {
               return new LargeInteger(getFieldName(), ErrorCodes.INTEGER_FIELD_SIZE_EXCEEDED, o, maxValue);
         }
      }
      Number num = (Number)o;
        if (num.longValue() > maxValue || num.longValue() < 0) {
            return new LargeInteger(getFieldName(), ErrorCodes.INTEGER_FIELD_SIZE_EXCEEDED, o, maxValue);
        } else {
            return null;
        }
    }
}
