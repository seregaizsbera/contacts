package su.sergey.contacts.codegen.datagen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;

/**
 * FieldsGenerator
 * @author 
 */
class FieldsGenerator implements TableListener {

    private StringBuffer fields;

    FieldsGenerator() {
        fields = new StringBuffer();
    }

    public void startTable(Table table) {
        fields.delete(0, fields.length());
    }

    public void attribute(Attribute attribute) {
        fields.append("\tprivate ").append(Helper.getJavaType(attribute)).append(" ").append(Helper.getAttributeFieldName(attribute)).append(";\n");
    }

    public void endTable() {
    }

    String getFields() {
        return fields.toString();
    }
}
