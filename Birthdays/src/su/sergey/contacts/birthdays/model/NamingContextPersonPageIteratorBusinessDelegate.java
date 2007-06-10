package su.sergey.contacts.birthdays.model;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.rmi.PortableRemoteObject;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import su.sergey.contacts.exceptions.RuntimeDelegateException;
import su.sergey.contacts.person.PersonPageIterator;
import su.sergey.contacts.person.PersonPageIteratorHome;
import su.sergey.contacts.person.businessdelegate.PersonPageIteratorBusinessDelegate;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.person.valueobjects.Person2;

public class NamingContextPersonPageIteratorBusinessDelegate implements PersonPageIteratorBusinessDelegate {
    private final PersonPageIteratorHome home;
    private final PersonSearchParameters searchParameters;
    private final int pageSize;    
    private PersonPageIterator iterator;
    
    private void initIterator() {
        try {
	    iterator = home.create(searchParameters, pageSize);
	} catch (CreateException e) {
	    throw new RuntimeDelegateException(e);
	} catch (RemoteException e) {
	    throw new RuntimeDelegateException(e);
	}
    }
    
    public NamingContextPersonPageIteratorBusinessDelegate(NamingContext context, String jndiName, PersonSearchParameters searchParameters, int pageSize) {
        try {
	    org.omg.CORBA.Object object = context.resolve(NamingUtil.toCorbaName(jndiName));
	    home = (PersonPageIteratorHome) PortableRemoteObject.narrow(object, PersonPageIteratorHome.class);
	    this.searchParameters = searchParameters;
	    this.pageSize = pageSize;
	    initIterator();
	} catch (InvalidName e) {
	    throw new RuntimeDelegateException(e);
	} catch (CannotProceed e) {
	    throw new RuntimeDelegateException(e);
	} catch (NotFound e) {
	    throw new RuntimeDelegateException(e);
	}
    }

    public Person2[] prev() {
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
    
    public Person2[] current() {
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
    
    public Person2[] next() {
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
    
    public Person2[] goTo(int page) {
        try {
            return iterator.goTo(page);
        } catch (NoSuchObjectException e) {
            initIterator();
            try {
                return iterator.goTo(page);
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
