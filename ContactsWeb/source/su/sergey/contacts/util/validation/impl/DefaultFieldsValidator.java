package su.sergey.contacts.util.validation.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;

/**
 * ���������� ����������.
 */
public class DefaultFieldsValidator extends AbstractFieldsValidator {
    /*������������ ��������� ����������*/
    private static FieldsValidator instance = new DefaultFieldsValidator();

    /**
     * ���������� ���������
     *
     * @return ������ ����������
     */
    public static FieldsValidator getInstance() {
        return instance;
    }

    /*�����������*/
    private DefaultFieldsValidator() {}

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
        validateString(value, canBeEmpty);
        if (value.length() > maxSize) {
            throw new FieldException(FieldException.SIZE_ERROR);
        }
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
        if (value != null) {
            if ((!canBeEmpty) && (value.trim().length() == 0)) {
                throw new FieldException(FieldException.EMPTY_ERROR);
            }
        } else {
            throw new FieldException(FieldException.NULL_ERROR);
        }
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
        validateString(value, true);
        if (value.length() != size) {
            throw new FieldException(FieldException.SIZE_ERROR);
        }
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
        validateString(value, false);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
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
        int i;
        i = validateInt(value);
        if ((isMaxLimit && (i > limit)) || (!isMaxLimit && (i < limit))) {
            throw new FieldException(FieldException.SIZE_ERROR);
        }
        return i;
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
        int i;
        i = validateInt(value);
        if ((i < min) || (i > max)) {
            throw new FieldException(FieldException.SIZE_ERROR);
        }
        return i;
    }

    /**
     * ��������� ����.
     *
     * @param value ������ ��� ��������
     * @param pattern ������ ��� ����
     * @return ����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public Date validateDate(String value, String pattern)
            throws FieldException {
        StringTokenizer parser;
        DateFormat      dateFormat;
        Date            date;
        validateString(value, false);
        dateFormat = new SimpleDateFormat(pattern);
        ParsePosition pp = new ParsePosition(0);
        date = dateFormat.parse(value, pp);
        if (date == null) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        return date;
    }
    

    /**
     * ��������� �����.
     *
     * @param value ������ ��� ��������
     * @return �����, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public Date validateTime(String value, String pattern) throws FieldException {
        StringTokenizer parser;
        DateFormat      dateFormat;
        Date            time;
        validateString(value, false);
        parser = new StringTokenizer(value, ":");
        if (parser.countTokens() != 3) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        validateHours(parser.nextToken());
        validateMinutes(parser.nextToken());
        dateFormat = new SimpleDateFormat(pattern);
        try {
            time = dateFormat.parse(value);
        } catch (ParseException e) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        return time;
    }


    /*��������� ����*/
    private int validateHours(String value) throws FieldException {
        int hours;
        try {
            hours = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        if ((hours > 23) || (hours < 0)) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        return hours;
    }
    

    /*��������� ������*/
    private int validateMinutes(String value) throws FieldException {
        int minutes;
        try {
            minutes = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        if ((minutes > 59) || (minutes < 0)) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
        return minutes;
    }
    
    /**
     * ��������� <code>BigDecimal</code>.
     *
     * @param value ������ ��� ��������
     * @return <code>BigDecimal</code>, ��������� ��������
     * @throws FieldException ���� ������ �� ������������� �����������
     */
    public BigDecimal validateBigDecimal(String value) throws FieldException {
        validateString(value, false);
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            throw new FieldException(FieldException.FORMAT_ERROR);
        }
    }
}
