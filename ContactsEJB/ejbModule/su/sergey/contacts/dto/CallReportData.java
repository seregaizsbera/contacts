package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public final class CallReportData implements Serializable, CallReportCreateInfo, CallReportUpdateInfo {
    private Integer id;
    private Date firstDay;
    private Date lastDay;
    private Date arrivalDay;
    private Date processDay;
    private Currency purePeriodPrice;
    private String note;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void  setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void  setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public Date getArrivalDay() {
        return arrivalDay;
    }

    public void  setArrivalDay(Date arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public Date getProcessDay() {
        return processDay;
    }

    public void  setProcessDay(Date processDay) {
        this.processDay = processDay;
    }

    public Currency getPurePeriodPrice() {
        return purePeriodPrice;
    }

    public void  setPurePeriodPrice(Currency purePeriodPrice) {
        this.purePeriodPrice = purePeriodPrice;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
