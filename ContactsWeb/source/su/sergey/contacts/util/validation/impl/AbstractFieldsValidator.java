package su.sergey.contacts.util.validation.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;

/**
 * ����������� ���������� ���������� <code>FieldsValidator</code>
 */
public class AbstractFieldsValidator implements FieldsValidator {

    /**
     * ��������� ������.
     *
     * @param value ������ ��� ��������
     * @param maxSize ������������ ������ ����������� ������
     * @param canBeEmpty ���������� �����-�� ������ ���� ������.
     * @return ������ ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public String validateString(String value, int maxSize, boolean canBeEmpty)
            throws FieldException {
        return value;
    }

    /**
     * ��������� ������.
     *
     * @param value ������ ��� ��������
     * @param canBeEmpty ���������� �����-�� ������ ���� ������.
     * @return ������ ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public String validateString(String value, boolean canBeEmpty)
            throws FieldException {

        return value;
    }

    /**
     * ��������� ������.
     *
     * @param value ������ ��� ��������
     * @param size ������ ������ ����������� ������
     * @return ������ ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public String validateString(String value, int size)
            throws FieldException {
        return value;
    }

    /**
     * ��������� ����� �����.
     *
     * @param value ������ ��� ��������
     * @return ����� �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public int validateInt(String value) throws FieldException {
        return Integer.parseInt(value);
    }

    /**
     * ��������� ����� �����.
     *
     * @param value ������ ��� ��������
     * @param limit ��������� �������� (������� ��� ������ - � ����������� ��
     * ��������� <code>isMaxLimit</code>)
     * @param isMaxLimit <code>true</code> ���� ������� ������� ���������
     * ��������
     * @return ����� �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public int validateInt(String value, int limit, boolean isMaxLimit)
            throws FieldException {
        return Integer.parseInt(value);
    }

    /**
     * ��������� ����� �����.
     *
     * @param min ����������� �������� �����
     * @param max ������������ �������� �����
     * @return ����� �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public int validateInt(String value, int min, int max) throws FieldException {
        return Integer.parseInt(value);
    }

    /**
     * ��������� ����.
     *
     * @param value ������ ��� ��������
     * @param pattern ������ ��� ����
     * @return ����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public Date validateDate(String value, String pattern) throws FieldException {
    	Date result = null;
    	try {
    		result = new SimpleDateFormat(pattern).parse(value);
    	} catch (ParseException e) {
    		throw new FieldException(FieldException.FORMAT_ERROR);
    	}
        return result;
    }

    /**
     * ��������� <code>BigDecimal</code>.
     *
     * @param value ������ ��� ��������
     * @return <code>BigDecimal</code>, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public BigDecimal validateBigDecimal(String value) throws FieldException {
        return new BigDecimal(value);
    }
}
