package su.sergey.contacts.call.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.JNDINamesForWeb;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.inquiry.TableNames;
import su.sergey.contacts.inquiry.businessdelegate.InquiryBusinessDelegate;
import su.sergey.contacts.inquiry.businessdelegate.impl.DefaultInquiryBusinessDelegate;
import su.sergey.contacts.inquiry.valueobjects.InquiryObject;
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
		InquiryBusinessDelegate inquiry = new DefaultInquiryBusinessDelegate(JNDINamesForWeb.INQUIRY_REFERENCE);
		InquiryObject[] expenseKinds = inquiry.inquireTableAsNames(TableNames.CALL_EXPENSES_KINDS);
		request.setAttribute("expense_kinds", expenseKinds);
		Integer id = ParameterUtil.getInteger(request, "id");
		if (id != null) {
			CallExpenseHandle handle = new CallExpenseHandle(id);
			CallExpenseData data = delegate.findCallExpense(handle);
			if (data != null) {
				request.setAttribute("expense", data);
			}
		}
		return PageNames.CALL_EXPENSE;
	}
}
