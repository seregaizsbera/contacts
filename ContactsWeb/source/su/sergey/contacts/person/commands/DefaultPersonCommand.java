package su.sergey.contacts.person.commands;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.util.ContactsDateTimeFormat;
import su.sergey.contacts.util.PageParameters;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;
import su.sergey.contacts.util.validation.impl.AbstractFieldsValidator;

public abstract class DefaultPersonCommand extends AbstractCommand implements PageParameters {
	private static final String PN_ADDRESS = "address";
	private static final String PN_AFTER_BIRTHDAY = "afterBirthday";
	private static final String PN_BEFORE_BIRTHDAY = "beforeBirthday";
	private static final String PN_EMAIL = "email";
	private static final String PN_FIRST_NAME = "firstName";
	private static final String PN_LAST_NAME = "lastName";
	private static final String PN_MIDDLE_NAME = "middleName";
	private static final String PN_ICQ = "icq";
	private static final String PN_PHONE = "phone";
	private static final String PN_MONTH_OF_BIRTHDAY = "monthOfBirthday";
	private static final String DATE_FORMAT = ContactsDateTimeFormat.DEFAULT_DATE_FORMAT;
	private static final String PN_PAGE = "page";
	
	protected final static String AN_SEARCH_PARAMETERS = "searchParameters";
	protected final static String ANS_PERSONS_ITERATOR = "personsIterator";
	protected final static String AN_PERSONS = "persons";
	
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
	
	protected String getAddress(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_ADDRESS);
	}
	
	protected String getEmail(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_EMAIL);
	}
	
	protected String getIcq(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_ICQ);
	}
	
	protected String getFirstName(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_FIRST_NAME);
	}
	
	protected String getLastName(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_LAST_NAME);
	}
	
	protected String getMiddleName(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_MIDDLE_NAME);
	}
	
	protected String getPhone(HttpServletRequest request) {
		return ParameterUtil.getString(request, PN_PHONE);
	}
	
	protected int getMonthOfBirthday(HttpServletRequest request) throws InvalidParameterException {
        int result;
        String value;
        try {
        	value = ParameterUtil.getString(request, PN_MONTH_OF_BIRTHDAY);
        	if (value == null) {
        		return 0;
        	}
            result = validator.validateInt(value);
        } catch (FieldException e) {
            throw new InvalidParameterException("Неправильно указан параметр поиска", "Месяц рождения");
        }
		return result;
	}
	
	protected Date getAfterBirthday(HttpServletRequest request) throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_AFTER_BIRTHDAY), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, DATE_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметры поиска", "День рождения после...");
        }
        return result;
	}
	
	protected Date getBeforeBirthday(HttpServletRequest request) throws InvalidParameterException {
        Date result;
        String value;
        try {
            value = validator.validateString(request.getParameter(PN_BEFORE_BIRTHDAY), true);
            result = (value.length() > 0)
                     ? validator.validateDate(value, DATE_FORMAT)
                     : null;
        } catch (FieldException e) {
            throw new InvalidParameterException("Введен неправильный параметры поиска", "День рождения после...");
        }
        return result;
	}
}
