package su.sergey.contacts.dto;

import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public interface GprsUpdateInfo {
    Date getMoment();
    Integer getDirection();
    Integer getUrl();
    Currency getTraffic();
    Currency getPrice();
    String getNote();
}
