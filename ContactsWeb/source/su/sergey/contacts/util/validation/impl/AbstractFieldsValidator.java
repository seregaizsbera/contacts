package su.sergey.contacts.util.validation.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import su.sergey.contacts.util.validation.FieldException;
import su.sergey.contacts.util.validation.FieldsValidator;

/**
 * Абстрактная реализация интерфейса <code>FieldsValidator</code>
 */
public class AbstractFieldsValidator implements FieldsValidator {

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
        return Integer.parseInt(value);
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
        return Integer.parseInt(value);
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
        return Integer.parseInt(value);
    }

    /**
     * Проверяет Дату.
     *
     * @param value Строка для проверки
     * @param pattern Шаблон для Даты
     * @return Дату, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
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
     * Проверяет <code>BigDecimal</code>.
     *
     * @param value Строка для проверки
     * @return <code>BigDecimal</code>, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    public BigDecimal validateBigDecimal(String value) throws FieldException {
        return new BigDecimal(value);
    }
}
