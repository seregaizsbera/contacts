package su.sergey.contacts.report;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import su.sergey.contacts.dao.ReportDAO;
import su.sergey.contacts.dto.ReportData;
import su.sergey.contacts.dto.ReportHandle;
import su.sergey.contacts.report.valueobjects.ReportConfig;

public class ReportBuilderFactory {
    public static ReportBuilder getReportBuilder(ReportConfig config) {
    	ReportDAO dao = ReportDAO.getInstance();
    	ReportHandle handle = new ReportHandle(config.getType());
    	ReportData data = dao.find(handle);
    	String builderClassName = data.getBuilder();
    	ClassLoader loader = ReportBuilderFactory.class.getClassLoader();
    	ReportBuilder result = null;
    	try {
    	    Class builderClass = loader.loadClass(builderClassName);
    	    Class constructorSignature[] = { ReportConfig.class };
    	    Object constructorParameters[] = { config };
    	    Constructor constructor = builderClass.getConstructor(constructorSignature);
    	    result = (ReportBuilder) constructor.newInstance(constructorParameters);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (NoSuchMethodException e) {
    		e.printStackTrace();
    	} catch (InvocationTargetException e) {
    		e.printStackTrace();
    	} catch (InstantiationException e) {
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
}
