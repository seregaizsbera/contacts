package su.sergey.contacts.valueobjects.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import su.sergey.contacts.valueobjects.Currency;

/**
 * ���������� ������������� ���������� <code>Currency</code>.
 * @author 
 * @version 1.0
 */
public class DefaultCurrency implements Currency {

    /**
     * ������� ������� �����.
     */
    private int ORDER = 100;
    
    /**
     * ������� ������� ����� (���������� ���������� ����)
     */
    private int ORDER_DEGREE = 2;
    
    /**
     * ����� �����.
     */
    private BigInteger integerValue;
    /**
     * ������� �����.
     */
    private int fractionalValue;

    /**
     * �������� ��������.
     */
    private BigDecimal value;

    /**
     * ������� ������.
     */
    public DefaultCurrency() {
        this(new BigDecimal(0));
    }

    /**
     * ������� ������.
     */
    public DefaultCurrency(BigInteger integerValue, int fractionalValue) {
        this.integerValue = integerValue;
        this.fractionalValue = fractionalValue;
    }

    /**
     * ������� ������.
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
     * ������������� �������� ��������.
     */
	public void setCurrency(BigDecimal value) {
        this.value = value;
	}

    /**
     * ��������� �������� ��������.
     */
    public BigDecimal getCurrency() {
        return value;
    }

    /**
     * ���������� ����� �����.
     */
    public BigInteger getIntegerValue() {
        return integerValue;
    }

    /**
     * ������������� ����� �����.
     */
    public void setIntegerValue(BigInteger integerValue) {
        this.integerValue = integerValue;
    }

    /**
     * ���������� ������� �����.
     */
    public int getFractionalValue() {
        return fractionalValue;
    }

    /**
     * ������������� ������� �����.
     */
    public void setFractionalValue(int fractionalValue) {
        this.fractionalValue = fractionalValue;
    }
    /**
     * ���������� ������, ��� ��������� ����� �������� ���� �������� �������.
     * ��� �������� � ���������� ���������� ������ �������� �� ����������.
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
     * ���������� ������, ��� ��������� ����� ��������� ���� �������� �������.
     * ��� �������� � ���������� ���������� ������ �������� �� ����������.
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
