package su.sergey.contacts.util.commands.common;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.SessionConstants;
import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.directory.wrappers.DirectoryHttpServletRequest;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

public abstract class AbstractCommand implements Command {
    protected DAOBusinessDelegate getDAOBusinessDelegate(HttpServletRequest request) {
        return (DAOBusinessDelegate) request.getSession().getAttribute(SessionConstants.DAO_BUSINESS_DELEGATE);
    }
}
