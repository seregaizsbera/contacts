package su.sergey.contacts.directory;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.pageiterator.AbstractPageIterator;
import su.sergey.contacts.util.dao.DAOException;

/**
 * DirectoryRecordsPageIteratorBean
 *
 * @author Сергей Богданов
 */
public class DirectoryRecordsPageIteratorBean extends AbstractPageIterator implements SessionBean {
    private SessionContext ctx = null;
    private DirectoryRecordSearchParameters searchParameters;

    public void ejbCreate(DirectoryRecordSearchParameters searchParameters, int pageSize) throws EJBException {
        this.searchParameters = searchParameters;
        try {
            create(pageSize);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    protected int evaluateTotal() throws DAOException {
        if (searchParameters != null) {
            return FindDirectoryDAO.getInstance().countDirectoryRecords(searchParameters);
        } else {
            return 0;
        }
    }

    protected List evaluatePage() throws DAOException {
        if (searchParameters != null) {
        	int pageSize = getPageSize();
        	int position = getCurrentPage() * pageSize + 1;
            return FindDirectoryDAO.getInstance().findDirectoryRecords(searchParameters, position, pageSize);
        } else {
            return null;
        }
    }

    public DirectoryRecord[] next() throws DAOException {
        List res = nextPage();
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public DirectoryRecord[] current() throws DAOException {
        List res = currentPage();
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public DirectoryRecord[] prev() throws DAOException {
        List res = prevPage();
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public DirectoryRecord[] goTo(int page) throws DAOException {
        List res = goToPage(page);
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public void ejbRemove() throws EJBException {}

    public void ejbActivate() throws EJBException {}

    public void ejbPassivate() throws EJBException {}

    public void setSessionContext(SessionContext context) throws EJBException {
        this.ctx = context;
    }
}
