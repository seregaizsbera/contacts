package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * InsertMethodGenerator
 * @author 
 * @date 16.07.2002
 * @time 15:41:16
 */

public class AddOutsMethodGenerator implements TableListener {

    private StringBuffer method;

    private Table currentTable;

    public AddOutsMethodGenerator() {
        method = new StringBuffer();
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        method.append("\tpublic void addOuts(").append("SqlOutAccessor accessor) {\n");
        currentTable = table;
    }

    public void attribute(Attribute attribute) {
        method.append("\t\t").append("accessor.addOut(\"").append(attribute.getColumnName()).append("\");\n");
    }

    public void endTable() {
        method.append("\t}\n");
    }
    public String getMethod() {
        return method.toString();
    }
}

