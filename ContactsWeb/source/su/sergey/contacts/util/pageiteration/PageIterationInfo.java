package su.sergey.contacts.util.pageiteration;

/**
 * �������� ���������� ����������� ��� �������� �������.
 *
 * Author: 
 * Date: 12.08.2002
 * */
public class PageIterationInfo {
    /*����� �������*/
    int numberOfPages = 0;
    /*����� ������������ ��������*/
    int currentPage = 0;

    /**
     * ������� ����� ������
     * */
    public PageIterationInfo(int numberOfPages, int currentPage) {

        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
    }

    /**
     * ������� ����� ������. ������� �������� �������.
     * */
    public PageIterationInfo(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    /**
     * ���������� ����� ����� �������
     * */
    public int getNumberOfPages() {

        return numberOfPages;
    }

    /**
     * ������������� ����� ����� �������
     * */
    public void setNumberOfPages(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    /**
     * ���������� ����� ������� ��������
     * */
    public int getCurrentPage() {

        return currentPage;
    }

    /**
     * ������������� ����� ������� ��������
     * */
    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;
    }
}
