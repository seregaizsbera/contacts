package su.sergey.contacts.directory.valueobjects;

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
