package su.sergey.contacts.directory.valueobjects;

import java.io.Serializable;

import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;

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
