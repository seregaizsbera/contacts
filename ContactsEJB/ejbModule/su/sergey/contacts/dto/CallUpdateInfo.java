package su.sergey.contacts.dto;

import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public interface CallUpdateInfo {
    Date getMoment();
    Integer getDirection();
    String getPhone();
    String getPlace();
    Integer getType();
    Date getQuantity();
    Currency getPrice();
    String getNote();
}
