package su.sergey.contacts.directory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;
import su.sergey.contacts.util.dao.ConnectionSource;
import su.sergey.contacts.util.dao.ContainerConnectionSource;

/**
 * Bean implementation class for Enterprise Bean: Directory
 */
public class DirectoryBean implements SessionBean {
	private SessionContext mySessionCtx;
	private FindDirectoryDAO findDirectoryDAO;
	
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle) {
		return findDirectoryDAO.findDirectoryMetadata(handle);
	}
	
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle handle) {
		DirectoryRecord result = findDirectoryDAO.findDirectoryRecord(handle);
		return result;
	}
	
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) {
		findDirectoryDAO.addDirectoryRecord(directoryMetadataHandle, directoryRecord);
	}
	
	public void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {
		findDirectoryDAO.removeDirectoryRecord(directoryRecordHandle);
	}

	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) {
		findDirectoryDAO.updateDirectoryRecord(directoryRecordHandle, directoryRecord);
	}
	
	//----------------------------------------------------------------------------------------
			
	/**
	 * getSessionContext
	 */
	public SessionContext getSessionContext() {
		return mySessionCtx;
	}
	
	/**
	 * setSessionContext
	 */
	public void setSessionContext(SessionContext ctx) {
		mySessionCtx = ctx;
	}
	
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {}
	
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws CreateException {
	    ConnectionSource connectionSource = new ContainerConnectionSource();
            findDirectoryDAO = new FindDirectoryDAO(connectionSource);
	}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {}
}
