package su.sergey.contacts.supply.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.supply.SupplyParameters;
import su.sergey.contacts.util.PageParameters;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;
import su.sergey.contacts.util.validation.impl.AbstractFieldsValidator;

public abstract class DefaultSupplyCommand extends AbstractCommand implements PageParameters, SupplyParameters {
	private FieldsValidator validator;
	protected DefaultSupplyCommand() {
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
