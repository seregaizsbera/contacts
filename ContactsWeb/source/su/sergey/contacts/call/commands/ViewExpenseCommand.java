package su.sergey.contacts.call.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.call.valueobjects.CallExpenseAttributes;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.util.InquiryAliases;
import su.sergey.contacts.inquiry.valueobjects.InquiryObjects;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class ViewExpenseCommand extends AbstractCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		saveInquiryData(request, InquiryAliases.CALL_EXPENSES_KINDS);
		Integer id = ParameterUtil.getInteger(request, "id");
		if (id != null) {
			CallExpenseHandle handle = new CallExpenseHandle(id);
			CallExpenseAttributes data = delegate.findCallExpense(handle);
			if (data != null) {
				request.setAttribute("expense", data);
			}
		}
		return PageNames.CALL_EXPENSE;
	}
}
