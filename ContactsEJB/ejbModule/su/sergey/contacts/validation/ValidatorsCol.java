package su.sergey.contacts.validation;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ValidatorsCol
 * @author Сергей Богданов
 * @date 19.08.2002
 * @time 18:11:52
 */

public class ValidatorsCol {
	private Map validators;

	public ValidatorsCol() {
		this.validators = new HashMap();
	}

	public List validate(List errors, String fieldName, Object value) {
		List res = errors;
		if (validators.get(fieldName) != null
			&& validators.get(fieldName) instanceof List) {
			List list = (List) validators.get(fieldName);
			for (Iterator i = list.iterator(); i.hasNext();) {
				res = validate(res, value, (Validator) i.next());
			}
		} else
			if (validators.get(fieldName) != null
				&& validators.get(fieldName) instanceof Validator) {
				res = validate(res, value, (Validator) validators.get(fieldName));
			}
		return res;
	}
	
	public void addValidator(String fieldName, Validator validator) {
		if (validators.get(fieldName) != null
			&& validators.get(fieldName) instanceof Validator) {
			List col = new ArrayList();
			col.add(validators.get(fieldName));
			col.add(validator);
			validators.put(fieldName, col);
		} else
			if (validators.get(fieldName) != null
				&& validators.get(fieldName) instanceof List) {
				((List) validators.get(fieldName)).add(validator);
			} else {
				validators.put(fieldName, validator);
			}

	}

	private List validate(List errors, Object value, Validator validator) {
		List res = errors;
		FieldValidationError fieldValidationError = validator.validate(value);
		if (fieldValidationError != null) {
			if (res == null) {
				res = new ArrayList();
			}
			res.add(fieldValidationError);
		}
		return res;
	}

	public static final List addErrors(List oldErrors, List newErrors) {
		if (newErrors != null) {
			if (oldErrors != null) {
				oldErrors.addAll(newErrors);
				return oldErrors;
			} else {
				return newErrors;
			}
		} else {
			return oldErrors;
		}
	}
	
	public static final List addError(List errors, FieldValidationError error) {
		List result = errors;
		if (error != null) {
			if (errors == null) {
				result = new ArrayList();
			}
			result.add(error);
		}
		return result;
	}
}
