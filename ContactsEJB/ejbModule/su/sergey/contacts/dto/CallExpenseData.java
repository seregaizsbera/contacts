package su.sergey.contacts.dto;

import java.io.Serializable;

import su.sergey.contacts.valueobjects.Currency;

public final class CallExpenseData implements Serializable, CallExpenseCreateInfo, CallExpenseUpdateInfo {
    private Integer id;
    private Integer report;
    private Integer kind;
    private Integer expense;
    private Currency price;

    public Integer getId() {
        return id;
    }

    public void  setId(Integer id) {
        this.id = id;
    }

    public Integer getReport() {
        return report;
    }

    public void  setReport(Integer report) {
        this.report = report;
    }

    public Integer getKind() {
        return kind;
    }

    public void  setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getExpense() {
        return expense;
    }

    public void  setExpense(Integer expense) {
        this.expense = expense;
    }

    public Currency getPrice() {
        return price;
    }

    public void  setPrice(Currency price) {
        this.price = price;
    }
}
