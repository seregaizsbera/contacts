package su.sergey.contacts.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Содержит данные, представляющие денежную величину.
 * @author: 
 * @version: 1.0
 */
public interface Currency extends Serializable {

    /**
     * Устанавливает денежную величину.
     */
	void setCurrency(BigDecimal value);

    /**
     * Возращает денежную величину.
     */
    BigDecimal getCurrency();

    /**
     * Возвращает целую часть.
     */
    BigInteger getIntegerValue();

    /**
     * Устанавливает целую часть.
     */
    void setIntegerValue(BigInteger integerValue);

    /**
     * Возвращает дробную часть.
     */
    int getFractionalValue();

    /**
     * Устанавливает дробную часть.
     */
    void setFractionalValue(int fractionalValue);

    /**
     * Возвращает объект, чей результат равен сложению двух денежных величин.
     * Обе величины в результате выполнения данной операции не изменяются.
     */
    Currency add(Currency currency);

    /**
     * Возвращает объект, чей результат равен вычитанию двух денежных величин.
     * Обе величины в результате выполнения данной операции не изменяются.
     */
    Currency substruct(Currency currency);
}
