package su.sergey.contacts.codegen.daogen;

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
public class PrepClassGenerator extends Broadcaster implements TableListener {

    public static final String PACKAGE = "com.sberbank.sbclients.dao.db2";

    private InsertMethodGenerator insertGenerator;
    private SelectMethodGenerator selectGenerator;
    private UpdateMethodGenerator updateGenerator;
    private AddOutsMethodGenerator addOutsMethodGenerator;
    private PopulateMethodGenerator populateMethodGenerator;
    private RemoveMethodGenerator removeGenerator;

    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;

    public PrepClassGenerator() {
        super();
        this.dataClass = new StringBuffer();
        insertGenerator = new InsertMethodGenerator();
        selectGenerator = new SelectMethodGenerator();
        updateGenerator = new  UpdateMethodGenerator();
        removeGenerator = new RemoveMethodGenerator();
        addOutsMethodGenerator = new AddOutsMethodGenerator();
        populateMethodGenerator = new PopulateMethodGenerator();
        addListener(insertGenerator);
        addListener(selectGenerator);
        addListener(updateGenerator);
        addListener(removeGenerator);
        addListener(addOutsMethodGenerator);
        addListener(populateMethodGenerator);
    }

    public void startTable(Table table) {
        if (Helper.isTarget(table)) {
            isTarget = true;
            currentTable = table;
            dataClass.delete(0, dataClass.length());
            dataClass.append("package ").append(PACKAGE).append(";\n\n");
            dataClass.append("import ").append("java.sql.*").append(";\n");
            dataClass.append("import ").append("com.sberbank.sbclients.util.dao.*").append(";\n");
            dataClass.append("import ").append("com.sberbank.sbclients.util.dao.db2.*").append(";\n");
            dataClass.append("import ").append("com.sberbank.sbclients.dao.dto.*").append(";\n");
            dataClass.append("import ").append("com.sberbank.sbclients.database.dao.*").append(";\n");
            dataClass.append("public class ").append(Helper.getDaoClassName(table)).append(" extends AbstractDAO {\n");
            dataClass.append("\tprivate static final ").append(Helper.getDaoClassName(table)).append(" instance = new ").append(Helper.getDaoClassName(table)).append("();\n\n");
            dataClass.append("\tprotected ").append(Helper.getDaoClassName(table)).append("() {\n");
            dataClass.append("\t}\n\n");
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
            dataClass.append("\n\n");
            dataClass.append(insertGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append(selectGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append(updateGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append(removeGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append(addOutsMethodGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append(populateMethodGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append("\tpublic static final ").append(Helper.getDaoClassName(currentTable)).append(" getInstance() {\n");
            dataClass.append("\t\treturn instance;\n");
            dataClass.append("\t}\n");
            dataClass.append("}");

            try {
                Writer out = new BufferedWriter(new FileWriter(Environment.DAO_OUT_PUT_PATH + Helper.getDaoClassName(currentTable) + ".java"));
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
