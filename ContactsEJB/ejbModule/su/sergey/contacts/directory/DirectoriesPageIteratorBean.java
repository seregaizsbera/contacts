package su.sergey.contacts.directory;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectorySearchParameters;
import su.sergey.contacts.pageiterator.AbstractPageIterator;
import su.sergey.contacts.util.dao.DAOException;

/**
 * DirectoryRecordsPageIteratorBean
 * 
 * @author Сергей Богданов
 */
public class DirectoriesPageIteratorBean extends AbstractPageIterator implements SessionBean {
    private SessionContext ctx = null;
    private DirectorySearchParameters searchParameters;
    
    public void ejbCreate(DirectorySearchParameters searchParameters, int pageSize) throws EJBException {
        try {
        	this.searchParameters = searchParameters;
            create(pageSize);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    protected int evaluateTotal() throws DAOException {
        return FindDirectoryDAO.getInstance().countDirectoryMetadata(searchParameters);
    }

    protected List evaluatePage() throws DAOException {
    	int pageSize = getPageSize();
    	int position = getCurrentPage() * pageSize + 1;
        return FindDirectoryDAO.getInstance().findDirectoryMetadata(searchParameters, position, pageSize);
    }

    public DirectoryMetadata[] next() throws DAOException {
        List res = nextPage();
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public DirectoryMetadata[] current() throws DAOException {
        List res = currentPage();
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public DirectoryMetadata[] prev() throws DAOException {
        List res = prevPage();
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public DirectoryMetadata[] goTo(int page) throws DAOException {
        List res = goToPage(page);
        return (DirectoryMetadata[])res.toArray(new DirectoryMetadata[res.size()]);
    }

    public void ejbRemove() throws EJBException {}

    public void ejbActivate() throws EJBException {}

    public void ejbPassivate() throws EJBException {}

    public void setSessionContext(SessionContext context) throws EJBException {
        this.ctx = context;
    }
}
