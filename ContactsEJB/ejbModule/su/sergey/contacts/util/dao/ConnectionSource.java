package su.sergey.contacts.util.dao;

import java.sql.Connection;

/**
 * �������� ���������������� ������ {@link AbstractDAO} �� �����������
 * ������� ��������� ���������� � ����� ������.
 * 
 * @author ������ ��������
 */
public interface ConnectionSource {
    /**
     * ���������� ���������� � ����� ������. ������ ���������� �����
     * ������� ���������� � ������������ ������� ������ ���� ������������
     * ������� ������ {@link #close(Connection)}.
     * 
     * @return ���������� � ����� ������
     */
    Connection getConnection();
    
    /**
     * ���������� ���������� � ����� ������. ������ ���������� �����
     * ������� ���������� � ������������ ������� ������ ���� ������������
     * ������� ������ {@link #close(Connection)}.
     * 
     * @param userName ��� ������������
     * @param password ������
     * @return ���������� � ����� ������
     */
    Connection getConnection(String userName, String password);
    
    /**
     * ��������� ���������� � ����� ������.
     * @param connection ���������� � ����� ������, ���������� � �������
     *        ������ �� ������� getConnection().
     */
    void close(Connection connection);
}
