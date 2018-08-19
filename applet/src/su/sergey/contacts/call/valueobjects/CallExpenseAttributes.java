package su.sergey.contacts.call.valueobjects;

import su.sergey.contacts.dto.CallExpenseData;

public class CallExpenseAttributes extends CallExpenseData {

    public CallExpenseAttributes() {
    }

    public CallExpenseAttributes(CallExpenseData data) {
        setId(data.getId());
	setReport(data.getReport());
	setKind(data.getKind());
	setExpense(data.getExpense());
	setPrice(data.getPrice());
    }
}
