package su.sergey.contacts.codegen.datagen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import su.sergey.contacts.codegen.Environment;
import su.sergey.contacts.codegen.FileHelper;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.impl.ImportGenerator;

/**
 * DataClassGenerator
 * @author Сергей Богданов
 */
public class DataClassGenerator extends Broadcaster implements TableListener {
    private ImportGenerator importGenerator;
    private FieldsGenerator fieldsGenerator;
    private MethodGenerator methodGenerator;
    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;
    private FileHelper fileHelper;
    private String packageName;

    public DataClassGenerator(FileHelper fileHelper, String packageName) {
    	this.fileHelper = fileHelper;
    	this.packageName = packageName;
        dataClass = new StringBuffer();
        importGenerator = new ImportGenerator();
        fieldsGenerator = new FieldsGenerator(importGenerator);
        methodGenerator = new MethodGenerator(importGenerator);
        addListener(importGenerator);
        addListener(fieldsGenerator);
        addListener(methodGenerator);
    }

    public void startTable(Table table) {
    	isTarget = Helper.isTarget(table);
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
            String serializable = importGenerator.type("java.io.Serializable");
            dataClass.append("package ").append(packageName).append(";\n\n");
            dataClass.append(importGenerator.getImports());
            dataClass.append("public class ").append(Helper.getDataClassName(currentTable));
            dataClass.append(" implements ").append(serializable).append(", ");
            dataClass.append(Helper.getCreateInfoClassName(currentTable)).append(", ");
            dataClass.append(Helper.getUpdateInfoClassName(currentTable)).append(" {\n");
            dataClass.append(fieldsGenerator.getFields()).append("\n");
            dataClass.append(methodGenerator.getMethods());
            dataClass.append("}\n");
            try {
            	String fileName = fileHelper.prepareFile(packageName, Helper.getDataClassName(currentTable) + ".java");
                Writer out = new BufferedWriter(new FileWriter(fileName));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
