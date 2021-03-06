package su.sergey.contacts.directory.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.directory.DirectoryRecordsPageIterator;
import su.sergey.contacts.directory.DirectoryRecordsPageIteratorHome;
import su.sergey.contacts.directory.businessdelegate.DirectoryRecordsPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.valueobjects.DirectoryRecord;
import su.sergey.contacts.directory.valueobjects.searchparameters.DirectoryRecordSearchParameters;
import su.sergey.contacts.exceptions.RuntimeDelegateException;

/**
 * ������������� ���������� <code>DirectoryRecordsPageIteratorBusinessDelegate</code>
 */
public class DefaultDirectoryRecordsPageIteratorBusinessDelegate
        implements DirectoryRecordsPageIteratorBusinessDelegate {
    private DirectoryRecordsPageIterator iterator;
    private DirectoryRecordSearchParameters searchParameters;
    private int pageSize;
    private String jndiName;

    /**
     * ������� ����� ������ ��� ������� �����������
     *
     * @param searchParameters ��������� ������
     * @param pageSize ����� ������� �� ��������
     * */
    public DefaultDirectoryRecordsPageIteratorBusinessDelegate (String jndiName, DirectoryRecordSearchParameters searchParameters, int pageSize) {
        if (searchParameters == null) {
            throw new NullPointerException("Null directory search parameters");
        }
        this.searchParameters = searchParameters;
        this.pageSize = pageSize;
        this.iterator = null;
        this.jndiName = jndiName;
	    initIterator();
    }

	private void initIterator() {
		try {
		   Context ctx = new InitialContext();
		   Object homeObject = ctx.lookup(jndiName);
		   DirectoryRecordsPageIteratorHome home = (DirectoryRecordsPageIteratorHome)
		           PortableRemoteObject.narrow(homeObject,
		                   DirectoryRecordsPageIteratorHome.class );
		   iterator = home.create(searchParameters, pageSize);
		} catch (NamingException e) {
    		throw new RuntimeDelegateException(e);
		} catch (CreateException e) {
    		throw new RuntimeDelegateException(e);
		} catch (RemoteException e) {
    		throw new RuntimeDelegateException(e);
		}
	}
    
    public DirectoryRecord[] next() {
        try {
            return iterator.next();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.next();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public DirectoryRecord[] current() {
        try {
            return iterator.current();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.current();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public DirectoryRecord[] prev() {
        try {
            return iterator.prev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.prev();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public boolean hasNext() {
        try {
            return iterator.hasNext();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.hasNext();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public boolean hasPrev() {
        try {
            return iterator.hasPrev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.hasPrev();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public int getCurrentPage() {
        try {
            return iterator.getCurrentPage();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getCurrentPage();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public int getPageSize() {
        try {
            return iterator.getPageSize();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getPageSize();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public int getNumberOfPages() {
        try {
            return iterator.getTotalPageCount();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.getTotalPageCount();
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public DirectoryRecord[] goToPage(int number) {
        try {
            return iterator.goTo(number);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(number);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }
    
	/**
	 * @see DirectoryRecordsPageIteratorBusinessDelegate#freeResources()
	 */
	public void freeResources() {
        if (iterator != null) {
            try {
                iterator.remove();
            } catch (NoSuchObjectException e) {
            } catch (RemoveException e) {
            } catch (RemoteException e) {
            }
        }
        iterator = null;
	}
}
