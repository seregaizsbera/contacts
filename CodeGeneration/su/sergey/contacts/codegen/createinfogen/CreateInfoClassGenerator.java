package su.sergey.contacts.codegen.createinfogen;

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
 * CreateInfoClassGenerator
 * 
 * @author Cергей Богданов
 */
public class CreateInfoClassGenerator extends Broadcaster {
	private ImportGenerator importGenerator;
    private MethodGenerator methodGenerator;
    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;
    private String packageName;
    private FileHelper fileHelper;

    public CreateInfoClassGenerator(FileHelper fileHelper, String packageName) {
    	this.fileHelper = fileHelper;
    	this.packageName = packageName;
        dataClass = new StringBuffer();
        importGenerator = new ImportGenerator();
        methodGenerator = new MethodGenerator(importGenerator);
        addListener(importGenerator);
        addListener(methodGenerator);
    }

    public void startTable(Table table) {
        isTarget = HelperFactory.getHelper().isTarget(table);
        if (isTarget) {
            currentTable = table;
            dataClass.delete(0, dataClass.length());
            super.startTable(table);
        }
    }

    public void attribute(Attribute attribute) {
        if (isTarget) {
            super.attribute(attribute);
        }
    }

    public void endTable() {
        if (isTarget) {
            super.endTable();
            dataClass.append("package ").append(packageName).append(";\n\n");
            dataClass.append(importGenerator.getImports());
            dataClass.append("public interface ");
            dataClass.append(HelperFactory.getHelper().getCreateInfoClassName(currentTable)).append(" {\n");
            dataClass.append(methodGenerator.getMethods());
            dataClass.append("}\n");
            try {
            	String fileName = fileHelper.prepareFile(packageName, HelperFactory.getHelper().getCreateInfoClassName(currentTable) + ".java");
                Writer out = new BufferedWriter(new FileWriter(fileName));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
