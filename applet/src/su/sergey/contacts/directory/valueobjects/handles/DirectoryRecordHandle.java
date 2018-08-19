package su.sergey.contacts.directory.valueobjects.handles;

import java.io.Serializable;

/**
 * Дескриптор записи таблицы.
 * Содержит первичный ключ - внутренний идентификатор записи,
 * и дескриптор таблицы, в которой запись находится.
 * 
 * @author Сергей Богданов
 */
public class DirectoryRecordHandle implements Serializable {
	/**
	 * Дескриптор таблицы
	 */
	private DirectoryMetadataHandle directoryMetadataHandle;
	
    /**
     * Значение oid записи
     */
    private Integer oid;

    /**
     * Создает объект.
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
