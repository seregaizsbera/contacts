package su.sergey.contacts.valueobjects.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import su.sergey.contacts.valueobjects.Currency;

public class DefaultCurrency implements Currency {
    private static final int ORDER = 2;
    private BigDecimal value;

    public DefaultCurrency() {
        this.value = new BigDecimal(0);
    }

    public DefaultCurrency(BigDecimal value) {
	if (value == null) {
            value = new BigDecimal(0);
	}
	this.value = value.setScale(ORDER, BigDecimal.ROUND_HALF_UP);
    }

    public DefaultCurrency(String strValue) {
	this((strValue == null) ? null : new BigDecimal(strValue));
    }
	
    /**
     * @see Currency#setCurrency(BigDecimal)
     */
    public void setCurrency(BigDecimal value) {
    	this.value = value;
    }
    
    /**
     * @see Currency#getCurrency()
     */
    public BigDecimal getCurrency() {
        return value;
    }
    
    private BigDecimal getIntegerBigDecimalValue() {
    	BigInteger integerValue = getIntegerValue();
    	BigDecimal result = new BigDecimal(integerValue);
    	return result;
    }

    /**
     * @see Currency#getIntegerValue()
     */
    public BigInteger getIntegerValue() {
    	BigInteger result = value.toBigInteger();
        return result;
    }

    /**
     * @see Currency#setIntegerValue(BigInteger)
     */
    public void setIntegerValue(BigInteger integerValue) {
    	BigDecimal currentIntegerBigDecimal = getIntegerBigDecimalValue();
    	BigDecimal newValue = value.subtract(currentIntegerBigDecimal);
    	BigDecimal newIntegerBigDecimal = new BigDecimal(integerValue);
    	newValue = newValue.add(newIntegerBigDecimal);
    	value = newValue;
    }

    /**
     * @see Currency#getFractionalValue()
     */
    public int getFractionalValue() {
    	BigDecimal currentIntegerBigDecimal = getIntegerBigDecimalValue();
    	BigDecimal smallValue = value.subtract(currentIntegerBigDecimal);
    	BigDecimal smallLimitedValue = smallValue.setScale(ORDER);
    	BigDecimal newValue = smallLimitedValue.movePointRight(ORDER);
    	int result = newValue.intValue();
        return result;
    }

    /**
     * @see Currency#setFractionalValue(int)
     */
    public void setFractionalValue(int fractionalValue) {
    	BigDecimal currentIntegerBigDecimal = getIntegerBigDecimalValue();
    	BigDecimal newFractionalBigDecimal = new BigDecimal(fractionalValue);
    	BigDecimal newSmallBigDecimal = newFractionalBigDecimal.movePointLeft(ORDER);
    	value = currentIntegerBigDecimal.add(newSmallBigDecimal);
    }

    /**
     * @see Currency#add(Currency)
     */
    public Currency add(Currency currency) {
    	BigDecimal secondValue = currency.getCurrency();
    	BigDecimal resultValue = value.add(secondValue);
    	Currency result = new DefaultCurrency(resultValue);
        return result;
    }

    /**
     * @see Currency#substruct(Currency)
     */
    public Currency substract(Currency currency) {
    	BigDecimal secondValue = currency.getCurrency();
    	BigDecimal resultValue = value.subtract(secondValue);
    	Currency result = new DefaultCurrency(resultValue);
        return result;
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        BigDecimal scaledValue = value.setScale(ORDER, BigDecimal.ROUND_HALF_UP);
        String result = scaledValue.toString();
        return result;
    }
    
    /**
     * @see Object#equals(Object)
     */
    public boolean equals(Object arg0) {
    	if (arg0 == null) {
            return false;
    	}
    	if (arg0 == this) {
            return true;
    	}
    	if (!(arg0 instanceof Currency)) {
            return false;
    	}
        return value.equals(((Currency) arg0).getCurrency());
    }
}
