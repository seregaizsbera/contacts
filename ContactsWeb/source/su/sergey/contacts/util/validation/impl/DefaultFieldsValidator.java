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
 * Реализация валидатора.
 */
public class DefaultFieldsValidator extends AbstractFieldsValidator {
    /*Единственный экземпляр валидатора*/
    private static FieldsValidator instance = new DefaultFieldsValidator();

    /**
     * Возвращает валидатор
     *
     * @return Объект валидатора
     */
    public static FieldsValidator getInstance() {
        return instance;
    }

    /*Конструктор*/
    private DefaultFieldsValidator() {}

    /**
     * Проверяет строку.
     *
     * @param value Строка для проверки
     * @param maxSize Максимальный размер проверяемой строки
     * @param canBeEmpty Определяет может-ли строка быть пустой.
     * @return Строка прошедшая проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет строку.
     *
     * @param value Строка для проверки
     * @param canBeEmpty Определяет может-ли строка быть пустой.
     * @return Строка прошедшая проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет строку.
     *
     * @param value Строка для проверки
     * @param size Точный размер проверяемой строки
     * @return Строка прошедшая проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет целое число.
     *
     * @param value Строка для проверки
     * @return Целое число, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет целое число.
     *
     * @param value Строка для проверки
     * @param limit Граничное значение (верхнее или нижнее - в зависимости от
     * параметра <code>isMaxLimit</code>)
     * @param isMaxLimit <code>true</code> если указана верхнее граничное
     * значение
     * @return Целое число, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет целое число.
     *
     * @param min Минимальное значение числа
     * @param max Максимальное значение числа
     * @return Целое число, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет Дату.
     *
     * @param value Строка для проверки
     * @param pattern Шаблон для Даты
     * @return Дату, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет Время.
     *
     * @param value Строка для проверки
     * @return время, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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


    /*Проверяет часы*/
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
    

    /*Проверяет минуты*/
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
     * Проверяет <code>BigDecimal</code>.
     *
     * @param value Строка для проверки
     * @return <code>BigDecimal</code>, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
