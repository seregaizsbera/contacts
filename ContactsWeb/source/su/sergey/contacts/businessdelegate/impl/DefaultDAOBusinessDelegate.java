package su.sergey.contacts.businessdelegate.impl;

import java.util.Collection;

import su.sergey.contacts.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.exceptions.InvalidValueException;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.DirectoryRecord;
import su.sergey.contacts.valueobjects.SystemParameter;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.valueobjects.handles.SystemParameterHandle;
import su.sergey.contacts.valueobjects.searchparameters.DirectoryRecordSearchParameters;

public class DefaultDAOBusinessDelegate implements DAOBusinessDelegate {
    public DefaultDAOBusinessDelegate() {}

	/**
	 * @see DAOBusinessDelegate#findSystemParameter(SystemParameterHandle)
	 */
	public SystemParameter findSystemParameter(SystemParameterHandle systemParameterHandle) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#findSystemParameters(int, int)
	 */
	public Collection findSystemParameters(int start, int length) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#findDirectoryMetadata(DirectoryMetadataHandle)
	 */
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#updateDirectoryMetadata(DirectoryMetadata)
	 */
	public void updateDirectoryMetadata(DirectoryMetadata directoryMetadata) {}

	/**
	 * @see DAOBusinessDelegate#findDirectoryRecords(DirectoryRecordSearchParameters, int, int)
	 */
	public Collection findDirectoryRecords(DirectoryRecordSearchParameters searchParameters, int start, int length) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#findDirectoryRecord(DirectoryRecordHandle)
	 */
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {
		return null;
	}

	/**
	 * @see DAOBusinessDelegate#addDirectoryRecord(DirectoryMetadataHandle, DirectoryRecord)
	 */
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) {}

	/**
	 * @see DAOBusinessDelegate#deleteDirectoryRecord(DirectoryRecordHandle)
	 */
	public void deleteDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {}

	/**
	 * @see DAOBusinessDelegate#getSystemProperties()
	 */
	public Collection getSystemProperties() {
		return null;
	}
	/**
	 * @see DAOBusinessDelegate#updateDirectoryRecord(DirectoryRecordHandle, DirectoryRecord)
	 */
	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) {}

	/**
	 * @see DAOBusinessDelegate#updateSystemProperty(String, String)
	 */
	public void updateSystemProperty(String name, String value) throws InvalidValueException {}
}
