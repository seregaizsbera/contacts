package su.sergey.contacts.codegen.updateinfogen;

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
 * UpdateInfoClassGenerator
 * 
 * @author Сергей Богданов
 */
public class UpdateInfoClassGenerator extends Broadcaster implements TableListener {
    private ImportGenerator importGenerator;
    private MethodGenerator methodGenerator;
    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;
    private FileHelper fileHelper;
    private String packageName;
    private boolean empty;

    public UpdateInfoClassGenerator(FileHelper fileHelper, String packageName) {
    	this.fileHelper = fileHelper;
    	this.packageName = packageName;
    	empty = true;
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
            empty = true;
        }
    }

    public void attribute(Attribute attribute) {
        if (isTarget) {
            super.attribute(attribute);
            if (HelperFactory.getHelper().isForUpdate(attribute)) {
            	empty = false;
            }
        }
    }

    public void endTable() {
        if (isTarget && !empty) {
            super.endTable();
            dataClass.append("package ").append(packageName).append(";\n\n");
            dataClass.append(importGenerator.getImports());
            dataClass.append("public interface ").append(HelperFactory.getHelper().getUpdateInfoClassName(currentTable)).append(" {\n");
            dataClass.append(methodGenerator.getMethods());
            dataClass.append("}\n");
            try {
            	String fileName = fileHelper.prepareFile(packageName, HelperFactory.getHelper().getUpdateInfoClassName(currentTable) + ".java");
                Writer out = new BufferedWriter(new FileWriter(fileName));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
