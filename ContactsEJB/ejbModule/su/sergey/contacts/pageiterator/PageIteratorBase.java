package su.sergey.contacts.pageiterator;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Базовый интерфейс для всех PageIteratorов.
 * @author 
 * @date 02.08.2002
 * @time 10:55:22
 */
public interface PageIteratorBase extends EJBObject {
    /**
     * Возвращает true если после текущей страницы есть следующая.
     * @return true если после текущей страницы есть следующая.
     */
    boolean hasNext() throws RemoteException;
    /**
     * Возвращает true если перед текущей страницей есть еще одна.
     * @return true если перед текущей страницей есть еще одна.
     */
    boolean hasPrev() throws RemoteException;
    /**
     * Возвращает номер первого показываемого на текущей странице
     * эелемента (нумерация начинается 0).
     * @return номер первого показываемого на текущей странице
     * эелемента (нумерация начинается 0).
     */
    int getCurrentPozition() throws RemoteException;
    /**
     * Возвращает номер текущей страницы (нумерация начинается с 0).
     * @return номер текущей страницы (нумерация начинается с 0).
     */
    int getCurrentPage() throws RemoteException;
    /**
     * Возвращает размер страницы.
     * @return размер страницы.
     */
    int getPageSize() throws RemoteException;
    /**
     * Возвращает общее число страниц.
     * @return общее число страниц.
     */
    int getTotalPageCount() throws RemoteException;
}
