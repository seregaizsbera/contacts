package su.sergey.contacts.directory.valueobjects.handles;

import java.io.Serializable;

/**
 * ���������� ������ �������.
 * �������� ��������� ���� - ���������� ������������� ������,
 * � ���������� �������, � ������� ������ ���������.
 * 
 * @author ������ ��������
 */
public class DirectoryRecordHandle implements Serializable {
	/**
	 * ���������� �������
	 */
	private DirectoryMetadataHandle directoryMetadataHandle;
	
    /**
     * �������� oid ������
     */
    private Integer oid;

    /**
     * ������� ������.
     */
    public DirectoryRecordHandle(DirectoryMetadataHandle directoryMetadataHandle, Integer oid) {
        this.directoryMetadataHandle = directoryMetadataHandle;
        this.oid = oid;
    }
    
	/**
	 * Gets the oid
	 * 
	 * @return Returns a Integer
	 */
	public Integer getOid() {
		return oid;
	}
	
	/**
	 * Gets the directoryMetadataHandle
	 * 
	 * @return Returns a DirectoryMetadataHandle
	 */
	public DirectoryMetadataHandle getDirectoryMetadataHandle() {
		return directoryMetadataHandle;
	}
}
