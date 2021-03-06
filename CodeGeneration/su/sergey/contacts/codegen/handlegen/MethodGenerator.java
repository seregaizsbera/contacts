package su.sergey.contacts.codegen.handlegen;

import su.sergey.contacts.codegen.util.HelperFactory;
import su.sergey.contacts.codegen.db.Attribute;
import su.sergey.contacts.codegen.db.Table;
import su.sergey.contacts.codegen.db.TableListener;
import su.sergey.contacts.codegen.db.TypeListener;
import su.sergey.contacts.codegen.util.*;


/**
 * MethodGenerator
 * 
 * @author ������ ��������
 */
class MethodGenerator implements TableListener {
	private TypeListener typeListener;
	private StringBuffer methods;
	
	MethodGenerator(TypeListener typeListener) {
		this.typeListener = typeListener;
		methods = new StringBuffer();
	}
	
	/**
	 * @see TableListener#startTable(Table)
	 */
	public void startTable(Table table) {
		methods.delete(0, methods.length());
	}
	
	/**
	 * @see TableListener#attribute(Attribute)
	 */
	public void attribute(Attribute attribute) {
		if (attribute.getKeyseq() != 0) {
            String typeName = typeListener.type(HelperFactory.getHelper().getJavaType(attribute));
			
            methods.append("    public ");
            methods.append(typeName);
            methods.append(" get");
            methods.append(HelperFactory.getHelper().getAttributeName(attribute));
            methods.append("() {\n");
            
            methods.append("        return ");
            methods.append(HelperFactory.getHelper().getAttributeFieldName(attribute));
            methods.append(";\n");
            
            methods.append("    }\n\n");
		}
	}
	
	/**
	 * @see TableListener#endTable()
	 */
	public void endTable() {
		int len = methods.length();
        if (len >= 2 && methods.substring(len - 2).equals("\n\n")) {
        	methods.delete(len - 1, len);
        }
	}

    String getMethods() {
        return methods.toString();
    }
}
