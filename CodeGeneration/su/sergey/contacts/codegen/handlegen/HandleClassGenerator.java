package su.sergey.contacts.codegen.handlegen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import su.sergey.contacts.codegen.util.FileHelper;
import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.impl.ImportGenerator;
import su.sergey.contacts.codegen.util.*;


/**
 * HandleClassGenerator
 * 
 * @author Сергей Богданов
 */
public class HandleClassGenerator extends Broadcaster {
	private ImportGenerator importGenerator;
	private MethodGenerator methodGenerator;
	private FieldsGenerator fieldsGenerator;
	private ConstructorGenerator constructorGenerator;
	private boolean isTarget;
	private FileHelper fileHelper;
	private String packageName;
	private Table theTable;
	
	/**
	 * Constructor for HandleClassGenerator
	 */
	public HandleClassGenerator(FileHelper fileHelper, String packageName) {
		this.fileHelper = fileHelper;
		this.packageName = packageName;
		importGenerator = new ImportGenerator();
		methodGenerator = new MethodGenerator(importGenerator);
		fieldsGenerator = new FieldsGenerator(importGenerator);
		constructorGenerator = new ConstructorGenerator(importGenerator);
		addListener(importGenerator);
		addListener(methodGenerator);
		addListener(fieldsGenerator);
		addListener(constructorGenerator);
		isTarget = false;
	}
	
	/**
	 * @see Broadcaster#startTable(Table)
	 */
	public void startTable(Table table) {
		isTarget = HelperFactory.getHelper().isTarget(table);
		if (isTarget) {
			theTable = table;
			super.startTable(table);
		}
	}

	/**
	 * @see Broadcaster#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {
		if (isTarget) {
			super.attribute(attribute);
		}
	}

	/**
	 * @see Broadcaster#endTable()
	 */
	public void endTable() {
		if (isTarget) {
			super.endTable();
			saveTable();
		}
	}
	
	private void saveTable() {
		StringBuffer result = new StringBuffer();
		String serializable = importGenerator.type("java.io.Serializable");
		result.append("package ").append(packageName).append(";\n\n");
		result.append(importGenerator.getImports());
		result.append("public final class ").append(HelperFactory.getHelper().getHandleClassName(theTable));
		result.append(" implements ").append(serializable).append(" {\n");
		result.append(fieldsGenerator.getFields()).append("\n");
		result.append(constructorGenerator.getConstructor()).append("\n");
		result.append(methodGenerator.getMethods()).append("}\n");
        try {
            Writer out = new BufferedWriter(new FileWriter(
                    fileHelper.prepareFile(packageName, HelperFactory.getHelper().getHandleClassName(theTable) + ".java")));
            out.write(result.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
