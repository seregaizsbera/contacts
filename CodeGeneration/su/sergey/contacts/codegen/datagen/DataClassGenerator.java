package su.sergey.contacts.codegen.datagen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import su.sergey.contacts.codegen.util.FileHelper;
import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.impl.Broadcaster;
import su.sergey.contacts.codegen.impl.ImportGenerator;
import su.sergey.contacts.codegen.util.*;


/**
 * DataClassGenerator
 * 
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
    private boolean needUpdateInfo;

    public DataClassGenerator(FileHelper fileHelper, String packageName) {
    	this.fileHelper = fileHelper;
    	this.packageName = packageName;
        dataClass = new StringBuffer();
        importGenerator = new ImportGenerator();
        fieldsGenerator = new FieldsGenerator(importGenerator);
        needUpdateInfo = false;
        methodGenerator = new MethodGenerator(importGenerator);
        addListener(importGenerator);
        addListener(fieldsGenerator);
        addListener(methodGenerator);
    }

    public void startTable(Table table) {
    	isTarget = HelperFactory.getHelper().isTarget(table);
        if (isTarget) {
            currentTable = table;
            dataClass.delete(0, dataClass.length());
            needUpdateInfo =  false;
            super.startTable(table);
        }
    }

    public void attribute(Attribute attribute) {
        if (isTarget) {
            super.attribute(attribute);
            if (HelperFactory.getHelper().isForUpdate(attribute)) {
            	needUpdateInfo = true;
            }
        }
    }

    public void endTable() {
        if (isTarget) {
            super.endTable();
            String serializable = importGenerator.type("java.io.Serializable");
            dataClass.append("package ").append(packageName).append(";\n\n");
            dataClass.append(importGenerator.getImports());
            dataClass.append("public final class ").append(HelperFactory.getHelper().getDataClassName(currentTable));
            dataClass.append(" implements ").append(serializable).append(", ");
            dataClass.append(HelperFactory.getHelper().getCreateInfoClassName(currentTable));
            if (needUpdateInfo) {
                dataClass.append(", ");
                dataClass.append(HelperFactory.getHelper().getUpdateInfoClassName(currentTable));
            }
            dataClass.append(" {\n");
            dataClass.append(fieldsGenerator.getFields()).append("\n");
            dataClass.append(methodGenerator.getMethods());
            dataClass.append("}\n");
            try {
            	String fileName = fileHelper.prepareFile(packageName, HelperFactory.getHelper().getDataClassName(currentTable) + ".java");
                Writer out = new BufferedWriter(new FileWriter(fileName));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
