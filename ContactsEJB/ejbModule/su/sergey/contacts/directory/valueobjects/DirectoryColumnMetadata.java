package su.sergey.contacts.directory.valueobjects;

import java.io.Serializable;

/**
 * �������� �������� ���������� c������ �������.
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
    
    /**
     * ����������, ������������� �� �������� ������� ���� ������ �������������
     */
    boolean isGenerated();
}
