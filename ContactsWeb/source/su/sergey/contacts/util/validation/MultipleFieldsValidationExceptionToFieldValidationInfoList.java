package su.sergey.contacts.util.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import su.sergey.contacts.validation.ErrorCodes;
import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * Преобразует <tt>MultipleFieldsValidationException</tt> в <tt>FieldValidationInfoList</tt>.
 * 
 * @author Сергей Богданов
 */
public class MultipleFieldsValidationExceptionToFieldValidationInfoList {
	private static final Map messages = new HashMap();
	static {
		messages.put(new Integer(ErrorCodes.NULL_VALUE), "необходимо ввести значение");
		messages.put(new Integer(ErrorCodes.CHAR_FIELD_SIZE_EXCEEDED), "введенное значение превосходит максимально возможную длину");
		messages.put(new Integer(ErrorCodes.FOREIGN_KEY_VIOLATION), "неправильно введенное значение");
		messages.put(new Integer(ErrorCodes.NUMBER_FORMAT_VALIDATION), "неправильно ввели число");
		messages.put(new Integer(ErrorCodes.DATE_FORMAT_VALIDATION), "неправильно ввели дату (допустимый формат дд.мм.гггг)");
        messages.put(new Integer(ErrorCodes.CHAR_FIELD_SIZE_TOO_SMALL), "введенное значение короче минимально возможной длины");
        messages.put(new Integer(ErrorCodes.DATE_OUT_OF_RANGE), "дата находится вне допустимого диапазона");
        messages.put(new Integer(ErrorCodes.INTEGER_FIELD_SIZE_EXCEEDED), "введенное число превышает максимально допустимое значение");
        messages.put(new Integer(ErrorCodes.CURRENCY_FIELD_SIZE_EXCEEDED), "введенное значение превосходит максимально возможный размер");
        messages.put(new Integer(ErrorCodes.EMPTY_FIELD), "Поле не заполнено");
	}

    /**
     * Преобразует <tt>MultipleFieldsValidationException</tt> в <tt>FieldValidationInfoList</tt>.
     * @param multipleFieldsValidationException  список ошибок
     * @return список <tt>FieldValidationInfo</tt>
     */
    public static List transform(List multipleFieldsValidationException) {
        List fieldValidationInfoList = null;
        if (multipleFieldsValidationException != null) {
            int size = multipleFieldsValidationException.size();
            fieldValidationInfoList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                FieldValidationError error =
                    (FieldValidationError) multipleFieldsValidationException.get(i);
                String fieldTitle = getFieldTitle(error);
                String message = getMessage(error);
                fieldValidationInfoList.add(new FieldValidationInfo(fieldTitle, message));
            }
        }
        return fieldValidationInfoList;
    }

    /**
     * Возвращает название поле, в заполнении которого была сделана ошибка.
     * @param   error ошибка
     * @return  название поле, в заполнении которого была сделана ошибка
     */
    private static String getFieldTitle(FieldValidationError error) {
        return error.getFieldName();
    }

    /**
     * Возвращает сообщение об ошибке.
     * @param   error ошибка
     * @return  сообщение об ошибке
     */
    private static String getMessage(FieldValidationError error) {
        int errorCode = error.getErrorCode();
        String message = (String)messages.get(new Integer(errorCode));
        if(message == null) {
        	message = error.getMessage();
        }
        if(message == null) {
        	message = "причина ошибки неизвестна";
        }
        return message;
    }
}
