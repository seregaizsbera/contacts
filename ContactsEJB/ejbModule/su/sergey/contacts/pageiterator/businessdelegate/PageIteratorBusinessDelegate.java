package su.sergey.contacts.pageiterator.businessdelegate;

/**
 * ����� ��������� ��� ���� page iterators,
 * ��������� ����� � ������ ��������� ����� ���������
 */
public interface PageIteratorBusinessDelegate {
    boolean hasNext();

    boolean hasPrev();

    int getCurrentPage();

    int getPageSize();

    int getNumberOfPages();
    
    void freeResources();
}
