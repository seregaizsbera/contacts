package su.sergey.contacts.codegen.datainfogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * MethodGenerator
 * @author 
 */
class MethodGenerator implements TableListener {
    private StringBuffer methods;

    MethodGenerator() {
        methods = new StringBuffer();
    }

    public void startTable(Table table) {
        methods.delete(0, methods.length());
    }

    public void attribute(Attribute attribute) {
        methods.append("\t").append(Helper.getJavaType(attribute)).append(" get").append(Helper.getAttributeName(attribute)).append("();\n");
    }

    public void endTable() {
    }

    String getMethods() {
        return methods.toString();
    }
}
