package su.sergey.contacts.directory;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import su.sergey.contacts.directory.dao.FindDirectoryDAO;
import su.sergey.contacts.valueobjects.DirectoryMetadata;
import su.sergey.contacts.valueobjects.handles.DirectoryMetadataHandle;

/**
 * Bean implementation class for Enterprise Bean: Directory
 */
public class DirectoryBean implements SessionBean {
	private SessionContext mySessionCtx;
	
	public DirectoryMetadata findDirectoryMetadata(DirectoryMetadataHandle handle) {
		return FindDirectoryDAO.getInstance().findDirectoryMetadata(handle);
	}
	
	public void updateDirectoryMetadata(DirectoryMetadataHandle directoryMetadataHandle,
	                                    DirectoryMetadata directoryMetadata) {
	    FindDirectoryDAO.getInstance().updateDirectoryMetadata(directoryMetadataHandle, directoryMetadata);
    }
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
