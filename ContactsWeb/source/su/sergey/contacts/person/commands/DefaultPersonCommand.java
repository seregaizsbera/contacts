package su.sergey.contacts.person.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.person.PersonParameters;
import su.sergey.contacts.util.PageParameters;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;
import su.sergey.contacts.util.validation.impl.AbstractFieldsValidator;

public abstract class DefaultPersonCommand extends AbstractCommand implements PageParameters, PersonParameters {
	private FieldsValidator validator;
	protected DefaultPersonCommand() {
		validator = new AbstractFieldsValidator();
	}
	
	protected Integer getPage(HttpServletRequest request) throws InvalidParameterException {
        String value;
        int result;
        try {
        	value = ParameterUtil.getString(request, PN_PAGE);
        	if (value == null) {
        		return null;
        	}
            result = validator.validateInt(value);
        } catch (FieldException e) {
            throw new InvalidParameterException("Неправильно указан параметр запроса", "Номер страницы");
        }
		return new Integer(result);
	}
}
