package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public final class CallData implements Serializable, CallCreateInfo, CallUpdateInfo {
    private Integer id;
    private Date moment;
    private Integer direction;
    private String phone;
    private String place;
    private Integer type;
    private Date quantity;
    private Currency price;
    private String note;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void  setMoment(Date moment) {
        this.moment = moment;
    }

    public Integer getDirection() {
        return direction;
    }

    public void  setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void  setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace() {
        return place;
    }

    public void  setPlace(String place) {
        this.place = place;
    }

    public Integer getType() {
        return type;
    }

    public void  setType(Integer type) {
        this.type = type;
    }

    public Date getQuantity() {
        return quantity;
    }

    public void  setQuantity(Date quantity) {
        this.quantity = quantity;
    }

    public Currency getPrice() {
        return price;
    }

    public void  setPrice(Currency price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void  setNote(String note) {
        this.note = note;
    }
}
