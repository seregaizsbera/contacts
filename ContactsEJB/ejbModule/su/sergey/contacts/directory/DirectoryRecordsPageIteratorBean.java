package su.sergey.contacts.directory;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.pageiterator.AbstractPageIterator;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

/**
 * DirectoryRecordsPageIteratorBean
 *
 * @author Сергей Богданов
 */
public class DirectoryRecordsPageIteratorBean extends AbstractPageIterator implements SessionBean {
    private SessionContext ctx = null;
    private DirectoryRecordSearchParameters searchParameters;
    private FindDirectoryDAO searchDao;

    protected int evaluateTotal() {
        if (searchParameters != null) {
            return searchDao.countDirectoryRecords(searchParameters);
        } else {
            return 0;
        }
    }

    protected List evaluatePage() {
        if (searchParameters != null) {
        	int pageSize = getPageSize();
        	int position = getCurrentPage() * pageSize + 1;
            return searchDao.findDirectoryRecords(searchParameters, position, pageSize);
        } else {
            return null;
        }
    }

    public DirectoryRecord[] next() {
        List res = nextPage();
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public DirectoryRecord[] current() {
        List res = currentPage();
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public DirectoryRecord[] prev() {
        List res = prevPage();
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public DirectoryRecord[] goTo(int page) {
        List res = goToPage(page);
        return (DirectoryRecord[])res.toArray(new DirectoryRecord[res.size()]);
    }

    public void ejbCreate(DirectoryRecordSearchParameters searchParameters, int pageSize) throws CreateException {
        ConnectionSource connectionSource = new ContainerConnectionSource();
        this.searchDao = new FindDirectoryDAO(connectionSource);
        this.searchParameters = searchParameters;
        create(pageSize);
    }

    public void setSessionContext(SessionContext context) {
        this.ctx = context;
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }
}
