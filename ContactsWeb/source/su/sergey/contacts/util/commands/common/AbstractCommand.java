package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.SessionConstants;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;

public abstract class AbstractCommand implements Command {
    protected DAOBusinessDelegate getDAOBusinessDelegate(HttpServletRequest request) {
        return (DAOBusinessDelegate) request.getSession().getAttribute(SessionConstants.DAO_BUSINESS_DELEGATE);
    }
}
