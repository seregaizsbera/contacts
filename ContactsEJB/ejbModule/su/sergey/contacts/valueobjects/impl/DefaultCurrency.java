package su.sergey.contacts.valueobjects.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import su.sergey.contacts.valueobjects.Currency;

/**
 * Дефалтовая имплементация интерфейса <code>Currency</code>.
 * @author 
 * @version 1.0
 */
public class DefaultCurrency implements Currency {

    /**
     * Порядок дробной части.
     */
    private int ORDER = 100;
    
    /**
     * Порядок дробной части (количество десятичных цифр)
     */
    private int ORDER_DEGREE = 2;
    
    /**
     * Целая часть.
     */
    private BigInteger integerValue;
    /**
     * Дробная часть.
     */
    private int fractionalValue;

    /**
     * Денежная величина.
     */
    private BigDecimal value;

    /**
     * Создает объект.
     */
    public DefaultCurrency() {
        this(new BigDecimal(0));
    }

    /**
     * Создает объект.
     */
    public DefaultCurrency(BigInteger integerValue, int fractionalValue) {
        this.integerValue = integerValue;
        this.fractionalValue = fractionalValue;
    }

    /**
     * Создает объект.
     */
    public DefaultCurrency(BigDecimal value) {
        this.value = value;
		if (value == null) {
		    this.integerValue = new BigInteger("0");
			this.fractionalValue = 0;
		    return;
		}
		String str = value.toString();
		int i = str.indexOf(".");
		if (i < 0) {
//		    this.integerValue = Integer.parseInt(str);
            this.integerValue = new BigInteger(str);
			this.fractionalValue = 0;
		} else {
		    //this.integerValue = Integer.parseInt(str.substring(0, i));
            this.integerValue = new BigInteger(str.substring(0, i));
			String left = str.substring(i + 1);
			if (left.length() >= 2) {
				this.fractionalValue = Integer.parseInt(left.substring(0, 2));
			} else {
				this.fractionalValue = Integer.parseInt(left);
			}
		}
    }

    /**
     * Устанавливает денежную величину.
     */
	public void setCurrency(BigDecimal value) {
        this.value = value;
	}

    /**
     * Возращает денежную величину.
     */
    public BigDecimal getCurrency() {
        return value;
    }

    /**
     * Возвращает целую часть.
     */
    public BigInteger getIntegerValue() {
        return integerValue;
    }

    /**
     * Устанавливает целую часть.
     */
    public void setIntegerValue(BigInteger integerValue) {
        this.integerValue = integerValue;
    }

    /**
     * Возвращает дробную часть.
     */
    public int getFractionalValue() {
        return fractionalValue;
    }

    /**
     * Устанавливает дробную часть.
     */
    public void setFractionalValue(int fractionalValue) {
        this.fractionalValue = fractionalValue;
    }
    /**
     * Возвращает объект, чей результат равен сложению двух денежных величин.
     * Обе величины в результате выполнения данной операции не изменяются.
     */
    public Currency add(Currency currency) {
        /*
        this.fractionalValue += currency.getFractionalValue();
        this.integerValue += currency.getIntegerValue() + (this.fractionalValue / ORDER);
        this.fractionalValue = this.fractionalValue % ORDER;
        */
        return this;
    }
    /**
     * Возвращает объект, чей результат равен вычитанию двух денежных величин.
     * Обе величины в результате выполнения данной операции не изменяются.
     */
    public Currency substruct(Currency currency) {
        return this;
    }

    public String toString() {
    	String fractionalStr = "" + getFractionalValue();
    	int fractionalStrLength = fractionalStr.length();
    	for(int i = 0; i < ORDER_DEGREE - fractionalStrLength; i++) {
    		fractionalStr = "0" + fractionalStr;
    	}
        return "" + getIntegerValue() + "." + fractionalStr;
    }
}
