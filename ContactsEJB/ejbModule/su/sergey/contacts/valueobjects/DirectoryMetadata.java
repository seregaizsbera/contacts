package su.sergey.contacts.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

/**
 * �������� �������� ��������� �������.
 * 
 * @author: ������ ��������
 */
public interface DirectoryMetadata extends Serializable {

    /**
     * ���������� ���������� �����������.
     */
    DirectoryMetadataHandle getHandle();
    
    /**
     * ���������� ���������� � �����������.
     */
    String getDescription();

    /**
     * ������������� ���������� � �����������.
     */
    void setDescription(String description);
    
    /**
     * ���������� �������� ����� �� ������������ � ���� ������.
     */
    String getDbSchemaName();

    /**
     * ���������� �������� ������� �� ������������ � ���� ������.
     */
    String getDbTableName();

    /**
     * ���������� ������ ����-��������� �����������.
     */
    DirectoryColumnMetadata[] getColumnMetadata();
}
