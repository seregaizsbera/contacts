package su.sergey.contacts.codegen.daogen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * PrepClassGenerator
 * @author 
 * @date 16.07.2002
 * @time 11:16:51
 */

public class RealClassGenerator implements TableListener {

    public static final String PACKAGE = "com.sberbank.sbclients.dao.db2";

//    private InsertMethodGenerator insertGenerator;
//    private SelectMethodGenerator selectGenerator;
//    private UpdateMethodGenerator updateGenerator;
//    private RemoveMethodGenerator removeGenerator;

    private StringBuffer dataClass;
    private boolean isTarget;
    private Table currentTable;

    public RealClassGenerator() {
        super();
        this.dataClass = new StringBuffer();
//        insertGenerator = new InsertMethodGenerator();
//        selectGenerator = new SelectMethodGenerator();
//        updateGenerator = new  UpdateMethodGenerator();
//        removeGenerator = new RemoveMethodGenerator();
//        addListener(insertGenerator);
//        addListener(selectGenerator);
//        addListener(updateGenerator);
//        addListener(removeGenerator);
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
            dataClass.append("public class ").append(Helper.getRealDaoClassName(table)).append(" extends ").append(Helper.getDaoClassName(table)).append(" {\n");
            dataClass.append("\tprivate static final ").append(Helper.getRealDaoClassName(table)).append(" instance = new ").append(Helper.getRealDaoClassName(table)).append("();\n\n");
            dataClass.append("\tprivate ").append(Helper.getRealDaoClassName(table)).append("() {\n");
            dataClass.append("\t}\n\n");
//            super.startTable(table);
        } else {
            isTarget = false;
        }
    }

    public void attribute(Attribute attribute) {
        if (isTarget) {
//            super.attribute(attribute);
        }
    }

    public void endTable() {
        if (isTarget) {
//            super.endTable();
//            dataClass.append(insertGenerator.getMethod());
//            dataClass.append("\n\n");
//            dataClass.append(selectGenerator.getMethod());
//            dataClass.append("\n\n");
//            dataClass.append(updateGenerator.getMethod());
//            dataClass.append("\n\n");
//            dataClass.append(removeGenerator.getMethod());
            dataClass.append("\n\n");
            dataClass.append("\tpublic static final ").append(Helper.getRealDaoClassName(currentTable)).append(" getInstance() {\n");
            dataClass.append("\t\treturn instance;\n");
            dataClass.append("\t}\n");
            dataClass.append("}");

            try {
                Writer out = new BufferedWriter(new FileWriter(".\\ibm_project\\ISClientsEJB\\ejbModule\\com\\sberbank\\sbclients\\dao\\db2\\" + Helper.getRealDaoClassName(currentTable) + ".java"));
                out.write(dataClass.toString());
                out.close();
            } catch (IOException e) {
                System.err.println("" + e);
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
