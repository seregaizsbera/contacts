package su.sergey.contacts.supply.businessdelegate.impl;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.supply.SupplyPageIterator;
import su.sergey.contacts.supply.SupplyPageIteratorHome;
import su.sergey.contacts.supply.businessdelegate.SupplyPageIteratorBusinessDelegate;
import su.sergey.contacts.supply.searchparameters.SupplySearchParameters;
import su.sergey.contacts.supply.valueobjects.Supply2;
import su.sergey.contacts.util.dao.DAOException;

public class DefaultSupplyPageIteratorBusinessDelegate implements SupplyPageIteratorBusinessDelegate {
	private SupplySearchParameters searchParameters;
	private int pageSize;
	private SupplyPageIterator iterator;
	private String jndiName;

    private void initIterator() {
    	try {
    		Context context = new InitialContext();
    		Object object = context.lookup(jndiName);
    		SupplyPageIteratorHome home = (SupplyPageIteratorHome) PortableRemoteObject.narrow(object, SupplyPageIteratorHome.class);
    		iterator = home.create(searchParameters, pageSize);
    	} catch (CreateException e) {
    		throw new RuntimeDelegateException(e);
    	} catch (NamingException e) {
    		throw new RuntimeDelegateException(e);
    	} catch (RemoteException e) {
    		throw new RuntimeDelegateException(e);
    	}
    }
    
	/**
	 * Constructor for DefaultSupplyPageIteratorBusinessDelegate
	 */
	public DefaultSupplyPageIteratorBusinessDelegate(String jndiName, SupplySearchParameters searchParameters, int pageSize) {
		if (searchParameters == null) {
			throw new NullPointerException("searchParamters");
		}
		this.searchParameters = searchParameters;
		this.pageSize = pageSize;
		this.iterator = null;
		this.jndiName = jndiName;
		initIterator();
	}

	/**
	 * @see SupplyPageIteratorBusinessDelegate#prev()
	 */
	public Supply2[] prev() {
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

	/**
	 * @see SupplyPageIteratorBusinessDelegate#current()
	 */
	public Supply2[] current() {
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

	/**
	 * @see SupplyPageIteratorBusinessDelegate#next()
	 */
	public Supply2[] next() {
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

	/**
	 * @see SupplyPageIteratorBusinessDelegate#goTo(int)
	 */
	public Supply2[] goTo(int page) {
        try {
            return iterator.goTo(page);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(page);
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
	 * @see PageIteratorBusinessDelegate#hasNext()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#hasPrev()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getCurrentPage()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getPageSize()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#getNumberOfPages()
	 */
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

	/**
	 * @see PageIteratorBusinessDelegate#freeResources()
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
