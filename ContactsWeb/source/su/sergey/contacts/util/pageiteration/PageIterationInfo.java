package su.sergey.contacts.util.pageiteration;

/**
 * Содержит информацию необходимую для итерации страниц.
 *
 * Author: 
 * Date: 12.08.2002
 * */
public class PageIterationInfo {
    /*Число страниц*/
    int numberOfPages = 0;
    /*Номер отображаемой страницы*/
    int currentPage = 0;

    /**
     * Создаёт новый объект
     * */
    public PageIterationInfo(int numberOfPages, int currentPage) {

        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
    }

    /**
     * Создаёт новый объект. Текущая страница нулевая.
     * */
    public PageIterationInfo(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    /**
     * Возвращает общее число страниц
     * */
    public int getNumberOfPages() {

        return numberOfPages;
    }

    /**
     * Устанавливает общее число страниц
     * */
    public void setNumberOfPages(int numberOfPages) {

        this.numberOfPages = numberOfPages;
    }

    /**
     * Возвращает номер текущей страницы
     * */
    public int getCurrentPage() {

        return currentPage;
    }

    /**
     * Устанавливает номер текущей страницы
     * */
    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;
    }
}
