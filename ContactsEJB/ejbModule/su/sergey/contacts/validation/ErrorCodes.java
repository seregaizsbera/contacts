package su.sergey.contacts.validation;

/**
 * ErrorCodes
 * @author 
 * @date 19.08.2002
 * @time 13:46:50
 */

public interface ErrorCodes {
    int CHAR_FIELD_SIZE_EXCEEDED        = 1;
    int INTEGER_FIELD_SIZE_EXCEEDED     = 2;
    int NULL_VALUE                      = 3;
    int FOREIGN_KEY_VIOLATION           = 4;
    int NUMBER_FORMAT_VALIDATION        = 5;
    int DATE_FORMAT_VALIDATION          = 6;
    int CURRENCY_FIELD_SIZE_EXCEEDED    = 7;
    int DATE_OUT_OF_RANGE               = 8;
    int EMPTY_FIELD                     = 10;
    int CHAR_FIELD_SIZE_TOO_SMALL       = 12;
}
