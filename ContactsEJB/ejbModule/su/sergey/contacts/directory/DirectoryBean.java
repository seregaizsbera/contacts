package su.sergey.contacts.directory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryMetadataHandle;
import su.sergey.contacts.directory.valueobjects.handles.DirectoryRecordHandle;

/**
 * Bean implementation class for Enterprise Bean: Directory
 */
public class DirectoryBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle) {
		return FindDirectoryDAO.getInstance().findDirectoryMetadata(handle);
	}
	
	public DirectoryRecord findDirectoryRecord(DirectoryRecordHandle handle) {
		FindDirectoryDAO directoryDao = FindDirectoryDAO.getInstance();
		DirectoryRecord result = directoryDao.findDirectoryRecord(handle);
		return result;
	}
	
	public void addDirectoryRecord(DirectoryMetadataHandle directoryMetadataHandle, DirectoryRecord directoryRecord) {
		FindDirectoryDAO directoryDao = FindDirectoryDAO.getInstance();
		directoryDao.addDirectoryRecord(directoryMetadataHandle, directoryRecord);
	}
	
	public void removeDirectoryRecord(DirectoryRecordHandle directoryRecordHandle) {
		FindDirectoryDAO directoryDao = FindDirectoryDAO.getInstance();
		directoryDao.removeDirectoryRecord(directoryRecordHandle);
	}

	public void updateDirectoryRecord(DirectoryRecordHandle directoryRecordHandle, DirectoryRecord directoryRecord) {
		FindDirectoryDAO directoryDao = FindDirectoryDAO.getInstance();
		directoryDao.updateDirectoryRecord(directoryRecordHandle, directoryRecord);
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
	public void ejbCreate() throws CreateException {}
	
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {}
	
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {}
}
