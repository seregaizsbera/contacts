package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public final class CallPayData implements Serializable, CallPayCreateInfo, CallPayUpdateInfo {
    private Integer id;
    private Date payDay;
    private Currency amount;
    private Currency course;
    private Currency amountInAbstractUnits;
    private String note;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public Date getPayDay() {
        return payDay;
    }

    public void  setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    public Currency getAmount() {
        return amount;
    }

    public void  setAmount(Currency amount) {
        this.amount = amount;
    }

    public Currency getCourse() {
        return course;
    }

    public void  setCourse(Currency course) {
        this.course = course;
    }

    public Currency getAmountInAbstractUnits() {
        return amountInAbstractUnits;
    }

    public void  setAmountInAbstractUnits(Currency amountInAbstractUnits) {
        this.amountInAbstractUnits = amountInAbstractUnits;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
