package su.sergey.contacts.codegen.delgen;

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
    private StringBuffer getMethods;
    private StringBuffer setMethods;

    MethodGenerator() {
        getMethods = new StringBuffer();
        setMethods = new StringBuffer();
    }

    public void startTable(Table table) {
        getMethods.delete(0, getMethods.length());
        setMethods.delete(0, setMethods.length());
    }

    public void attribute(Attribute attribute) {
        getMethods.append("\tpublic ").append(Helper.getJavaType(attribute)).append(" get").append(Helper.getAttributeName(attribute)).append("() {\n");
        getMethods.append("\t\treturn _value.get").append(Helper.getAttributeName(attribute)).append("();\n\t}\n");

        setMethods.append("\tpublic void ").append(" set").append(Helper.getAttributeName(attribute)).append("(").
                append(Helper.getJavaType(attribute)).append(" ").append(Helper.getAttributeFieldName(attribute)).append(") {\n");
        setMethods.append("\t\t_value.set").append(Helper.getAttributeName(attribute)).append("(").
                append(Helper.getAttributeFieldName(attribute)).append(");\n\t}\n");
    }

    public void endTable() {
    }

    String getGetMethods() {
        return getMethods.toString();
    }
    String getSetMethods() {
        return setMethods.toString();
    }
}
