package su.sergey.contacts.util.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import su.sergey.contacts.validation.ErrorCodes;
import su.sergey.contacts.valueobjects.validation.FieldValidationError;

/**
 * ����������� <tt>MultipleFieldsValidationException</tt> � <tt>FieldValidationInfoList</tt>.
 * 
 * @author ������ ��������
 */
public class MultipleFieldsValidationExceptionToFieldValidationInfoList {
	private static final Map messages = new HashMap();
	static {
		messages.put(new Integer(ErrorCodes.NULL_VALUE), "���������� ������ ��������");
		messages.put(new Integer(ErrorCodes.CHAR_FIELD_SIZE_EXCEEDED), "��������� �������� ����������� ����������� ��������� �����");
		messages.put(new Integer(ErrorCodes.FOREIGN_KEY_VIOLATION), "����������� ��������� ��������");
		messages.put(new Integer(ErrorCodes.NUMBER_FORMAT_VALIDATION), "����������� ����� �����");
		messages.put(new Integer(ErrorCodes.DATE_FORMAT_VALIDATION), "����������� ����� ���� (���������� ������ ��.��.����)");
        messages.put(new Integer(ErrorCodes.CHAR_FIELD_SIZE_TOO_SMALL), "��������� �������� ������ ���������� ��������� �����");
        messages.put(new Integer(ErrorCodes.DATE_OUT_OF_RANGE), "���� ��������� ��� ����������� ���������");
        messages.put(new Integer(ErrorCodes.INTEGER_FIELD_SIZE_EXCEEDED), "��������� ����� ��������� ����������� ���������� ��������");
        messages.put(new Integer(ErrorCodes.CURRENCY_FIELD_SIZE_EXCEEDED), "��������� �������� ����������� ����������� ��������� ������");
        messages.put(new Integer(ErrorCodes.EMPTY_FIELD), "���� �� ���������");
	}

    /**
     * ����������� <tt>MultipleFieldsValidationException</tt> � <tt>FieldValidationInfoList</tt>.
     * @param multipleFieldsValidationException  ������ ������
     * @return ������ <tt>FieldValidationInfo</tt>
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
     * ���������� �������� ����, � ���������� �������� ���� ������� ������.
     * @param   error ������
     * @return  �������� ����, � ���������� �������� ���� ������� ������
     */
    private static String getFieldTitle(FieldValidationError error) {
        return error.getFieldName();
    }

    /**
     * ���������� ��������� �� ������.
     * @param   error ������
     * @return  ��������� �� ������
     */
    private static String getMessage(FieldValidationError error) {
        int errorCode = error.getErrorCode();
        String message = (String)messages.get(new Integer(errorCode));
        if(message == null) {
        	message = error.getMessage();
        }
        if(message == null) {
        	message = "������� ������ ����������";
        }
        return message;
    }
}
