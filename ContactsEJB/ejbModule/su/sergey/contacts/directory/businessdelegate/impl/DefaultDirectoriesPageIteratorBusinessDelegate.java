package su.sergey.contacts.directory.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.directory.DirectoriesPageIterator;
import su.sergey.contacts.directory.DirectoriesPageIteratorHome;
import su.sergey.contacts.directory.businessdelegate.DirectoriesPageIteratorBusinessDelegate;
import su.sergey.contacts.directory.valueobjects.DirectoryMetadata;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.util.dao.DAOException;
import su.sergey.contacts.exceptions.*;


/**
 * Имплементация интерфейса <code>IDirectoryMetadatasPageIteratorBusinessDelegate</code>
 */
public class DefaultDirectoriesPageIteratorBusinessDelegate implements DirectoriesPageIteratorBusinessDelegate {
    private DirectoriesPageIterator iterator;
    private int pageSize;
    private String jndiName;

    /**
     * Создаёт новый объект для записей таблицы
     *
     * @param searchParameters параметры поиска
     * @param pageSize Число записей на страницу
     * */
    public DefaultDirectoriesPageIteratorBusinessDelegate(String jndiName, int pageSize) {
    	this.jndiName = jndiName;
    	this.pageSize = pageSize;
		initIterator();
    }

	private void initIterator() {
		Context ctx;
		DirectoriesPageIteratorHome home;
		try {
		   ctx = new InitialContext();
		   Object homeObject = ctx.lookup(jndiName);
		   home = (DirectoriesPageIteratorHome)
		           PortableRemoteObject.narrow(homeObject,
		                   DirectoriesPageIteratorHome.class );
		   iterator = home.create(pageSize);
		} catch (NamingException e) {
		   e.printStackTrace();
		} catch (CreateException e) {
		   e.printStackTrace();
		} catch (RemoteException e) {
		   e.printStackTrace();
		}
	}

    public DirectoryMetadata[] next() {
        try {
            return iterator.next();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.next();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public DirectoryMetadata[] current() {
        try {
            return iterator.current();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.current();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public DirectoryMetadata[] prev() {
        try {
            return iterator.prev();
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.prev();
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
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
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
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
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
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
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
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
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
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
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }

    public DirectoryMetadata[] goToPage(int number) {
        try {
            return iterator.goTo(number);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(number);
	        } catch (DAOException e1) {
	            throw new RuntimeDelegateException(e);
            } catch (RemoteException e1) {
                throw new RuntimeDelegateException(e1);
            }
        } catch (DAOException e) {
            throw new RuntimeDelegateException(e);
        } catch (RemoteException e) {
            throw new RuntimeDelegateException(e);
        }
    }
    
	/**
	 * 
	 * @see DirectoriesPageIteratorBusinessDelegate#freeResources()
	 */
	public void freeResources() {
        if (iterator != null) {
            try {
                iterator.remove();
            } catch (NoSuchObjectException e) {}
              catch (RemoveException e) {}
              catch (RemoteException e) {}
        }
        iterator = null;
	}
}
