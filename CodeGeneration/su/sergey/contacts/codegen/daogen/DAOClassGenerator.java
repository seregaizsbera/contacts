package su.sergey.contacts.codegen.daogen;

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
 * DAOClassGenerator
 * 
 * @author ������ ��������
 */
public class DAOClassGenerator extends Broadcaster implements TableListener {
	private ImportGenerator importGenerator;
    private InsertMethodGenerator insertGenerator;
    private SelectMethodGenerator selectGenerator;
    private UpdateMethodGenerator updateGenerator;
    private AddOutsMethodGenerator addOutsMethodGenerator;
    private PopulateMethodGenerator populateMethodGenerator;
    private RemoveMethodGenerator removeGenerator;
    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;
    private String packageName;
    private FileHelper fileHelper;
    private String abstractDaoClassName;
    private String connectionSourceClassName;
    private String myClassName;

    public DAOClassGenerator(FileHelper fileHelper,
                             String packageName,
                             String dtoPackage,
                             String abstractDaoClassName,
                             String connectionSourceClassName,
                             String sqlOutAccessorClassName,
                             String daoExceptionClassName) {
    	this.fileHelper = fileHelper;
    	this.packageName = packageName;
    	this.abstractDaoClassName = abstractDaoClassName;
    	this.connectionSourceClassName = connectionSourceClassName;
        dataClass = new StringBuffer();
        importGenerator = new ImportGenerator();
        insertGenerator = new InsertMethodGenerator(importGenerator, dtoPackage, daoExceptionClassName);
        selectGenerator = new SelectMethodGenerator(importGenerator, dtoPackage, daoExceptionClassName);
        updateGenerator = new UpdateMethodGenerator(importGenerator, dtoPackage, daoExceptionClassName);
        removeGenerator = new RemoveMethodGenerator(importGenerator, dtoPackage, daoExceptionClassName);
        addOutsMethodGenerator = new AddOutsMethodGenerator(importGenerator, sqlOutAccessorClassName);
        populateMethodGenerator = new PopulateMethodGenerator(importGenerator, dtoPackage);
        addListener(importGenerator);
        addListener(insertGenerator);
        addListener(selectGenerator);
        addListener(updateGenerator);
        addListener(removeGenerator);
        addListener(addOutsMethodGenerator);
        addListener(populateMethodGenerator);
    }

    public void startTable(Table table) {
    	isTarget = HelperFactory.getHelper().isTarget(table);
        if (isTarget) {
            currentTable = table;
            dataClass.delete(0, dataClass.length());
        	myClassName = HelperFactory.getHelper().getDaoClassName(table);
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
            String abstractDao = importGenerator.type(abstractDaoClassName);
            String connectionSource = importGenerator.type(connectionSourceClassName);
            dataClass.append("package ").append(packageName).append(";\n\n");
            dataClass.append(importGenerator.getImports());
            dataClass.append("public final class ").append(myClassName);
            dataClass.append(" extends ").append(abstractDao).append(" {\n");
            dataClass.append("    public ").append(myClassName).append("(").append(connectionSource).append(" connectionSource) {\n");
            dataClass.append("        super(connectionSource);\n");
            dataClass.append("    }\n\n");
            dataClass.append(insertGenerator.getMethod());
            dataClass.append("\n");
            dataClass.append(selectGenerator.getMethod());
            dataClass.append("\n");
            if (!updateGenerator.isEmpty()) {
    			dataClass.append(updateGenerator.getMethod());
			    dataClass.append("\n");
            }
			dataClass.append(removeGenerator.getMethod());
			dataClass.append("\n");
			dataClass.append(addOutsMethodGenerator.getMethod());
			dataClass.append("\n");
			dataClass.append(populateMethodGenerator.getMethod());
            dataClass.append("}\n");
            try {
            	String fileName = fileHelper.prepareFile(packageName, myClassName + ".java");
                Writer out = new BufferedWriter(new FileWriter(fileName));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
