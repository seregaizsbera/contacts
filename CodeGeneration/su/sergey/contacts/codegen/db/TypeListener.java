package su.sergey.contacts.codegen.db;

/**
 * TypeListener
 * 
 * @author Сергей Богданов
 */
public interface TypeListener {
	String type(Class type);
	String type(String fullTypeName);
}
