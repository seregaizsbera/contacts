package su.sergey.contacts.codegen.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import su.sergey.contacts.codegen.db.TypeListener;

/**
 * ImportGenerator
 * 
 * @author Сергей Богданов
 */
public class ImportGenerator implements TypeListener {
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
	
	public String getImports() {
		StringBuffer result = new StringBuffer();
		for (int i = 1; i < GROUPS.length; i++) {
			result.append(makeImports((Collection) types.get(GROUPS[i])));
		}
		return result.toString();
	}
	
	public static void main(String args[]) {
		try {
			ImportGenerator generator = new ImportGenerator();
			System.err.println(generator.type(String.class));
			System.err.println(generator.type(String.class));
			System.err.println(generator.type(Collection.class));
			System.err.println(generator.type(ImportGenerator.class));
			System.err.println(generator.type(Collection.class));
			System.err.println(generator.type(Collection.class));
			System.err.println(generator.type(Collection.class));
			System.err.println(generator.type(ImportGenerator.class));
			System.err.println(generator.type(ImportGenerator.class));
			System.err.println(generator.type("java.lang.String"));
			System.err.println(generator.type(ImportGenerator.class.getName()));
			System.err.println(generator.type(TreeSet.class.getName()));
			System.err.println(generator.type("Test"));
			System.err.println(generator.type("su.sergey.create.ClassGenerator"));
			System.err.println(generator.type("su.sergey.create.ClassGenerator"));
			System.err.println(generator.type("su.sergey.data.ClassGenerator"));
			System.err.println(generator.type("su.sergey.update.ClassGenerator"));
			System.err.println(generator.type("su.sergey.data.ClassGenerator"));
			System.err.println(generator.type("su.sergey.create.ClassGenerator"));
			System.err.println(generator.type("su.sergey.create.String"));
			System.err.println(generator.type("su.sergey.create.Integer"));
			System.err.println(generator.type("java.lang.Integer"));
			System.err.println(generator.type("java.lang.Boolean"));
			System.err.println(generator.type("su.sergey.create.Boolean"));
			System.err.println(generator.type("Character"));
			System.err.println(generator.type("java.lang.Character"));
			System.err.println("---------------");
			System.err.print(generator.getImports());
			System.err.println("---------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
