package su.sergey.contacts.codegen.daogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;

/**
 * AddOutsMethodGenerator
 * 
 * @author Сергей Богданов
 */
public class AddOutsMethodGenerator implements TableListener {
    private StringBuffer method;
    private Table currentTable;
    private TypeListener typeListener;
    private String sqlOutAccessorClassName;

    public AddOutsMethodGenerator(TypeListener typeListener, String sqlOutAccessorClassName) {
    	this.typeListener = typeListener;
    	this.sqlOutAccessorClassName = sqlOutAccessorClassName;
        method = new StringBuffer();
    }

    public void startTable(Table table) {
        method.delete(0, method.length());
        String sqlOutAccessor = typeListener.type(sqlOutAccessorClassName);
        method.append("    public void addOuts(").append(sqlOutAccessor).append(" accessor) {\n");
        currentTable = table;
    }

    public void attribute(Attribute attribute) {
        method.append("        ").append("accessor.addOut(\"").append(attribute.getColumnName()).append("\");\n");
    }

    public void endTable() {
        method.append("    }\n");
    }
    
    public String getMethod() {
        return method.toString();
    }
}
