package su.sergey.contacts.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * �������� ������, �������������� �������� ��������.
 *
 * @author: ������ ��������
 */
public interface Currency extends Serializable {
	BigDecimal bigDecimalValue();
}
