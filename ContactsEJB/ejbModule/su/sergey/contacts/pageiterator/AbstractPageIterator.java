package su.sergey.contacts.pageiterator;

import java.util.List;

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
    protected abstract int evaluateTotal();

    /**
     * �� ������ �������� ������� currentPosition � pageSize ��������
     * ������� ������ � ��������������� ����������.
     * 
     * @return ������ ��������� �� ������ �������� ������� currentPosition � pageSize.
     */
    protected abstract List evaluatePage();

    /**
     * �������������� ���������� �����. ���� ����� ������ ���������� � ������ ejbCreate
     * �����������.
     * 
     * @param pageSize ����� ��������� �� ��������.
     */
    protected void create(int pageSize) {
        this.pageSize = pageSize;
        this.currentPage = -1;
        this.totalCount = evaluateTotal();
        this.totalPageCount = this.totalCount / this.pageSize;
        if (this.totalCount % this.pageSize != 0) {
        	this.totalPageCount++;
        }
    }

    protected List currentPage() {
    	setCurrentPage(currentPage);
        List res = evaluatePage();
        return res;
    }

    protected List nextPage() {
        setCurrentPage(currentPage + 1);
        List res = evaluatePage();
        return res;
    }

    protected List prevPage() {
        setCurrentPage(currentPage - 1);
        List res = evaluatePage();
        return res;
    }

    protected List goToPage(int page) {
        setCurrentPage(page);
        List res = evaluatePage();
        return res;
    }
    
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
