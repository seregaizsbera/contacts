package su.sergey.contacts.exceptions;

import java.util.Iterator;
import java.util.List;

import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * FieldValidationException
 * 
 * @author Сергей Богданов
 */
public class MultipleFieldsValidationException extends Exception {
    private List errors;

    public MultipleFieldsValidationException(List errors) {
        this.errors = errors;
    }

    public MultipleFieldsValidationException(String message, List errors) {
        super(message);
        this.errors = errors;
    }

    public List getFieldValidationErrors() {
        return errors;
    }

    public String getMessage() {
        StringBuffer res = new StringBuffer("Wrong fields:\n");
        for (Iterator i = errors.iterator(); i.hasNext();) {
            FieldValidationError error = (FieldValidationError)i.next();
            res.append(error.getFieldName()).append("\t").append(error.getErrorCode()).append("\t").append(error.getInvalidValue());
            if (i.hasNext()) {
                res.append("\n");
            }
        }
        return res.toString();
    }
}
