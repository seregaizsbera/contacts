package su.sergey.contacts.dto;

import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public interface CallPayCreateInfo {
    Date getPayDay();
    Currency getAmount();
    Currency getCourse();
    Currency getAmountInAbstractUnits();
    String getNote();
}
