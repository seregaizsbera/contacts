package su.sergey.contacts.valueobjects.impl;

import java.math.BigDecimal;

import su.sergey.contacts.valueobjects.Currency;

public class DefaultCurrency implements Currency {
	private static final int ORDER = 8;
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
	 * @see Object#toString()
	 */
	public String toString() {
		String result = value.toString();
		return result;
	}
	
	/**
	 * @see Currency#bigDecimalValue()
	 */
	public BigDecimal bigDecimalValue() {
		return value;
	}
}
