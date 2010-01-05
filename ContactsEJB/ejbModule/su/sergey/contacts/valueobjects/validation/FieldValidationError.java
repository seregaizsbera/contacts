package su.sergey.contacts.valueobjects.validation;

import java.io.Serializable;

/**
 * FieldValidationError
 * @author Сергей Богданов
 * @date 19.08.2002
 * @time 11:52:51
 */

public class FieldValidationError implements Serializable {
    static private final String DEF_FIELD_NAME = "N/A";
    static private final int DEF_ERROR_CODE = 0;

    private String fieldName;
    private int errorCode;
    private Object invalidValue = null;
    private String message = null;

    public FieldValidationError(String fieldName, int errorCode) {
        this.fieldName = fieldName;
        this.errorCode = errorCode;
    }

    public FieldValidationError(String message) {
        this.fieldName = DEF_FIELD_NAME;
        this.errorCode = DEF_ERROR_CODE;
        this.message = message;
    }

    public FieldValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.errorCode = DEF_ERROR_CODE;
        this.message = message;
    }
    
    public FieldValidationError(String fieldName, String message, Object invalidValue) {
        this.fieldName = fieldName;
        this.errorCode = DEF_ERROR_CODE;
        this.message = message;
        this.invalidValue = invalidValue;
    }

    public FieldValidationError(String fieldName, int errorCode, Object invalidValue) {
        this.fieldName = fieldName;
        this.errorCode = errorCode;
        this.invalidValue = invalidValue;
    }

    protected void _setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    protected void _setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    protected void _setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }

    /**
     * Возвращает имя некоректного свойства.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Возвращает код ошибки.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Возвращает неправильное значение.
     */
    public Object getInvalidValue() {
        return invalidValue;
    }
	/**
	 * Gets the message
	 * @return Returns a String
	 */
	public String getMessage() {
		return message;
	}
}
