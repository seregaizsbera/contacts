package su.sergey.contacts.valueobjects.handles;

import java.io.Serializable;

/**
 * ���������� ��������� �������.
 * 
 * @author ������ ��������
 */
public class DirectoryMetadataHandle implements Serializable {
    /**
     * ��� �������.
     */
    private String tableName;

    /**
     * ������� ������.
     */
    public DirectoryMetadataHandle(String tableName) {
        this.tableName = tableName;
    }

	/**
	 * Gets the tableName
	 * 
	 * @return Returns a String
	 */
	public String getTableName() {
		return tableName;
	}
}
