package su.sergey.contacts.directory.valueobjects.searchparameters;

import java.io.Serializable;
import java.util.Properties;

import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;

/**
 * ��������� ��� ������ ��������� �����������.
 * @author ������ ��������
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
