package su.sergey.contacts.codegen.datagen;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.util.*;


/**
 * MethodGenerator
 * 
 * @author Сергей Богданов
 */
class MethodGenerator implements TableListener {
	private TypeListener typeListener;
    private StringBuffer methods;
    private boolean firstField;

    MethodGenerator(TypeListener typeListener) {
    	this.typeListener = typeListener;
        methods = new StringBuffer();
    }

    public void startTable(Table table) {
        methods.delete(0, methods.length());
        firstField = true;
    }

    public void attribute(Attribute attribute) {
    	if (!firstField) {
    		methods.append("\n");
    	}
    	String typeName = typeListener.type(HelperFactory.getHelper().getJavaType(attribute));
    	String fieldName = HelperFactory.getHelper().getAttributeFieldName(attribute);
    	String attributeName = HelperFactory.getHelper().getAttributeName(attribute);
        methods.append("    public ").append(typeName);
        methods.append(" get").append(attributeName).append("() {\n");
        methods.append("        return ").append(fieldName).append(";\n    }\n\n");

        methods.append("    public void ").append(" set").append(attributeName);
        methods.append("(").append(typeName).append(" ").append(fieldName).append(") {\n");
        methods.append("        this.").append(fieldName).append(" = ");
        methods.append(fieldName).append(";\n    }\n");
    	firstField = false;
    }

    public void endTable() {}

    String getMethods() {
        return methods.toString();
    }
}
