package su.sergey.contacts.util.dao;

import su.sergey.contacts.exceptions.RuntimeGenericException;


/**
 * �������������� � � ������ ������������� �������������� �������� ��� ������ ��
 * ������ ������� � ���� ������.
 * 
 * @author ������ ��������
 */
public class DAOException extends RuntimeGenericException {
    public DAOException(String message, Exception cause) {
        super(message, cause);
    }

    public DAOException(Exception cause) {
        super(cause);
    }
    
    public DAOException(String message) {
        super(message);
    }
}
