package su.sergey.contacts.codegen.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;

/**
 * ImportGenerator
 * 
 * @author Сергей Богданов
 */
public class ImportGenerator implements TypeListener, TableListener {
	private static final String GROUPS[] ={"java.lang.", "java.", "javax.", ""};
	
	private Map types;
	private Map shortTypeNames;
	
	/**
	 * Constructor for ImportGenerator
	 */
	public ImportGenerator() {
		types = new HashMap();
		shortTypeNames = new HashMap();
		for (int i = 0; i < GROUPS.length; i++) {
			types.put(GROUPS[i], new TreeSet());
		}
	}

	private String getPackageName(String typeName) {
		if (typeName == null) {
			return null;
		}
		int dotPosition = typeName.lastIndexOf(".");
		if (dotPosition < 0) {
			return null;
		}
		return typeName.substring(0, dotPosition);
	}
	
	/**
	 * @see TypeListener#type(String)
	 */
	public String type(String fullTypeName) {
		String packageName = getPackageName(fullTypeName);
		String shortTypeName = fullTypeName;
		if (packageName != null) {
		    shortTypeName = fullTypeName.substring(packageName.length() + 1);
		}
		if(shortTypeNames.get(shortTypeName) == null) {
			shortTypeNames.put(shortTypeName, fullTypeName);
		}
		if (!shortTypeNames.get(shortTypeName).equals(fullTypeName)) {
			return fullTypeName;
		} else {
			insertTypeImport(fullTypeName);
		    return shortTypeName;
		}
	}
	
	private void insertTypeImport(String typeName) {
		for (int i = 0; i < GROUPS.length; i++) {
			if (typeName.startsWith(GROUPS[i])) {
				((Collection) types.get(GROUPS[i])).add(typeName);
				break;
			}
		}
	}
	
	/**
	 * @see TypeListener#type(Class)
	 */
	public String type(Class type) {
		return type(type.getName());
	}
	
	private String makeImports(Collection typeNames) {
		StringBuffer result = new StringBuffer();
		for (Iterator i = typeNames.iterator(); i.hasNext();) {
			String typeName = (String) i.next();
			result.append("import ");
			result.append(typeName);
			result.append(";\n");
		}
		if (!typeNames.isEmpty()) {
			result.append("\n");
		}
		return result.toString();
	}
	
	/**
	 * @see TableListener#startTable(Table)
	 */
	public void startTable(Table table) {
		shortTypeNames.clear();
		for (Iterator i = types.keySet().iterator(); i.hasNext();) {
			((Collection) types.get(i.next())).clear();
		}
	}

	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {}

	/**
	 * @see TableListener#endTable()
	 */
	public void endTable() {}

	public String getImports() {
		StringBuffer result = new StringBuffer();
		for (int i = 1; i < GROUPS.length; i++) {
			result.append(makeImports((Collection) types.get(GROUPS[i])));
		}
		return result.toString();
	}
}
