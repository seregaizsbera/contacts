package su.sergey.contacts.call.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.call.valueobjects.CallExpenseAttributes;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;
import su.sergey.contacts.valueobjects.impl.DefaultCurrency;

public class UpdateExpenseCommand extends AbstractCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		Integer id = ParameterUtil.getInteger(request, "id");
        CallExpenseHandle handle = new CallExpenseHandle(id);
		CallExpenseAttributes data = new CallExpenseAttributes();
		data.setExpense(ParameterUtil.getInteger(request, "expense"));
		data.setKind(ParameterUtil.getInteger(request, "kind"));
		data.setPrice(new DefaultCurrency(ParameterUtil.getBigDecimal(request, "price")));
		data.setReport(ParameterUtil.getInteger(request, "report"));
		try {
    		delegate.updateCallExpense(handle, data);
    		request.setAttribute("message", "Информация обновлена. Id=" + handle.getId());
		} catch (ContactsException e) {
			request.setAttribute("message", e.getMessage());
		} catch (RuntimeDelegateException e) {
			request.setAttribute("message", e.getMessage());
		}
		return new ViewExpenseCommand().execute(request);
	}
}
