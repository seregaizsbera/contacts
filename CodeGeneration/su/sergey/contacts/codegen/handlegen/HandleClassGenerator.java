package su.sergey.contacts.codegen.handlegen;

import su.sergey.contacts.codegen.FileHelper;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.impl.ImportGenerator;

/**
 * HandleClassGenerator
 * 
 * @author Сергей Богданов
 */
public class HandleClassGenerator extends Broadcaster {
	private ImportGenerator importGenerator;
	private MethodGenerator methodGenerator;
	private FieldsGenerator fieldsGenerator;
	private boolean isTarget;
	private FileHelper fileHelper;
	private String packageName;
	
	/**
	 * Constructor for HandleClassGenerator
	 */
	public HandleClassGenerator(FileHelper fileHelper, String packageName) {
		this.fileHelper = fileHelper;
		this.packageName = packageName;
		importGenerator = new ImportGenerator();
		methodGenerator = new MethodGenerator(importGenerator);
		fieldsGenerator = new FieldsGenerator(importGenerator);
		addListener(methodGenerator);
		addListener(fieldsGenerator);
		isTarget = false;
	}
	
	/**
	 * @see Broadcaster#startTable(Table)
	 */
	public void startTable(Table table) {
		isTarget = Helper.isTarget(table);
		if (isTarget) {
			importGenerator.init();
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
		}
	}
}
