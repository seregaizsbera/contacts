package su.sergey.contacts.dto;

import su.sergey.contacts.valueobjects.Currency;

public interface CallExpenseCreateInfo {
    Integer getReport();
    Integer getKind();
    Integer getExpense();
    Currency getPrice();
}
