package su.sergey.contacts.util.pageiteration;

import su.sergey.contacts.util.PageParameters;

/**
 * Содержит информацию необходимую для итерации страниц.
 */
public class PageIterationInfo {
    /** Число страниц */
    private int numberOfPages = 0;
    
    /** Номер отображаемой страницы */
    private int currentPage = 0;
    
    /** размер страницы */
    private int pageSize = PageParameters.DEFAULT_PAGE_SIZE;

    /**
     * Создаёт новый объект
     */
    public PageIterationInfo(int numberOfPages, int currentPage, int pageSize) {
        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * Возвращает общее число страниц
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Возвращает номер текущей страницы
     */
    public int getCurrentPage() {
        return currentPage;
    }

	/**
	 * Gets the pageSize
	 * @return Returns a int
	 */
	public int getPageSize() {
		return pageSize;
	}	
}
