package su.sergey.contacts.dto;

import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public interface CallReportCreateInfo {
    Date getFirstDay();
    Date getLastDay();
    Date getArrivalDay();
    Date getProcessDay();
    Currency getPurePeriodPrice();
    String getNote();
}
