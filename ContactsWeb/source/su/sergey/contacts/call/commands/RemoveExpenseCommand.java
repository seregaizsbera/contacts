package su.sergey.contacts.call.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.CallExpenseHandle;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.ParameterUtil;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class RemoveExpenseCommand extends AbstractCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		Integer id = ParameterUtil.getInteger(request, "id");
        CallExpenseHandle handle = new CallExpenseHandle(id);
		try {
    		delegate.removeCallExpense(handle);
    		request.setAttribute("message", "Информация удалена.");
		} catch (RuntimeDelegateException e) {
			request.setAttribute("message", e.getMessage());
		}
		return new ViewExpenseCommand().execute(request);
	}
}
