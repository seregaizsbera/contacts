package su.sergey.contacts.pageiterator;

import java.util.List;

import su.sergey.contacts.util.dao.DAOException;

/**
 * Базовый класс для всех PageIterator'ов.
 * 
 * @author Сергей Богданов
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
     * Возращает общее число элементов.
     * 
     * @return общее число элементов.
     */
    protected abstract int evaluateTotal() throws DAOException;

    /**
     * На основе значений свойств currentPosition и pageSize пытается
     * вернуть список с соотвествующими элементами.
     * 
     * @return список елементов на основе значений свойств currentPosition и pageSize.
     */
    protected abstract List evaluatePage() throws DAOException;

    /**
     * Инициализирует переменные члены. Этот метод должен вызываться и метода ejbCreate
     * наследников.
     * 
     * @param pageSize число элементов на странице.
     * @throws DAOException если таковое пробрасываетс при выполнения метода evaluateTotal().
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
