package su.sergey.contacts.codegen.datagen;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.util.*;


/**
 * FieldsGenerator
 * 
 * @author Сергей Богаданов
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
    	String typeName = typeListener.type(HelperFactory.getHelper().getJavaType(attribute));
        fields.append("    private ").append(typeName);
        fields.append(" ").append(HelperFactory.getHelper().getAttributeFieldName(attribute)).append(";\n");
    }

    public void endTable() {}

    String getFields() {
        return fields.toString();
    }
}
