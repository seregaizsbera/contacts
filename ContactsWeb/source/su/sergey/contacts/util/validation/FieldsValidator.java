package su.sergey.contacts.util.validation;

import java.math.BigDecimal;
import java.util.Date;

public interface FieldsValidator {

    /**
     * Проверяет строку.
     *
     * @param value Строка для проверки
     * @param maxSize Максимальный размер проверяемой строки
     * @param canBeEmpty Определяет может-ли строка быть пустой.
     * @return Строка прошедшая проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    String validateString(String value, int maxSize, boolean canBeEmpty)
            throws FieldException;

    /**
     * Проверяет строку.
     *
     * @param value Строка для проверки
     * @param canBeEmpty Определяет может-ли строка быть пустой.
     * @return Строка прошедшая проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    String validateString(String value, boolean canBeEmpty)
            throws FieldException;

    /**
     * Проверяет строку.
     *
     * @param value Строка для проверки
     * @param size Точный размер проверяемой строки
     * @return Строка прошедшая проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    String validateString(String value, int size)
            throws FieldException;

    /**
     * Проверяет целое число.
     *
     * @param value Строка для проверки
     * @return Целое число, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    int validateInt(String value) throws FieldException;

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
    int validateInt(String value, int limit, boolean isMaxLimit)
            throws FieldException;

    /**
     * Проверяет целое число.
     *
     * @param min Минимальное значение числа
     * @param max Максимальное значение числа
     * @return Целое число, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    int validateInt(String value, int min, int max) throws FieldException;

    /**
     * Проверяет Дату.
     *
     * @param value Строка для проверки
     * @param pattern Шаблон для Даты
     * @return Дату, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    Date validateDate(String value, String pattern) throws FieldException;

    /**
     * Проверяет <code>BigDecimal</code>.
     *
     * @param value Строка для проверки
     * @return <code>BigDecimal</code>, прошедшее проверку
     * @throws FieldException Если строка не удовлетворяет требованиям
     */
    BigDecimal validateBigDecimal(String value) throws FieldException;
}
