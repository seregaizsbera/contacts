package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * Проверяет, что строка содержит только цифры
 */
public class NumberValidator extends BaseValidator {
	public NumberValidator(String fieldName) {
		super(fieldName);
	}
    
	public FieldValidationError validate(Object o) {
		FieldValidationError error = null;
		char ch;
		String str;
		if (o != null) {
			str = o.toString();
			for (int i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (!Character.isDigit(ch)) {
					error =
						new FieldValidationError(
							getFieldName(),
							ErrorCodes.NUMBER_FORMAT_VALIDATION,
							o);
					break;
				}
			}
		}
		return error;
	}
}
