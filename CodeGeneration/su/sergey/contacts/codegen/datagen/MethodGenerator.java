package su.sergey.contacts.codegen.datagen;

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
        methods.append("\tpublic ").append(Helper.getJavaType(attribute)).append(" get").append(Helper.getAttributeName(attribute)).append("() {\n");
        methods.append("\t\treturn ").append(Helper.getAttributeFieldName(attribute)).append(";\n\t}\n");

        methods.append("\tpublic void ").append(" set").append(Helper.getAttributeName(attribute)).append("(").
                append(Helper.getJavaType(attribute)).append(" ").append(Helper.getAttributeFieldName(attribute)).append(") {\n");
        methods.append("\t\tthis.").append(Helper.getAttributeFieldName(attribute)).append(" =").
                    append(" ").append(Helper.getAttributeFieldName(attribute)).append(";\n\t}\n");
    }

    public void endTable() {
    }

    String getMethods() {
        return methods.toString();
    }
}
