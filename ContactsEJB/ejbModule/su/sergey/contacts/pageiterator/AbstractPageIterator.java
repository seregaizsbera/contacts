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
    private int currentPozition;
    private long totalCount;


    /**
     * ��������� ����� ����� ���������.
     * 
     * @return ����� ����� ���������.
     */
    protected abstract long evaluateTotal() throws DAOException;

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
        setPageSize(pageSize);
        this.currentPozition = 0;
        //this.totalCount = evaluateTotal();
        this.totalCount = -1;
    }

    protected List currentPage() throws DAOException {
        checkCurrentPosition();
        List res = evaluatePage();
        return res;
    }

    protected List nextPage() throws DAOException {
        setCurrentPozition(getCurrentPozition() + getPageSize());
        checkCurrentPosition();
        List res = evaluatePage();
        return res;
    }

    private void checkCurrentPosition() throws DAOException {
        setTotalCount(evaluateTotal());
        if (getCurrentPozition() >= getTotalCount()) {
            if (getTotalCount() == 0) {
                setCurrentPozition(0);
            } else if (getTotalCount() % getPageSize() == 0) {
                setCurrentPozition(getTotalCount() - getPageSize());
            } else {
                setCurrentPozition(getTotalCount() - (getTotalCount() % getPageSize()));
            }
        } else if (getCurrentPozition() < 0) {
            setCurrentPozition(0);
        }
    }

    protected List prevPage() throws DAOException {
        setCurrentPozition(getCurrentPozition() - getPageSize());
        checkCurrentPosition();
        List res = evaluatePage();
        return res;
    }

    protected List goToPage(int page) throws DAOException {
        setCurrentPozition(page * getPageSize());
        checkCurrentPosition();
        List res = evaluatePage();
        return res;
    }

    public boolean hasNext() {
        return (getTotalCount() - getCurrentPozition()) > 0;
    }

    public boolean hasPrev() {
        return getCurrentPozition() > 0;
    }

    public int getCurrentPozition() {
        return currentPozition;
    }

    public int getCurrentPage() {
        return getCurrentPozition() / getPageSize();
    }

    public int getPageSize() {
        return pageSize;
    }

    private void setCurrentPozition(int currentPozition) {
        this.currentPozition = currentPozition;
    }

    private void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private int getTotalCount() {
        if (totalCount == -1) {
            totalCount = evaluateTotal();
        }

        return totalCount > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)totalCount;
    }

    public int getTotalPageCount() {
        if (getTotalCount() == 0) {
            return 0;
        } else if ((getTotalCount() % getPageSize()) == 0) {
            return getTotalCount() / getPageSize();
        } else {
            return (getTotalCount() / getPageSize()) + 1;
        }
    }

    private void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
