package su.sergey.contacts.codegen.createinfogen;

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

    MethodGenerator(TypeListener typeListener) {
    	this.typeListener = typeListener;
        methods = new StringBuffer();
    }

    public void startTable(Table table) {
        methods.delete(0, methods.length());
    }

    public void attribute(Attribute attribute) {
        if (!attribute.isGenerated()) {
        	String typeName = typeListener.type(HelperFactory.getHelper().getJavaType(attribute));
            methods.append("    ").append(typeName);
            methods.append(" get").append(HelperFactory.getHelper().getAttributeName(attribute));
            methods.append("();\n");
        }
    }

    public void endTable() {
    }

    String getMethods() {
        return methods.toString();
    }
}
