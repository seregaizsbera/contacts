package su.sergey.contacts.pageiterator;

import java.util.List;

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
    protected abstract int evaluateTotal();

    /**
     * На основе значений свойств currentPosition и pageSize пытается
     * вернуть список с соотвествующими элементами.
     * 
     * @return список елементов на основе значений свойств currentPosition и pageSize.
     */
    protected abstract List evaluatePage();

    /**
     * Инициализирует переменные члены. Этот метод должен вызываться и метода ejbCreate
     * наследников.
     * 
     * @param pageSize число элементов на странице.
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
