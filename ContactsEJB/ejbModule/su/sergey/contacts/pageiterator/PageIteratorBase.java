package su.sergey.contacts.pageiterator;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * ������� ��������� ��� ���� PageIterator��.
 * @author 
 * @date 02.08.2002
 * @time 10:55:22
 */
public interface PageIteratorBase extends EJBObject {
    /**
     * ���������� true ���� ����� ������� �������� ���� ���������.
     * @return true ���� ����� ������� �������� ���� ���������.
     */
    boolean hasNext() throws RemoteException;
    /**
     * ���������� true ���� ����� ������� ��������� ���� ��� ����.
     * @return true ���� ����� ������� ��������� ���� ��� ����.
     */
    boolean hasPrev() throws RemoteException;
    /**
     * ���������� ����� ������� ������������� �� ������� ��������
     * ��������� (��������� ���������� 0).
     * @return ����� ������� ������������� �� ������� ��������
     * ��������� (��������� ���������� 0).
     */
    int getCurrentPozition() throws RemoteException;
    /**
     * ���������� ����� ������� �������� (��������� ���������� � 0).
     * @return ����� ������� �������� (��������� ���������� � 0).
     */
    int getCurrentPage() throws RemoteException;
    /**
     * ���������� ������ ��������.
     * @return ������ ��������.
     */
    int getPageSize() throws RemoteException;
    /**
     * ���������� ����� ����� �������.
     * @return ����� ����� �������.
     */
    int getTotalPageCount() throws RemoteException;
}
