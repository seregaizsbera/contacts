package su.sergey.contacts.pageiterator;

import java.util.List;

import su.sergey.contacts.util.dao.DAOException;

/**
 * ������� ����� ��� ���� PageIterator'��.
 * 
 * @author ������ ��������
 */
public abstract class AbstractPageIterator {
    private int pageSize;
    private int currentPage;
    private int totalCount;
    private int totalPageCount;
    
    //-----------------------------------------------------------------------------
    public int getTotalPageCount() {
    	return totalPageCount;
    }    

    public boolean hasNext() {
        return currentPage + 1 < totalPageCount;
    }

    public boolean hasPrev() {
        return currentPage > 0;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    //-----------------------------------------------------------------------------
    /**
     * ��������� ����� ����� ���������.
     * 
     * @return ����� ����� ���������.
     */
    protected abstract int evaluateTotal() throws DAOException;

    /**
     * �� ������ �������� ������� currentPosition � pageSize ��������
     * ������� ������ � ��������������� ����������.
     * 
     * @return ������ ��������� �� ������ �������� ������� currentPosition � pageSize.
     */
    protected abstract List evaluatePage() throws DAOException;

    /**
     * �������������� ���������� �����. ���� ����� ������ ���������� � ������ ejbCreate
     * �����������.
     * 
     * @param pageSize ����� ��������� �� ��������.
     * @throws DAOException ���� ������� ������������� ��� ���������� ������ evaluateTotal().
     */
    protected void create(int pageSize) throws DAOException {
        this.pageSize = pageSize;
        this.currentPage = -1;
        this.totalCount = evaluateTotal();
        this.totalPageCount = this.totalCount / this.pageSize;
        if (this.totalCount % this.pageSize != 0) {
        	this.totalPageCount++;
        }
    }

    protected List currentPage() throws DAOException {
    	setCurrentPage(currentPage);
        List res = evaluatePage();
        return res;
    }

    protected List nextPage() throws DAOException {
        setCurrentPage(currentPage + 1);
        List res = evaluatePage();
        return res;
    }

    protected List prevPage() throws DAOException {
        setCurrentPage(currentPage - 1);
        List res = evaluatePage();
        return res;
    }

    protected List goToPage(int page) throws DAOException {
        setCurrentPage(page);
        List res = evaluatePage();
        return res;
    }
    
    //---------------------------------------------------------------------------
    private void setCurrentPage(int page) {
    	int newPage = page;
        int pageCount = getTotalPageCount();
        if (newPage >= pageCount) {
        	newPage = pageCount - 1;
        }
        if (newPage < 0) {
        	newPage = 0;
        }
    	this.currentPage = newPage;
    }
}
