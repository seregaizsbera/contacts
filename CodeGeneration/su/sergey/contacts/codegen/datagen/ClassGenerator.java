package su.sergey.contacts.codegen.datagen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import su.sergey.contacts.codegen.Environment;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.impl.Broadcaster;

/**
 * PrepClassGenerator
 * @author 
 */
public class ClassGenerator extends Broadcaster implements TableListener {

    public static final String PACKAGE = "com.sberbank.sbclients.dao.dto";

    private FieldsGenerator fieldsGenerator;
    private MethodGenerator methodGenerator;

    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;

    public ClassGenerator() {
        super();
        this.dataClass = new StringBuffer();
        fieldsGenerator = new FieldsGenerator();
        methodGenerator = new MethodGenerator();
        addListener(fieldsGenerator);
        addListener(methodGenerator);
    }

    public void startTable(Table table) {
        if (Helper.isTarget(table)) {
            isTarget = true;
            currentTable = table;
            dataClass.delete(0, dataClass.length());
            dataClass.append("package ").append(PACKAGE).append(";\n\n").
                    append("public class ").append(Helper.getDataClassName(table)).append(" implements java.io.Serializable, ").append(Helper.getCreateInfoClassName(table)).append(", ").append(Helper.getUpdateInfoClassName(table)).append(" {\n");
            super.startTable(table);
        } else {
            isTarget = false;
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
            dataClass.append(fieldsGenerator.getFields()).append("\n\n").append(methodGenerator.getMethods());
            dataClass.append("\n");
            dataClass.append("}");
            try {
                Writer out = new BufferedWriter(new FileWriter(Environment.DTO_OUT_PUT_PATH + Helper.getDataClassName(currentTable) + ".java"));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                System.err.println("" + e);
                e.printStackTrace();
                //throw new RuntimeException(e.getMessage());
            }
        }
    }
}
