package su.sergey.contacts.valueobjects;

import java.io.Serializable;

/**
 * �������� ������ �� ����� ������ �������.
 * 
 * @author: ������ ��������
 */
public interface DirectoryRecord extends Serializable {
    /**
     * ���������� �������� ����� ������ �����������.
     */
    String[] getValues();

    /**
     * ���������� �������� PK �����������.
     */
    Integer getOid();
}
