package su.sergey.contacts.util.pageiteration;

import su.sergey.contacts.util.PageParameters;

/**
 * �������� ���������� ����������� ��� �������� �������.
 */
public class PageIterationInfo {
    /** ����� ������� */
    private int numberOfPages = 0;
    
    /** ����� ������������ �������� */
    private int currentPage = 0;
    
    /** ������ �������� */
    private int pageSize = PageParameters.DEFAULT_PAGE_SIZE;

    /**
     * ������� ����� ������
     */
    public PageIterationInfo(int numberOfPages, int currentPage, int pageSize) {
        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * ���������� ����� ����� �������
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * ���������� ����� ������� ��������
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
