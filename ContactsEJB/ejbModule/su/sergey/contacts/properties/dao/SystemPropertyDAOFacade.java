package su.sergey.contacts.properties.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import su.sergey.contacts.dao.SystemPropertyDAO;
import su.sergey.contacts.dto.SystemPropertyData;
import su.sergey.contacts.dto.SystemPropertyHandle;
import su.sergey.contacts.properties.InvalidPropertyValueException;
import su.sergey.contacts.properties.PropertyMaker;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.properties.impl.DefaultPropertyMaker;
import su.sergey.contacts.util.dao.AbstractDAO;
import su.sergey.contacts.util.dao.ConnectionSource;

public class SystemPropertyDAOFacade extends AbstractDAO {
    private final SystemPropertyDAO systemPropertyDao;

	private Class getMakerClass(String name) throws ClassNotFoundException {
		if (name == null) {
			return DefaultPropertyMaker.class;
		}
		Class result = SystemPropertyDAOFacade.class.getClassLoader().loadClass(name);
		return result;
	}
	
	private PropertyMaker getPropertyMaker(String makerClassName, String format, String propertyClassName) {
		try {
			Class makerClass = getMakerClass(makerClassName);
			Class args[] = {String.class, String.class};
			Constructor constructor = makerClass.getConstructor(args);
			Object params[] = {format, propertyClassName};
			Object object = constructor.newInstance(params);
			PropertyMaker result = (PropertyMaker) object;
			return result;
		} catch (ClassNotFoundException e ) {
			throw new IllegalArgumentException(makerClassName);
		} catch (NoSuchMethodException e ) {
			throw new IllegalArgumentException(makerClassName);
		} catch (InstantiationException e ) {
			throw new IllegalArgumentException(makerClassName);
		} catch (IllegalAccessException e ) {
			throw new IllegalArgumentException(makerClassName);
		} catch (InvocationTargetException e ) {
			throw new IllegalArgumentException(makerClassName);
		}
	}

	public Object getValue(String name) throws PropertyNotFoundException {
		SystemPropertyHandle handle = new SystemPropertyHandle(name);
		SystemPropertyData data = systemPropertyDao.find(handle);
		if (data == null) {
			throw new PropertyNotFoundException(name);
		}
		PropertyMaker maker = getPropertyMaker(data.getMaker(), data.getFormat(), data.getType());
    	Object result = maker.makePropertyValue(data.getValue());
		return result;
	}
	
    public void setValue(String name, Object value) throws PropertyNotFoundException {
		SystemPropertyHandle handle = new SystemPropertyHandle(name);
		SystemPropertyData data = systemPropertyDao.find(handle);
		if (data == null) {
			throw new PropertyNotFoundException(name);
		}
		PropertyMaker maker = getPropertyMaker(data.getMaker(), data.getFormat(), data.getType());
		String strValue = maker.getPropertyStringValue(value);
		data.setValue(strValue);
		systemPropertyDao.update(handle, data);
    }
    
    public void setValue(String name, String value) throws InvalidPropertyValueException, PropertyNotFoundException {
		SystemPropertyHandle handle = new SystemPropertyHandle(name);
		SystemPropertyData data = systemPropertyDao.find(handle);
		if (data == null) {
			throw new PropertyNotFoundException(name);
		}
		PropertyMaker maker = getPropertyMaker(data.getMaker(), data.getFormat(), data.getType());
		if (!maker.validateValue(value)) {
			throw new InvalidPropertyValueException(value);
		}
		data.setValue(value);
		systemPropertyDao.update(handle, data);
    }
    
    /**
     * Constructor for SystemPropertyDAOFacade
     */
    public SystemPropertyDAOFacade(ConnectionSource connectionSource) {
        super(connectionSource);
        systemPropertyDao = new SystemPropertyDAO(connectionSource);	
    }
}
