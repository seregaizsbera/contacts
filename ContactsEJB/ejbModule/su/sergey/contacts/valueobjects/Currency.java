package su.sergey.contacts.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * �������� ������, �������������� �������� ��������.
 * @author: 
 * @version: 1.0
 */
public interface Currency extends Serializable {

    /**
     * ������������� �������� ��������.
     */
	void setCurrency(BigDecimal value);

    /**
     * ��������� �������� ��������.
     */
    BigDecimal getCurrency();

    /**
     * ���������� ����� �����.
     */
    BigInteger getIntegerValue();

    /**
     * ������������� ����� �����.
     */
    void setIntegerValue(BigInteger integerValue);

    /**
     * ���������� ������� �����.
     */
    int getFractionalValue();

    /**
     * ������������� ������� �����.
     */
    void setFractionalValue(int fractionalValue);

    /**
     * ���������� ������, ��� ��������� ����� �������� ���� �������� �������.
     * ��� �������� � ���������� ���������� ������ �������� �� ����������.
     */
    Currency add(Currency currency);

    /**
     * ���������� ������, ��� ��������� ����� ��������� ���� �������� �������.
     * ��� �������� � ���������� ���������� ������ �������� �� ����������.
     */
    Currency substruct(Currency currency);
}
