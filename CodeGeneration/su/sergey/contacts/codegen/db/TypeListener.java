package su.sergey.contacts.codegen.db;

/**
 * TypeListener
 * 
 * @author ������ ��������
 */
public interface TypeListener {
	String type(Class type);
	String type(String fullTypeName);
}
