package su.sergey.contacts.call.searchparameters;

import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.valueobjects.Currency;

public class ExpenseSearchParameters extends CallExpenseData {
	
	public ExpenseSearchParameters() {
	}
	
	public ExpenseSearchParameters(Integer id, Integer report, Integer kind, Integer expense, Currency price) {
		setId(id);
		setReport(report);
		setKind(kind);
		setExpense(expense);
		setPrice(price);
	}
}
