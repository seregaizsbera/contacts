package su.sergey.contacts.dto;

import java.io.Serializable;
import java.util.Date;

import su.sergey.contacts.valueobjects.Currency;

public final class GprsData implements Serializable, GprsCreateInfo, GprsUpdateInfo {
    private Integer id;
    private Date moment;
    private Integer direction;
    private Integer url;
    private Integer traffic;
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

    public Integer getUrl() {
        return url;
    }

    public void  setUrl(Integer url) {
        this.url = url;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void  setTraffic(Integer traffic) {
        this.traffic = traffic;
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
