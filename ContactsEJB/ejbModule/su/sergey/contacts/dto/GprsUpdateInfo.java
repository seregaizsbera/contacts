package su.sergey.contacts.dto;

import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public interface GprsUpdateInfo {
    Date getMoment();
    Integer getDirection();
    Integer getUrl();
    Integer getTraffic();
    Currency getPrice();
    String getNote();
}
