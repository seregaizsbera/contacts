package su.sergey.contacts.codegen.updateinfogen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * MethodGenerator
 * @author 
 * @date 16.07.2002
 * @time 11:12:42
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
        if (!attribute.isGenerated() && attribute.getKeyseq() == 0) {
            methods.append("\t").append(Helper.getJavaType(attribute)).append(" get").append(Helper.getAttributeName(attribute)).append("();\n");
        }
    }

    public void endTable() {
    }

    String getMethods() {
        return methods.toString();
    }
}
