package su.sergey.contacts.call.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.CallExpenseData;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.util.pagemessage.PageMessage;
import su.sergey.contacts.valueobjects.impl.DefaultCurrency;

public class CreateExpenseCommand extends AbstractCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		CallExpenseData data = new CallExpenseData();
		data.setExpense(ParameterUtil.getInteger(request, "expense"));
		data.setKind(ParameterUtil.getInteger(request, "kind"));
		data.setPrice(new DefaultCurrency(ParameterUtil.getBigDecimal(request, "price")));
		data.setReport(ParameterUtil.getInteger(request, "report"));
		try {
    		CallExpenseHandle handle = delegate.createCallExpense(data);
    		request.setAttribute("message", new PageMessage("Информация добавлена. Id=" + handle.getId()));
		} catch (ContactsException e) {
			request.setAttribute("message", new PageMessage(e.getMessage()));
		} catch (RuntimeDelegateException e) {
			request.setAttribute("message", new PageMessage(e.getMessage()));
		}
		return new ViewExpenseCommand().execute(request);
	}
}
