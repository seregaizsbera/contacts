package su.sergey.contacts.valueobjects.searchparameters;

import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

import java.util.Properties;
import java.io.Serializable;

/**
 * ��������� ��� ������ ��������� �����������.
 * @author 
 * @version 1.0 
 */
public class DirectoryRecordSearchParameters implements Serializable {
    /**
     * ���������� ��������� �����������.
     */
    private DirectoryMetadataHandle directoryMetadataHandle;

    /**
     * �������� ��� ������ ��� ��������� ����� (������������� ��������)
     */
    private Properties parameters;

    /**
     * ������� ������.
     */
    public DirectoryRecordSearchParameters(DirectoryMetadataHandle directoryMetadataHandle, Properties parameters) {
        this.directoryMetadataHandle = directoryMetadataHandle;
        this.parameters = parameters;
    }

    /**
     * ���������� ���������� ��������� �����������.
     */
    public DirectoryMetadataHandle getDirectoryMetadataHandle() {
        return directoryMetadataHandle;
    }

    /**
     * ���������� �������� ��� ������ ��� ��������� �����.
     */
    public Properties getParameters() {
        return parameters;
    }
}