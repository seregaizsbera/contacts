package su.sergey.contacts.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Содержит данные, представляющие денежную величину.
 *
 * @author: Сергей Богданов
 */
public interface Currency extends Serializable {
	BigDecimal bigDecimalValue();
}
