package su.sergey.contacts.directory;

import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectorySearchParameters;
import su.sergey.contacts.pageiterator.AbstractPageIterator;

/**
 * DirectoryRecordsPageIteratorBean
 * 
 * @author Сергей Богданов
 */
public class DirectoriesPageIteratorBean extends AbstractPageIterator implements SessionBean {
    private SessionContext ctx = null;
    private DirectorySearchParameters searchParameters;
    private FindDirectoryDAO searchDao;
    
    protected int evaluateTotal() {
        return searchDao.countDirectoryMetadata(searchParameters);
    }

    protected List evaluatePage() {
    	int pageSize = getPageSize();
    	int position = getCurrentPage() * pageSize + 1;
        return searchDao.findDirectoryMetadata(searchParameters, position, pageSize);
    }

    public DirectoryMetadata[] next() {
        List res = nextPage();
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public DirectoryMetadata[] current() {
        List res = currentPage();
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public DirectoryMetadata[] prev() {
        List res = prevPage();
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public DirectoryMetadata[] goTo(int page) {
        List res = goToPage(page);
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public void ejbCreate(DirectorySearchParameters searchParameters, int pageSize) throws CreateException {
        this.searchDao = FindDirectoryDAO.getInstance();
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
