package su.sergey.contacts.pageiterator.businessdelegate;

/**
 * Общий интерфейс для всех page iterators,
 * позволяет взять у любого итератора общие параметры
 */
public interface PageIteratorBusinessDelegate {
    boolean hasNext();

    boolean hasPrev();

    int getCurrentPage();

    int getPageSize();

    int getNumberOfPages();
    
    void freeResources();
}
