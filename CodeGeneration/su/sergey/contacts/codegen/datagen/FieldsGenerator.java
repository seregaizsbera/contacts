package su.sergey.contacts.codegen.datagen;

import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Helper;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;

/**
 * FieldsGenerator
 * 
 * @author ������ ���������
 */
class FieldsGenerator implements TableListener {
	private TypeListener typeListener;
    private StringBuffer fields;

    FieldsGenerator(TypeListener typeListener) {
    	this.typeListener = typeListener;
        fields = new StringBuffer();
    }

    public void startTable(Table table) {
        fields.delete(0, fields.length());
    }

    public void attribute(Attribute attribute) {
    	String typeName = typeListener.type(Helper.getJavaType(attribute));
        fields.append("    private ").append(typeName);
        fields.append(" ").append(Helper.getAttributeFieldName(attribute)).append(";\n");
    }

    public void endTable() {}

    String getFields() {
        return fields.toString();
    }
}
