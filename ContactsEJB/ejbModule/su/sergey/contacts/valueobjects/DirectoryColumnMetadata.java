package su.sergey.contacts.valueobjects;

import java.io.Serializable;

/**
 * �������� �������� ���������� ���� �����������.
 * 
 * @author: ������ ��������
 */
public interface DirectoryColumnMetadata extends Serializable {

    /**
     * ���������� ��� ������� � ���� ������.
     */
    String getDbColumnName();

    /**
     * ���������� ������ ������������.
     */
    String getFullName();

    /**
     * ������������� ����� ������ ������������
     */
    void setFullName(String fullName);

    /**
     * ���������� ������ ����.
     */
    int getWidth();

    /**
     * ���������� ��� ������ ����.
     */
    int getType();

    /**
     * ����������, ����� �� ���� � ���� ����
     */
    boolean isNullable();
    
}
